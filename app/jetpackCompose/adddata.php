<?php

	include 'conn.php';

	$id = $_POST['id'];
	$name = $_POST['name'];
	$description = $_POST['description'];
	
	$connect->query("INSERT INTO member_tb (id,name,description) 
	VALUES ('".$id."','".$name."','".$description."')")

?>