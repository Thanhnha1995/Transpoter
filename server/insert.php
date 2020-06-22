<?php
    require "connect.php";

    $tensanpham = $_POST['tensanpham'];
    $giasanpham = $_POST['giasanpham'];
    $hinhanhsanpham = $_POST['hinhanhsanpham'];
    $motasanpham = $_POST['motasanpham'];
    $idsanpham = $_POST['idsanpham'];
    if(strlen($tensanpham)>0&&strlen($giasanpham)>0&&strlen($hinhanhsanpham)>0&&strlen($motasanpham)>0&&strlen($idsanpham)>0){
        $query ="INSERT INTO sanpham VALUES (null,'$tensanpham','$giasanpham','$hinhanhsanpham','$motasanpham','$idsanpham')";
        $data = mysqli_query($conn,$query);
        if($data){
            echo "Sccess";
        }else{
            echo "Fail";
        }
    }else{
        echo"Null";
    }
?>