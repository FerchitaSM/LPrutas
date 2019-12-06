<?php
require("../Generales/conexion.php");



$query_markets = "SELECT 
            st.id_stop,
            st.description,
            st.latitude,
            st.longitude
    
            FROM stop st, route ro, route_stop_transport rst, transport tr
                         WHERE st.id_stop = ro.stop_start
                             AND ro.id_route = rst.route_id_route
                             AND rst.transport_id_transport = tr.id_transport
                             AND tr.description ='Villa Salome'";

$result_markets = mysqli_query($conn,$query_markets)  or die ("Problemas al leer".mysqli_error($conn));

header("Content-type: text/xml");

echo "<?xml version='1.0' ?>";

echo '<markers>';
while ($row = mysqli_fetch_assoc($result_markets)){
  // Add to XML document node
  echo '<marker ';
  echo 'id="' . $row['id_stop'] . '" ';
  echo 'name="' .$row['description'] . '" ';
  echo 'lat="' . $row['latitude'] . '" ';
  echo 'lng="' . $row['longitude'] . '" ';
  echo '/>';
}

// End XML file
echo '</markers>';

?>


