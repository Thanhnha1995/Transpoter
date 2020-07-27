package com.example.admin.apps.ultil;

public class Server {
    public  static String nojs = "192.68.1.59:3000";
    public  static String http = "20.0.25.143:81";
    //loai sản phẩm
    public  static  String DuongdanLoaisp = "http://"+http+"/server/getloaisp.php";
    //lấy hết data
    public  static  String Duongdansanphammoinhat = "http://" + http+ "/server/getsanphammoinhat.php";
    //data điện thoại
    public  static  String Duongdandienthoai = "http://" + http+ "/server/getsanpham.php?page=";
    //data laptop
    public  static  String Duongdanlaptop = "http://" + http+ "/server/getsanphamlaptop.php?page=";
    //đăng ký tài khoản
    public  static  String Duongdanthongtinkh = "http://" + http+ "/server/thongtin.php";
    //đăng nhập tài khoản
    public  static  String Duongdangettk_kh = "http://" + http+ "/server/getthongtinkh.php";
    //hóa đơn
    public  static  String Duongdanchitietdonhang = "http://" + http+ "/server/chitietdonhang.php";
    //reset tài khoản khách hàng
    public  static  String Duongdanresettk_kh = "http://" + http+ "/server/sentrequest.php";
    //đưa giỏ hàng lên database
    public  static  String Duongdanpostdonhang = "http://" + http+ "/server/postthongtinsp_kh.php";
    //lấy sản phẩm search
    public  static  String Duongdantimkiemsp = "http://" + http+ "/server/getsearch.php";
    //lấy tài khoản dk
    public  static  String DuongdanAccount = "http://" + http+ "/server/getthongtinaccount.php";
    //send don hang
//    public  static  String DuongdanAccountDonHang = "http://" + http+ "server/getchitietdonhang.php";

    public  static  String DuongdanAccountDonHang = "http://" + http+  "/server/getthongdonhang.php";

    public  static  String Duongdantintuc = "http://" + http+  "/server/gettintuc.php";
}
