<?php
	include 'conn.php';
	$id=$_POST['id'];
	$connect->query("DELETE FROM member_tb WHERE id=".$id);

?>