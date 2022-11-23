<?php
include 'conn.php';

$queryResult=$connect->query("SELECT * FROM member_tb");

$result=array();

while($fetchData=$queryResult->fetch_assoc()){
	$result[]=$fetchData;
	
}
$myJSON = json_encode($result);

echo ($myJSON);
?>
