<?php
//đưa dữ liệu khách hàng đăng ký tài khoản
    include "connect.php";
    $tenkhachhang= $_POST['tenkhachhang'];
    $email =  $_POST['email'];
    $diachi = $_POST['diachi'];
    $matkhau = $_POST['matkhau'];
    $sdt = $_POST['sdt'];
    if (strlen($tenkhachhang)>0 && strlen($email)>0 && strlen($sdt)>0 && strlen($diachi)>0 && strlen($matkhau)>0 ) 
    {
        $query = "INSERT INTO donhang(id,tenkhachhang,email,diachi,matkhau,sdt)
        VALUES(null,'$tenkhachhang','$email','$diachi','$matkhau','$sdt')";
        if (mysqli_query($conn,$query)) {
            $iddonhang = $conn->insert_id;
            echo $iddonhang;
        }else{
            echo"thất bại";
        }
    }else{
        echo"Bạn hãy kiểm tra lại dữ liệu";
    }
?>