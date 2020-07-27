<!-- thong tin khách dang ky -->
<?php
    include "connect.php";
    $query = "SELECT*FROM donhang";
    $data = mysqli_query($conn,$query);
    $manglogin= array();
    while($row = mysqli_fetch_assoc($data)){
        array_push($manglogin,new Thongtin(
            $row['tenkhachhang'],
            $row['sdt'],
            $row['email'],
            $row['diachi'],
            $row['matkhau']
        ));
    }
    echo json_encode($manglogin);
    class Thongtin{
        function Thongtin($tenkhachhang,$sdt,$email,$diachi,$matkhau){   
            $this ->tenkhachhang = $tenkhachhang;
            $this->sdt = $sdt;
            $this ->email = $email;   
            $this ->diachi = $diachi;
            $this->matkhau = $matkhau;
        }
    }
?>

<!-- UPDATE donhang SET matkhau = "1345456s1" WHERE email = "thanhnhadev@gmail.com" -->