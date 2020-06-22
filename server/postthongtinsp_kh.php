<?php
//dua du lieu gio hang
    include "connect.php";
    // $tenkhachhang = $_POST['tenkhachhang'];
    // $sdt = $_POST['sdt'];
    // $diachi = $_POST['diachi'];
    $json =  $_POST['json'];
    $data = json_decode($json,true);
    foreach($data as $value){
        $tenkhachhang = $value['tenkhachhang'];
        $sdt = $value['sdt'];
        $diachi = $value['diachi'];
        $tensanpham = $value['tensanpham'];
        $soluong = $value['soluong'];
        $thantien = $value['thantien'];
        $query = "INSERT INTO chitietdonhang(id,tenkhachhang,sdt,diachi,tensanpham,soluong,thantien)
    VALUES(null,'$tenkhachhang','$sdt','$diachi','$tensanpham','$soluong','$thantien')";
    $Dta = mysqli_query($conn, $query);
    }
// $tenkhachhang = "ffb";
//     $sdt = "bfbfbfb3r2";
//     $diachi = "gsgsg";
//         $tensanpham = "dfdvdv";
//         $soluong = "3";
//         $thantien = "3313203";
    if($Dta){
        echo "1";
    } else{
        echo "0";
    }
?>