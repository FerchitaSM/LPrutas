package com.example.lp.bl;

import com.example.lp.bl.graph.graph.Edge;
import com.example.lp.bl.graph.graph.Graph;
import com.example.lp.bl.graph.graph.Vertex;
import com.example.lp.dao.*;
import com.example.lp.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
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
    private static File file=null;
    private static String u_origin="";
    private static String u_destination="";
    private static int cod_transport=0;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteBl.class);
    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;
    private StopRepository stopRepository;
    private StopBl stopBl;
    private TransportInfoRepository transportInfoRepository;
    private ConnectionRoutesRepository connectionRoutesRepository;


    @Autowired
    public RouteBl(RouteRepository routeRepository, RouteStopRepository routeStopRepository, StopRepository stopRepository, StopBl stopBl, TransportInfoRepository transportInfoRepository, ConnectionRoutesRepository connectionRoutesRepository) {
        this.routeRepository = routeRepository;
        this.routeStopRepository=routeStopRepository;
        this.stopRepository=stopRepository;
        this.stopBl=stopBl;
        this.transportInfoRepository=transportInfoRepository;
        this.connectionRoutesRepository=connectionRoutesRepository;
    }
    /*Obteniendo el codigo del transporte escogido*/
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
    /*Mensaje sobre descargar Google Earth*/
    public String download_message(){
        String message="";
        String instalation="Debes tener instalado Google Earth para poder abrir el archivo: "+"\n";
        String link="https://play.google.com/store/apps/details?id=com.google.earth&hl=es_BO";
        message=instalation+link;
        return message;
    }

    //Funcionalidad de la parte uno obteniendo la lista de origen
    public String route_one(Update update,String message){
        //Se obtiene la latitud y longitud del usuario
      //  u_origin=latitude(update)+","+longitude(update);
        //Obteniendo una lista con los lugares mas cercanos a mi ubicacion
        list_origin=nearby_points(list_origin,update,10);
        message="Envia la ubicacion a donde quieres llegar";
        return message;
    }
    //Funcionalidad para acabar la segundo parte dibujando el mapa
    public String route_two(Update update,String message){
       // u_destination=latitude(update)+","+longitude(update);
        //Obteniendo los puntos mas cercanos a mi destino
        list_destination=nearby_points(list_destination,update,15);
        //Se envia la lista de puntos cercanos a la ubicacion del usuario y la lista de los puntos cercanos a su destino
       // List<Integer> codes=new ArrayList<>();
        //codes=findRoute(list_origin,list_destination);
       // message=routelist_url(codes,message);
        message=kml(list_origin,list_destination);
        return message;
    }


/*//////////////////////////////////////////////////////REALIZANDO MAPAS CON KML////////////////////////////////////////////////////////////////////////////*/
    public String kml(List<Integer> list_origin,List<Integer> list_destination){
        String message="";
        List<Integer> origin_routes=to_route(list_origin);
        List<Integer> destination_routes=to_route(list_destination);
        List<String> routes = to_connections_routes();
        //generamos el grafo
        Graph<String, String> grafo = new Graph<>(true);
        //se ingresara adentro del for para que cada vez que compare inicialize otra vez
        /*Se utiliza un for de los origin_routes para ir uno por uno para formar su grafo y ver si estan conectados con algun destino*/
        for (int g = 0; g < origin_routes.size(); g++) {
            //TODO falta la comparacion de los minimios entre origenes y rutas al obtener ya la ruta al destino minio es lo mismo pero con origenes
            /*se inicia el general que es tipo string para poder encontrar repeticiones con la funcion string*/
            message=general(origin_routes.get(g),grafo,routes,destination_routes,message);
            /*cuando el cont sea igual a el size del general la bandera cambiara a true*/
        }
        return message;
    }

    private String general(int origin_routes, Graph<String,String> graph, List<String> routes, List<Integer> destination_routes, String message){
        /*Se obtiene el grafo a partir del origin_routes que se da */
        graph=general_graph(graph,origin_routes,routes);
        /*Se elimina las repeticiones del grafo*/
        graph=finding_repetitions_graph(graph);
        /*Se verifica que todas las destination routes existan dentro del general y se obtiene una nueva lista de destination routes que si se encuentren en
        * el general*/
        destination_routes=verifying_destiny_exists(graph,destination_routes);
        /*si el size de destination_routes es mayor a 0 entonces por lo menos este tiene un destino*/
        if(destination_routes.size()>0){
            message="SI HAY UNA RUTA DISPONIBLE"+"\n";
            dijkstra(graph,origin_routes,destination_routes);
        }else{
            message="NO HAY RUTA DISPONIBLE";
        }
        return message;
    }

    private Graph<String,String> general_graph(Graph<String,String> graph,int origin_routes,List<String> routes){
        /*se utiliza una bandera cuando la bandera sea true se conecto- dibujo correctamente el grafo y el general*/
        boolean bandera = false;
        List<String> general=new ArrayList<>();
        int cont = 0;
        /*se adiciona su primer objeto desde el cual se dibujara el grafo o se adicionaran los demas nodos*/
        general.add(origin_routes+"");
        while (bandera == false) {
            /*se adiciona al grafo adentro */
            Vertex<String, String> vuno = graph.addVertex(general.get(cont));
            for (int i = 0; i < routes.size(); i++) {
                String string = routes.get(i);
                String[] parts = string.split(",");
                String part1 = parts[0];
                String part2 = parts[1];
                /* se manejan dos vertex aca*/
                Vertex<String, String> vdos = null;
                if (part1.equals(general.get(cont)) || part2.equals(general.get(cont))) {
                    if (part1.equals(general.get(cont))) {
                        vdos = graph.addVertex(part2);
                        general.add(part2);
                        graph.addEdge(vuno, vdos);
                    } else {
                        if (part2.equals(general.get(cont))) {
                            vdos = graph.addVertex(part1);
                            general.add(part1);
                            graph.addEdge(vuno, vdos);
                        }
                    }

                }
            }
            /*aqui encontramos si la lista general tiene repeticiones en su lista*/
            general = finding_repetitions_string(general);
            cont++;
            if (general.size() == cont) {
                bandera = true;
            }
        }
        return graph;
    }


    /*Verifica que rutas dentro del destination se encuentran en el general*/
    private List<Integer> verifying_destiny_exists(Graph<String,String> graph, List<Integer> destination_routes){
        List<Integer> new_destination_routes=new ArrayList<>();
        for(int w=0;w<destination_routes.size();w++){
            Vertex<String,String> exists_vertex=null;
            exists_vertex=convert_string_to_vertex(graph,destination_routes.get(w)+"");
            /*se compruea si el destino existe en el grafo*/
            if(exists_vertex!=null){
                new_destination_routes.add(destination_routes.get(w));
            }
        }
        return new_destination_routes;
    }

    /*....................................................DIJKSTRA.....................................................................................*/
/*En esta funcion se maneja dijkstra*/
    private void dijkstra(Graph<String,String> graph,int origin_routes,List<Integer> destination_routes){
        List<String> draw_route=compare_route_size(graph,origin_routes,destination_routes);
        file=createKMLFile(draw_route);
    }
    /*se compara con todos los destination routes y el que tenga menor cantidad de rutas conectadas se devuelve*/
    private List<String> compare_route_size(Graph<String,String> graph,int origin_routes,List<Integer> destination_routes){
        List<String> draw_route=new ArrayList<>();
        int minimum_route=100;
        /* Se coloco un numero alto para poder comparar y obtener la ruta que teng ala menor cantidad de conexiones*/
        for(int i=0;i<destination_routes.size();i++)
        {
            draw_route=dijkstra_function(graph,origin_routes,destination_routes.get(i));
            if(draw_route.size()<minimum_route){
                minimum_route=destination_routes.get(i);
            }
        }
        //TODO revisar que cuando el usuario envia un mensaje despues de pedir el tipo de transporte este acepta y esta mal
        /*Al obtener la ruta y comprobando que el destin_route es mayor a 0 y distinto de nuestro numero de comparacion del principio se genera la lista de
        * la ruta*/

        if(minimum_route>0 && minimum_route!=100){
            draw_route=dijkstra_function(graph,origin_routes,minimum_route);
        }
        return draw_route;
    }

    /*Se utiliza la funcion de dijkstra para almacenar todas las rutas por las cuales se debe pasar*/
    private List<String> dijkstra_function(Graph<String,String> graph,int origin_routes,int destination_routes){
        List<String> draw_route=new ArrayList<>();
        Vertex<String,String> v1=convert_string_to_vertex(graph,origin_routes+"");
        Vertex<String,String> v2=convert_string_to_vertex(graph,destination_routes+"");
        Edge<String,String> dijsktra[]=graph.dijkstra(v1,v2);
        for(Edge<String,String> dijsktra1:dijsktra){
            System.out.println(dijsktra1);
            draw_route.add(dijsktra1.getV1().getData()+"");
            draw_route.add(dijsktra1.getV2().getData()+"");
        }
        draw_route=finding_repetitions_string(draw_route);
        return draw_route;
    }
    /*....................................................................................................................................................*/
    /*Convirtiendo la lista obteniendo sus rutas*/
    private List<Integer> to_route(List<Integer> list){
        List<Integer> convert_list=new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            List<RouteStopEntity> all = this.routeStopRepository.findRoute(list.get(i));
            for (RouteStopEntity x : all) {
                //se obtiene la ruta del punto de inicio
                convert_list.add(x.getRouteIdRoute());
            }
        }
        return convert_list;
    }
    /*Se obtiene las conexiones de rutas segun el tipo de transporte*/
    //TODO idea basica busqueda mixta modificar
    private List<String> to_connections_routes(){
        List<String> routes = new ArrayList<>();
        List<ConnectionRoutesEntity> all=null;
        if(cod_transport>3){
            all=this.connectionRoutesRepository.findAll();
        }else{
            all=this.connectionRoutesRepository.findAllByTypeTransport(cod_transport);
        }
        for (ConnectionRoutesEntity x : all) {
            //se obtiene la ruta del punto de inicio
            routes.add(x.getRouteA()+","+x.getRouteB());
        }
        return routes;
    }

/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    /**************************************** SE DIBUJA UN NUEVO GRAFO ENCONTRANDO SI ESTE TIENE REPETIDOS************************************************/
    private Graph<String,String> finding_repetitions_graph(Graph<String,String> graph){
        List<String> vertex=vertex_to_String(graph);
        List<String> edge=edges_to_String(graph);
        /*Ahora si se dibuja un nuevo grafo*/
        Graph<String, String> new_graph =draw_new_graph(vertex,edge);
        return new_graph;
    }
    /*se convierte los vertex a una lista vertex*/
    private List<String> vertex_to_String(Graph<String,String> graph){
        List<String> vertex=new ArrayList<>();
        Vertex<String,String> v[] = graph.vertices_array();
        for(Vertex<String,String> v1:v) {
            vertex.add(v1.getData());
        }
        vertex=finding_repetitions_string(vertex);
        return vertex;
    }
    /*Se convierte los edges a una lista edges*/
    private List<String> edges_to_String(Graph<String,String> graph){
        List<String> edges=new ArrayList<>();
        Edge<String,String> e[]=graph.edges_array();
        for(Edge<String,String> e1: e){
            edges.add(e1.getV1().getData()+","+e1.getV2().getData());
        }
        edges=finding_repetitions_string(edges);
        return edges;
    }

    /*En esta funcion se dibuja un nuevo grafo */
    private Graph<String,String> draw_new_graph(List<String> vertex,List<String> edge){
        /*Se crea un nuevo grafo*/
        Graph<String, String> new_graph = new Graph<>(false);
        /*Se dibuja los Vertex*/
        for(int i=0;i<vertex.size();i++){
            new_graph.addVertex(vertex.get(i));
        }
        /*Se dibujar los edges*/
        for(int u=0;u<edge.size();u++){
            /*como los datos del edge se encuentra separados por una coma se utiliza split*/
            String string = edge.get(u);
            String[] parts = string.split(",");
            String part1 = parts[0];
            String part2 = parts[1];
            Vertex<String,String> v1=convert_string_to_vertex(new_graph,part1);
            Vertex<String,String> v2=convert_string_to_vertex(new_graph,part2);
            new_graph.addEdge(v1,v2);
        }
        return new_graph;
    }

    /*se obtiene el grafo y el String a convertir en Vertex*/
    private Vertex<String,String> convert_string_to_vertex(Graph<String,String> graph,String vertex)
    {
        Vertex<String,String> convert_vertex=null;
        /*se obtiene la lista de vertices para encontrar el string y obtener el vertex de este*/
        Vertex<String,String> v[] = graph.vertices_array();
        for(Vertex<String,String> v1:v) {
            if(v1.getData().equals(vertex)){
                convert_vertex=v1;
            }
        }
        return convert_vertex;
    }
    /**************************************************************************************************************************************************/

    /*----------------------------------CREANDO EL ARCHIVO KML --------------------------------------------------------------------------*/
    /*Se crea el archivo KML llamando a las respectivas funciones*/
    private File createKMLFile(List <String> routes){
        String kml=createStringKML(routes);
        File file=createFileKml(kml);
        return file;
    }
    /*Se crea el string kml, se obtiene la lista de rutas y se genera el string para cada ruta y el documento en general */
    private String createStringKML(List <String> routes){
        String kml="";
        /*inicio del documento*/
        String kmlstart = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
                "\t<Document>\n"+
                "\t<name>Líneas de Teleférico</name>\n"+
                "\t<description/>\n";
        String document="";
        /*for para ingresar el documento de todas las rutas*/
        for(int i=0;i<routes.size();i++){
            List<RouteEntity> all = this.routeRepository.findByRoute(Integer.parseInt(routes.get(i)));
            for (RouteEntity x : all) {
                //se obtiene la ruta del punto de inicio
                document=document+
                        "\t<NetworkLink>\n"+
                        "\t<name>"+x.getRouteName()+"</name>\n"+
                        "\t<Link>\n"+
                        "\t <href>"+x.getRouteDetails()+"</href>\n"+
                        "\t</Link>\n"+
                        "\t</NetworkLink>\n";

            }
        }
        /*cerrando el documento*/
        String kmlend = "\t</Document>\n"+
                "</kml>";
        /*Sumando en un solo String kml el documento*/
        kml=kmlstart+
                document+
                kmlend;
        return kml;
    }
    /*Se crea el File con el nombre Mapa.kml recibiendo el String kml*/
    private File createFileKml(String kml){
        File file=new File("Mapa.kml");
        PrintWriter fichero =null;
        try
        {
            fichero = new PrintWriter(file);
            fichero.println(kml);
            fichero.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

/*---------------------------------------------------------------------------------------------------------------------------------------------------*/

/*++++++++++++++++++++++++++++++++++++++++++++FUNCIONES GENERALES NO ESPECIFICAS PARA UNA SOLA FUNCION++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
    //Sacando la latitud
    private String latitude(Update update){
        return update.getMessage().getLocation().getLatitude()+"";
    }
    //Sacando la longitud
    private String longitude(Update update){
        return update.getMessage().getLocation().getLongitude()+"";
    }
    /*Encontrando repeticiones en una lista String*/
    private static List<String> finding_repetitions_string(List<String> codes){
        //Utilizando hashSet para evitar rutas repetidas
        Set<String> hashSet = new HashSet<String>(codes);
        codes.clear();
        codes.addAll(hashSet);
        return codes;
    }
    //Encontrando repeticioens en una lista int
    private List<Integer> finding_repetitions(List<Integer> codes){
        //Utilizando hashSet para evitar rutas repetidas
        Set<Integer> hashSet = new HashSet<Integer>(codes);
        codes.clear();
        codes.addAll(hashSet);
        return codes;
    }
    /*Retorna un File global que se encuentra arriba*/
    public File return_file(){
        return  file;
    }

    //Se obtiene una url grande y se la acorta utilizando tinyURL
    //karen uso esta funcion no la borres
    private String shorter(String url) throws IOException {
        String tinyUrl = "http://tinyurl.com/api-create.php?url=";
        String tinyUrlLookup = tinyUrl + url;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
        tinyUrl = reader.readLine();
        return tinyUrl;
    }

    //Se saca la lista de puntos cercanos a la ubicacion que se da
    private List<Integer> nearby_points(List<Integer> list, Update update,int compare){
        try {
            list=stopBl.findAllNearbyLocationStop(latitude(update)+","+longitude(update),compare);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    //Devolviendo la ruta para hoteles
    public String route_hotel(Update update,String message){
        //Se obtiene la latitud y longitud del usuario
        u_origin=latitude(update)+","+longitude(update);
        //Obteniendo una lista con los lugares mas cercanos a mi ubicacion
        list_origin=nearby_points(list_origin,update,15);

        message=kml(list_origin,list_destination);
        return message;
    }

    public void data_hotel(String latitude, String longitude) {
        u_destination=latitude+","+longitude;
        list_destination=nearby_points_hotel(list_destination,latitude, longitude);
    }

    private List<Integer> nearby_points_hotel(List<Integer> list, String latitude, String longitude) {
        try {
            list=stopBl.findAllNearbyLocationStop(latitude+","+longitude,15);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
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






    /*DONT USE CODE*/
    /////
    //Lista de rutas con su url
   /* private String routelist_url(List<Integer> codes,String message){
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
    }*/

}