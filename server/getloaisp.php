<?php
    include "connect.php";
    $query = "SELECT*FROM loaisanpham";
    $data = mysqli_query($conn,$query);
    $mangloaisp= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($mangloaisp,new Loaisp(
            $row['id'],
            $row['tenloaisanpham'],
            $row['hinhanhloaisanpham']
        ));
    }
    echo json_encode($mangloaisp);
    class Loaisp{
        function Loaisp($id,$tenLoaisp,$hinhanhLoaisp){
            $this->id = $id;
            $this -> tenloaisp =$tenLoaisp;
            $this->hinhanhloaisp = $hinhanhLoaisp;
        }
    }
?>