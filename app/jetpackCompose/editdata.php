<?php

	include 'conn.php';
	
	$id = $_POST['id'];
	$name = $_POST['name'];
	$description = $_POST['description'];

	$connect->query("UPDATE member_tb SET id='".$id."', name='".$name."', description='".$description."' WHERE id=". $id);

?>