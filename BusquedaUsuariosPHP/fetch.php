<?php
//fetch.php
$conn = mysqli_connect("localhost", "root", "root", "DAW");
$output = '';
if(isset($_POST["query"]))
{
    $search = mysqli_real_escape_string($conn, $_POST["query"]);
    $query = "
  SELECT u.id, u.nombre, u.apellido, u.email, u.username, u.numLogin, u.estatusCuenta, u.tipoUsuario, u.primerLogin
 FROM User u
  WHERE  u.nombre LIKE '%".$search."%' OR u.apellido LIKE '%".$search."%' 
 ";

}
else
{
    $query = "
SELECT u.id, u.nombre, u.apellido, u.email, u.username, u.numLogin, u.estatusCuenta, u.tipoUsuario, u.primerLogin
 FROM User u
 ";
}
$result = mysqli_query($conn, $query);
if(mysqli_num_rows($result) > 0)
{
    $output .= '
  <div class="table-responsive">
   <table id="tbProductos">
    <tr>
     <th>Id Usuario</th>
     <th>Nombre</th>
     <th>Apellido</th>
     <th>Correo</th>
     <th>Username</th>
     <th>Cantidad de Logins</th>
     <th>Estatus Cuenta</th>
     <th>Tipo Cuenta</th>
     <th>Primer Login?</th>
    </tr>
 ';
    while($row = mysqli_fetch_array($result))
    {
        $output .= '
   <tr>
    <td>'.$row["id"].'</td>
    <td>'.$row["nombre"].'</td>
    <td>'.$row["apellido"].'</td>
    <td>'.$row["email"].'</td>
    <td>'.$row["username"].'</td>
    <td>'.$row["numLogin"].'</td>
    <td>'.$row["estatusCuenta"].'</td>
    <td>'.$row["tipoUsuario"].'</td>
    <td>'.$row["primerLogin"].'</td>
   </tr>
  ';
    }
    echo $output;
}
else
{
    echo 'No se encontro ningun dato';
}

?>