<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "thietbi";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("kết nối thất bại: " . $conn->connect_error);
} 

$Email = $_POST['email'];
$Matkhau = $_POST['matkhau'];
$sql = "UPDATE donhang SET matkhau = '".$Matkhau."' WHERE email = '".$Email."'";

if ($conn->query($sql) === TRUE) {
    echo "1";
} else {
    echo "0" . $conn->error;
}

$conn->close();
?>

