<?php
    include "connect.php";
    //tạo mảng hứng giá trị
    $mangspmoinhat = array();
    //câu lệnh móc dữ liệu từ dưới lên truyền vào 10000 san phẩm
    $query = "SELECT * FROM  sanpham ORDER BY ID DESC LIMIT 10000";
    //lấy dữ liệu ra
    $data = mysqli_query($conn,$query);
    //duyệt từng dòng dữ liệu
    while ($row = mysqli_fetch_assoc($data))
    {
        //mảng đẩy dữ liệu vào. truyền vào mảng+giá trị
        array_push($mangspmoinhat, new Sanphammoinhat(
            //tên cột
            $row['id'],
            $row['tensanpham'],
            $row['giasanpham'],
            $row['hinhanhsanpham'],
            $row['motasanpham'],
            $row['idsanpham']     
        ));
    }
    //đỗ về dữ liệu json(giá trị mảng)
    echo json_encode($mangspmoinhat);
    class Sanphammoinhat{
        function Sanphammoinhat($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham)
        {
            $this -> id = $id;
            $this -> tensp = $tensp;
            $this -> giasp = $giasp;
            $this -> hinhanhsp = $hinhanhsp;
            $this -> motasp = $motasp;
            $this -> idsanpham = $idsanpham;
        }
    }

?>