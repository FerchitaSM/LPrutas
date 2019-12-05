<?php
	$server= 'databases.000webhost.com';
	$database='id11809723_lpbus_bot';
	$user='id11809723_root';
	$password='123456789';

$conn = mysqli_connect($server,$user,$password,$database);

if (!$conn) {
    echo "Error: No se pudo conectar a MySQL.";
}else{
   $base = mysqli_select_db($database);
    if (!$base) {
        echo "Error: No se pudo conectar a la base de datos.";
    }
}

//

//mysqli_close($conn);
?>
