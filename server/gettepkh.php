<?php
    include "connect.php";
    $page = $_GET['page'];
    $idsp = 2;
    $space = 5;
    $limit = ($page -1 )*$space;
    $query = "SELECT*FROM chitietdonhang";
    $data = mysqli_query($conn,$query);
    $manglogin= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($manglogin,new Thongtin(
            $row['id'],
            $row['tenkhachhang'],
            $row['sdt'],
            $row['diachi'],
            $row['tensanpham'],
            $row['soluong'],
            $row['thantien']
            
            
        ));
    }
    echo json_encode($manglogin);
    class Thongtin{
        function Thongtin($id,$tenkhachhang,$sdt,$diachi
        ,$tensanpham,$soluong,$thantien){           
            $this->id=$id;
            $this ->tenkhachhang = $tenkhachhang;
            $this->sdt = $sdt;
            $this ->diachi = $diachi;
            $this ->tensanpham = $tensanpham;
            $this ->soluong=$soluong;
            $this ->thantien=$thantien;
        }
    }
?>