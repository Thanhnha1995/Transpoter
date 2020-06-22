<?php
    include "connect.php";
    $query = "SELECT*FROM sukien";
    $data = mysqli_query($conn,$query);
    $manglogin= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($manglogin,new Thongtin(
            $row['id'],
            $row['tensukien'],
            $row['hinhanhsukien'],
            $row['noidungsukien']   
        ));
    }
    echo json_encode($manglogin);
    class Thongtin{
        function Thongtin($id,$tensanpham,$hinhanhsukien,$noidungsukien){           
            $this ->id = $id;
            $this->tensanpham = $tensanpham;
            $this ->hinhanhsukien = $hinhanhsukien;
            $this->noidungsukien = $noidungsukien;
        }
    }
?>