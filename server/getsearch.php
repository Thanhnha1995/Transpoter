<?php
    include "connect.php";
    $query = "SELECT*FROM sanpham";
    $data = mysqli_query($conn,$query);
    $manglogin= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($manglogin,new Thongtin(
            
            $row['tensanpham'],
            $row['giasanpham'],
            $row['hinhanhsanpham'],
            $row['motasanpham'],
            $row['idsanpham']
            
        ));
    }
    echo json_encode($manglogin);
    class Thongtin{
        function Thongtin($tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idsanpham){           
            $this ->tensanpham = $tensanpham;
            $this->giasanpham = $giasanpham;
            $this ->hinhanhsanpham = $hinhanhsanpham;
            $this->motasanpham = $motasanpham;
            $this ->idsanpham = $idsanpham;
            
        }
    }
?>