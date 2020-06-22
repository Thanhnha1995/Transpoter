<?php
    include "connect.php";
    $query = "SELECT*FROM chitietdonhang";
    $data = mysqli_query($conn,$query);
    $manglogin= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($manglogin,new Thongtin(
            $row['id'],
            $row['tenkhachhang'],
            $row['sdt'],
            $row['tensanpham'],
            $row['thantien'],
            $row['soluong'], 
            $row['diachi']
        ));
    }
    echo json_encode($manglogin);
    class Thongtin{
        function Thongtin($id,$tenkhachhang,$sdt,$tensanpham,$thantien,$soluong,$diachi){           
            $this ->id = $id;
            $this->tenkhachhang = $tenkhachhang;
            $this ->sdt = $sdt;
            $this->tensanpham = $tensanpham;
            $this ->thantien = $thantien;
            $this ->soluong = $soluong;
            $this ->diachi = $diachi;
        }
    }
?>