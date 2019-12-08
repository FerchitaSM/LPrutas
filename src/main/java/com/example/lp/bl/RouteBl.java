package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.dao.RouteStopRepository;
import com.example.lp.dao.StopRepository;
import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.RouteStopEntity;
import com.example.lp.domain.StopEntity;
import com.example.lp.domain.TransportInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Service
public class RouteBl {
    List<Integer> list_origin=new ArrayList<>();
    //Lista de paradas cercanas al destino
    List<Integer> list_destination=new ArrayList<>();
    private static String u_origin="";
    private static String u_destination="";
    private static int cod_transport=0;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteBl.class);
    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;
    private StopRepository stopRepository;
    private StopBl stopBl;
    private TransportInfoRepository transportInfoRepository;


    @Autowired
    public RouteBl( RouteRepository routeRepository,RouteStopRepository routeStopRepository,StopRepository stopRepository,StopBl stopBl,TransportInfoRepository transportInfoRepository) {
        this.routeRepository = routeRepository;
        this.routeStopRepository=routeStopRepository;
        this.stopRepository=stopRepository;
        this.stopBl=stopBl;
        this.transportInfoRepository=transportInfoRepository;
    }
    public void get_transport(Update update) throws IOException {
        String type_transport=update.getMessage().getText();
        List<TransportInfoEntity> all = this.transportInfoRepository.findAll();
        int info_id=0;
        for (TransportInfoEntity x: all) {
            if(type_transport.equals(x.getInfoDescription())){
                info_id=x.getIdTransportInfo();
            }
        }
        cod_transport=info_id;
    }
    //Funcionalidad de la parte uno obteniendo la lista de origen
    public String route_one(Update update,String message){
        //Se obtiene la latitud y longitud del usuario
        u_origin=latitude(update)+","+longitude(update);
        //Obteniendo una lista con los lugares mas cercanos a mi ubicacion
        list_origin=nearby_points(list_origin,update);
        message="Envia la ubicacion a donde quieres llegar";
        return message;
    }
    //Funcionalidad para acabar la segundo parte dibujando el mapa
    public String route_two(Update update,String message){
        u_destination=latitude(update)+","+longitude(update);
        //Obteniendo los puntos mas cercanos a mi destino
        list_destination=nearby_points(list_destination,update);
        //Se envia la lista de puntos cercanos a la ubicacion del usuario y la lista de los puntos cercanos a su destino
        List<Integer> codes=new ArrayList<>();
        codes=findRoute(list_origin,list_destination);
        message=routelist_url(codes,message);
        return message;
    }

    //Lista de rutas con su url
    private String routelist_url(List<Integer> codes,String message){
        //Preguntando si la ruta es distinta de cero
        String url= null;
        if(codes.size()>1){
            message="Grandioso ya tenemos la informacion\nEstas son las rutas disponibles:\n\n";
            //Encontrando si hay ruta repetidas
            codes=finding_repetitions(codes);
            for(int i=0;i<codes.size();i++){
                //Generando la url (dibujando el mapa que se enviara)
                try {
                    url =drawMap(codes.get(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Devolviendo la url corta
                message=message+"Ruta:  "+codes.get(i)+"\n";
                message=message+url+"\n";
            }
        }else{
                message="No hay una ruta disponible";
        }
        return message;
    }
    //Evitando rutas repetidas
    private List<Integer> finding_repetitions(List<Integer> codes){
        //Utilizando hashSet para evitar rutas repetidas
        Set<Integer> hashSet = new HashSet<Integer>(codes);
        codes.clear();
        codes.addAll(hashSet);
        return codes;
    }

    //Se saca la lista de puntos cercanos a la ubicacion
    private List<Integer> nearby_points(List<Integer> list, Update update){
        try {
            list=stopBl.findAllNearbyLocationStop(latitude(update)+","+longitude(update));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //Se da la lista de puntos de inicio y destino encontrando la ruta en la cual conectan
    private List<Integer> findRoute(List<Integer> start_points, List<Integer> finish_points){
        List<Integer> routes=new ArrayList<>();
            for(int i=0; i<start_points.size();i++){
                //Se obtiene la lista de puntos de inicio
                int point_start=start_points.get(i);
                //Se busca la ruta del punto o las rutas que esten relacionadas a el
                List<RouteStopEntity> all = this.routeStopRepository.findRoute(point_start);
                    for(RouteStopEntity x:all){
                        //se obtiene la ruta del punto de inicio
                        int route_start=x.getRouteIdRoute();
                        //se obtiene uno por uno los puntos de destino
                        for(int u=0;u<finish_points.size();u++){
                            int point_finish=finish_points.get(u);
                            //se obtiene la ruta donde la ruta sea igual a la del punto de inicio y al punto final
                            List<RouteStopEntity> find_route=this.routeStopRepository.findRouteFinish(route_start,point_finish);
                            for(RouteStopEntity y:find_route){
                               // List<TransportBl> find_transport=this.
                                //se obtiene cual es esa ruta y se la adiciona a la lista de rutas
                                routes.add(y.getRouteIdRoute());
                            }
                        }
                    }
            }

        //se devuelve la ruta
        return routes;
    }

    //Se dibuja el mapa llamando a la mayor parte de funciones en el String se devuelve la url corta
    private String drawMap(int route) throws IOException {
        //Se utiliza la funcion coordenadas donde se busca todos los puntos que esten relacionados con la ruta obtenida
        String short_url="";
        String url="";
        if(cod_transport==1){
            List<RouteEntity> all=this.routeRepository.findByRoute(route);
            for(RouteEntity x:all){
               url=x.getRouteDetails();
            }
            short_url=url;

        }else{
            // if(cod_transport==1){
            //   String url="https://www.google.com/maps/d/edit?hl=es&mid=1sT0h3AEummjmqVLW0pZpweC3pA2t3uTA&ll=-16.695798030199423%2C-68.03800741642942&z=12";
            //  short_url=shorter(url);
            // }else{
            List<String> coordinates=get_coordinates(route);
            //Se envia esta lista de coordenadas a se genere la URL
            url=get_url(route,coordinates);
            //Al tener el URL se lo convierte en una URLcorta
            short_url=shorter(url);
            // }
        }

        return short_url;
    }
    //Se genera la lista de rutas intermedias con la ruta enviada
    private List<String> get_coordinates(int route){
        List<String> coordinates=new ArrayList<>();
        List<StopEntity> all = this.stopRepository.findPointsRoute(route);
        for(StopEntity x:all){
            float latitude= (float) x.getLatitude();
            float longitude= (float) x.getLongitude();
            coordinates.add(latitude+","+longitude);
            LOGGER.info(latitude+","+longitude);
        }
        return coordinates;
    }

    //Se obtiene el origen y destino de la ruta
    private String origin_and_destin(int route){
        String origin="";
        //Se obtiene la ubicacion de origen de la ruta
        List<StopEntity> all = this.stopRepository.findStartPosition(route);
        for(StopEntity x:all){
            origin=x.getLatitude()+","+x.getLongitude();
        }
        //Se obtiene la ubicacion de destino de la ruta
        String destination="";
        List<StopEntity> two_all = this.stopRepository.findFinishPosition(route);
        for(StopEntity x:two_all){
            destination=x.getLatitude()+","+x.getLongitude();
        }
        return origin+"/"+destination;
    }

    //Se genera la URL enviandole la lista de coordenadas intermedias y la ruta
    private String get_url(int route,List<String> coordinates){
        //waypoints son las paradas intermedias
        String waypoint="";
        //Se divide con un split el origen y destino despues de invocar la funcion origin_and_destin
        String ubication=origin_and_destin(route);
        String points[]=ubication.split("/");
        String origin=points[0];
        String destination=points[1];
        //Url de inicio del mapa con solo origen y destino
        String url="https://www.google.com/maps/dir/?api=1&origin=+"+origin+"&destination="+destination+"&travelmode=driving&waypoints=";
        //Adicionando los puntos intermedios a la URl de inicio
       for(int i=0;i<coordinates.size();i++){
            String coordin=coordinates.get(i);
            waypoint=waypoint+coordin+"%7C";
        }
        url=url+waypoint;
        return url;
    }
    //Al obtener la url de inicio se la acorta
    //karen uso esta funcion no la borres
    private String shorter(String url) throws IOException {
        String tinyUrl = "http://tinyurl.com/api-create.php?url=";
        String tinyUrlLookup = tinyUrl + url;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
        tinyUrl = reader.readLine();
        return tinyUrl;
    }
    //Sacando la latitud
    private String latitude(Update update){
        return update.getMessage().getLocation().getLatitude()+"";
    }
    //Sacando la longitud
    private String longitude(Update update){
        return update.getMessage().getLocation().getLongitude()+"";
    }





    //Sirve para obtener la URL con las rutas por donde pasa el transporte segun su nombre
    public String drawMapByDescription(String name){
        List<String> listLatitudeStop = this.stopRepository.findLatitudeByDescription(name);
        List<String> listLongitudeStop = this.stopRepository.findLongitudeByDescription(name);
        List<String> listStop = new ArrayList<>();
        for (int i = 0; i<listLatitudeStop.size();i++) {
            String coordenadas= listLatitudeStop.get(i)+ ","+listLongitudeStop.get(i);
            listStop.add(coordenadas);
        }

        String url= "Lo sentimos aun no esta disponible la ruta ";
        try {
            url = drawMapForTransport(listStop);
        } catch (IOException e) {
            e.printStackTrace();
            return url;
        }
        return url;
    }
    public String drawMapForTransport(List<String> listStop) throws IOException {
        String url=getURLForTransport(listStop);
        String short_url=shorter(url);
        return short_url;
    }


    public String getURLForTransport(List<String> listStop ){
        String url="https://www.google.com/maps/dir/";
        int tamanio= listStop.size()-1;
        for (int i = 0; i<tamanio;i++) {
            url+=listStop.get(i)+"/";

        }
        if (tamanio>0)
            url+=listStop.get(tamanio);
        LOGGER.info(url);
        return url;
    }




    // -----------------------------------INTENTANDO HACER LOS MAPAS CON KML------------------------------------------------
    public void kml(){
        List<Integer> origin_routes=new ArrayList<>();
        for(int i=0;i<list_origin.size();i++) {
            List<RouteStopEntity> all = this.routeStopRepository.findRoute(list_origin.get(i));
            for (RouteStopEntity x : all) {
                //se obtiene la ruta del punto de inicio
                origin_routes.add(x.getRouteIdRoute());
            }
        }
        List<Integer> destination_routes=new ArrayList<>();
        for(int i=0;i<list_destination.size();i++) {
            List<RouteStopEntity> all = this.routeStopRepository.findRoute(list_destination.get(i));
            for (RouteStopEntity x : all) {
                //se obtiene la ruta del punto de inicio
                destination_routes.add(x.getRouteIdRoute());
            }
        }
        List<String> rutas = new ArrayList<>();
        rutas.add("1,2");
        rutas.add("2,3");
        rutas.add("3,4");
        rutas.add("4,5");
        rutas.add("3,9");
        rutas.add("4,9");
        //generamos el grafo
        //  Graph<String, String> grafo = new Graph<>(false);
        //se ingresara adentro del for para que cada vez que compare inicialize otra vez
        for (int g = 0; g < origin_routes.size(); g++) {
            boolean bandera = false;
            List<String> general = new ArrayList<>();
            int cont = 0;
            general.add(origin_routes.get(g)+"");
            while (bandera == false) {
                System.out.println("Este es el punto general> " + general.get(cont));
                //   Vertex<String, String> vuno = grafo.addVertex(general.get(cont));
                for (int i = 0; i < rutas.size(); i++) {
                    String string = rutas.get(i);
                    String[] parts = string.split(",");
                    String part1 = parts[0]; // 123
                    String part2 = parts[1]; // 654321
                    //   Vertex<String, String> vdos = null;
                    if (part1.equals(general.get(cont)) || part2.equals(general.get(cont))) {
                        if (part1.equals(general.get(cont))) {
                            // vdos = grafo.addVertex(part2);
                            general.add(part2);
                            //grafo.addEdge(vuno, vdos);
                            System.out.println("EL VALOR DE GENERAAAL CAMBIO A>" + general.size());
                        } else {
                            if (part2.equals(general.get(cont))) {
                                // vdos = grafo.addVertex(part1);
                                general.add(part1);
                                //grafo.addEdge(vuno, vdos);
                                System.out.println("EL VALOR DE GENERAAAL CAMBIO 222222222 A>" + general.size());
                            }
                        }

                    }
                }

                general = finding_repetitions2(general);
                cont++;
                System.out.println("ES IGUAL " + general.size() + " y " + " CONT? " + cont);
                if (general.size() == cont) {
                    bandera = true;
                }
                for (int t = 0; t < general.size(); t++) {
                    System.out.println("ESTOS SON LOS VALORES DE T" + general.get(t));
                }
                //  System.out.println(grafo);
             /* for(Vertex<String,String> e : grafo.vertices_array()){
                  System.out.println(e);
                  e.getData();
              }*/
                System.out.println("-------------------------------");
            }
            boolean flag=false;
            for(int w=0;w<destination_routes.size();w++){
                for(int c=0;c<general.size();c++){
                    if((destination_routes.get(w)+"").equals(general.get(c))){
                        flag=true;
                        System.out.println(" SI HAY UNA RUTA DISPONIBLE");
                    }
                }
            }
            if(flag==true){

            }
        }
    }
    public static List<String> finding_repetitions2(List<String> codes){
        //Utilizando hashSet para evitar rutas repetidas
        Set<String> hashSet = new HashSet<String>(codes);
        codes.clear();
        codes.addAll(hashSet);
        return codes;
    }




    //Se da la lista de puntos de inicio y destino encontrando la ruta en la cual conectan
    private List<Integer> findRoute2(List<Integer> start_points, List<Integer> finish_points){
        List<Integer> routes=new ArrayList<>();
        for(int i=0; i<start_points.size();i++){
            //Se obtiene la lista de puntos de inicio
            int point_start=start_points.get(i);
            //Se busca la ruta del punto o las rutas que esten relacionadas a el
            List<RouteStopEntity> all = this.routeStopRepository.findRoute(point_start);
            for(RouteStopEntity x:all){
                //se obtiene la ruta del punto de inicio
                int route_start=x.getRouteIdRoute();
                //se obtiene uno por uno los puntos de destino
                for(int u=0;u<finish_points.size();u++){
                    int point_finish=finish_points.get(u);
                    //se obtiene la ruta donde la ruta sea igual a la del punto de inicio y al punto final
                    List<RouteStopEntity> find_route=this.routeStopRepository.findRouteFinish(route_start,point_finish);
                    for(RouteStopEntity y:find_route){
                        // List<TransportBl> find_transport=this.
                        //se obtiene cual es esa ruta y se la adiciona a la lista de rutas
                        routes.add(y.getRouteIdRoute());
                    }
                }
            }
        }

        //se devuelve la ruta
        return routes;
    }
}