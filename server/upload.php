<?php

    $file_path="hinhanh/";
    $file_path =$file_path.basename($_FILES['uploaded_file']['name']);

    if (move_upload_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
        echo $_FILES['uploaded_file']['name'];
    }else{
        echo "error";
    }
?>