<?php
    require "connect.php";
   $dangnhap = "admin";
   $matkhau = "admin";
   class Cuahang{
       function Cuahang($id,$dangnhap,$matkhau){
            $this ->Id =$id;
            $this ->dangnhap =$dangnhap;
            $this ->matkhau =$matkhau;
       }
   }
   if(strlen($dangnhap)>0&& strlen($matkhau)>0){
       $Arraycuahang = array();
       $query ="SELECT *FROM admin_ad WHERE FIND_IN_SET('$dangnhap',dangnhap) AND FIND_IN_SET('$matkhau',matkhau)";
       $data = mysqli_query($conn,$query);
       if($data){
            while ($row = mysqli_fetch_assoc($data)) {
               array_push($Arraycuahang, new Cuahang($row['id'],
                                                        $row['dangnhap'],
                                                        $row['matkhau']));
            }
            if(count($Arraycuahang)>0){
                echo json_encode($Arraycuahang);
            }else{
                echo "Fail";
            }
       }
   }else{
       echo "Null";
   }


// $username = $_POST['dangnhap'];
// $password = $_POST['matkhau'];

// $con=mysqli_connect($conn,$sql);
// $sql=mysqli_prepare($con,"SELECT * FROM admin_ad WHERE  UserName =".$username." AND Password=?");
// mysqli_stmt_bind_param($sql,"ss",$username,$password);
// mysqli_stmt_execute($sql);
// mysqli_stmt_store_result($sql);
// mysqli_stmt_bind_result($sql,$id,$username,$password);
// $response=array();
// $response["sucess"]=false;
// while(mysqli_stmt_fetch($sql))
// {
//     $response["sucess"]=true;
//     $response["ID"]=$id;        //   $result="true"; 
// }  


// // send result back to android
// echo json_encode($response);
?>
