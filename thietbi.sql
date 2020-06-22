-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2019 at 07:41 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thietbi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_ad`
--

CREATE TABLE `admin_ad` (
  `id` int(11) NOT NULL,
  `dangnhap` varchar(2000) NOT NULL,
  `matkhau` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin_ad`
--

INSERT INTO `admin_ad` (`id`, `dangnhap`, `matkhau`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `sdt` int(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `soluong` int(11) NOT NULL,
  `thantien` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `tenkhachhang`, `sdt`, `diachi`, `tensanpham`, `soluong`, `thantien`) VALUES
(46, 'thanhnha', 123, '456', 'Apple iPhone 7 ', 1, 36470000),
(47, 'thanhnha', 123, '456', 'Sony Xperia XA1', 1, 4490000);

-- --------------------------------------------------------

--
-- Table structure for table `chitietsp`
--

CREATE TABLE `chitietsp` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(20000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietsp`
--

INSERT INTO `chitietsp` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 8, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(2, 9, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(3, 10, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(4, 11, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(5, 12, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(6, 13, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(7, 14, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(8, 15, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(9, 16, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(10, 17, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(11, 18, 17, 'Điện thoại Vivo Y85', 5990000, 1),
(12, 19, 16, 'Điện thoại Sony Xperia XZ Dual', 9990000, 1),
(13, 20, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(14, 1, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(15, 2, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1),
(16, 3, 19, 'Laptop HP Pavilion 14bf034TU i3 7100U', 11990000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(2000) NOT NULL,
  `sdt` int(11) NOT NULL,
  `email` varchar(2000) NOT NULL,
  `diachi` mediumtext NOT NULL,
  `matkhau` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sdt`, `email`, `diachi`, `matkhau`) VALUES
(6, 'thanhnha', 456, 'thanhnhadev@gmail.com', '123/4 hcm vietnam', '231');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Điện thoại', 'https://cdn.tgdd.vn/Products/Images/42/92962/iphone-6-32gb-gold-hh-400x400.jpg'),
(2, 'Laptop', 'https://media.wired.com/photos/59e952e4f572231fe56c3937/master/w_2500,c_limit/rosegold-macbook-1.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Apple iPhone 6', 7490000, 'https://cdn.tgdd.vn/Products/Images/42/92962/iphone-6-32gb-vang-400-400x460.png', 'iPhone 6 là một trong những smartphone được yêu thích nhất của Apple. Lắng nghe nhu cầu về thiết kế, khả năng lưu trữ và giá cả, iPhone 6 32GB được chính thức phân phối chính hãng tại Việt Nam hứa hẹn sẽ là một sản phẩm rất \"Hot\".\r\n', 1),
(2, 'Apple iPhone 7 ', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/74110/iphone-7-32gb-den-400x460.png', 'Âm thanh stereo phát ra từ 2 phía\r\nKhông còn khó chịu khi bạn cầm máy chơi game nhưng bị bít loa như ở những phiên bản trước đó.\r\n\r\nĐiện thoại iPhone 7 32 GB được trang bị dàn loa trên dưới cho âm thanh phát ra hay hơn.', 1),
(3, 'Apple Macbook Air ', 23990000, 'https://cdn.tgdd.vn/Products/Images/44/106875/apple-macbook-air-mqd32sa-a-i5-5350u-400-1-450x300-600x600.jpg', 'Macbook Air MQD32SA/A i5 5350U với thiết kế vỏ nhôm nguyên khối Unibody rất đẹp, chắc chắn và sang trọng. Macbook Air là một chiếc máy tính xách tay siêu mỏng nhẹ, hiệu năng ổn định mượt mà, thời lượng pin cực lâu, phục vụ tốt cho nhu cầu làm việc lẫn giải trí.\r\nThiết kế siêu mỏng và nhẹ', 2),
(4, ' Dell Vostro 5568 i5 7200U', 15490000, 'https://cdn.tgdd.vn/Products/Images/44/89025/dell-vostro-5568-077m52-vangdong-450x300-450x300.png', 'Được tích hợp chip xử lý core i5 Kaby Lake, tốc độ CPU 2.5 GHz và có thể tăng tốc lên tối đa 3.1 GHz nhờ tính năng Turbo Boost.\r\n\r\nRAM 4 GB và có thể nâng lên tối đa 16 GB, đặc biệt ổ cứng lưu trữ HDD lên đến 1 TB không gian lưu trữ thoải mái.', 2),
(5, 'Acer AspireA71572G ', 19990000, 'https://cdn.tgdd.vn/Products/Images/44/177954/acer-aspire-a715-72g-54pc-gxbsv003-4503-600x600.jpg', 'Laptop Aspire A715 72G 54PC đến từ thương hiệu Acer với Chip Core i5, thế hệ thứ 8 kết hợp card đồ họa rời NVIDIA GeForce GTX 1050, 4GB được tích hợp cho khả năng xử lý mượt mà các tác vụ đồ họa - kĩ thuật cũng như các tựa game có cấu hình khá. Đây sẽ là chiếc máy tính xách tay đáp ứng tốt nhu cầu 2 trong 1: đồ họa kĩ thuật và kể cả nhu cầu chơi game.\r\nThiết kế tối giản tinh tế', 2),
(6, 'Asus X540UB ', 10590000, 'https://cdn.tgdd.vn/Products/Images/44/169524/asus-x540ub-dm024t-thumb-600x600.jpg', 'Laptop Asus X540UB i3 6006U là mẫu máy tính xách tay có hiệu năng mạnh mẽ dành cho đồ họa - kỹ thuật với CPU thế hệ Skylake, card đồ họa rời Nvidia được tích hợp cũng giúp máy hoạt động mượt các tác vụ khác.\r\nThiết kế đơn giản\r\nLaptop Asus X540UB i3 6006U cũng như các mẫu máy tầm trung khác, máy có thiết kế bằng vỏ nhựa và kiểu dáng khá tinh tế phù hợp cho mọi lứa tuổi người dùng. Máy có độ dày 27.2 mm và nặng 2 kg nên mặc dù không quá nhẹ nhưng cũng thuận tiện khi di chuyển.', 2),
(7, 'Samsung Galaxy Note 9', 22990000, 'https://cdn.tgdd.vn/Products/Images/42/154897/samsung-galaxy-note-9-black-400x460-400x460.png', 'Mang lại sự cải tiến đặc biệt trong cây bút S-Pen, siêu phẩm Samsung Galaxy Note 9 còn sở hữu dung lượng pin khủng lên tới 4.000 mAh cùng hiệu năng mạnh mẽ vượt bậc, xứng đáng là một trong những chiếc điện thoại cao cấp nhất của Samsung.\r\nMột chút thay đổi trong thiết kế', 1),
(8, 'OPPO F9', 7690000, 'https://cdn.tgdd.vn/Products/Images/42/182153/oppo-f9-red-2-400x460.png', 'Là chiếc điện thoại OPPO mới nhất sở hữu công nghệ sạc VOOC đột phá, OPPO F9 còn được ưu ái nhiều tính năng nổi trội như thiết kế mặt lưng chuyển màu độc đáo, màn hình tràn viền giọt nước và camera chụp chân dung tích hợp trí tuệ nhân tạo A.I hoàn hảo.\r\nSạc VOOC nhanh đột phá\r\nTrong những giây phút gay cấn như chơi game điện thoại báo hết pin hay sáng dậy đi làm nhưng phát hiện quên sạc pin thì bộ sạc của OPPO F9 sẽ đem lại sự cứu trợ ngay lập tức.\r\n\r\nVới sạc VOOC 5V/AA, tốc độ trở sạc trở nên nhanh chóng so với các bộ sạc thông thường.', 1),
(9, 'Nokia 6.1Plus', 6590000, 'https://cdn.tgdd.vn/Products/Images/42/167150/nokia-61-plus-3-400x460.png', 'Nokia 6.1 Plus (hoặc tên khác Nokia X6) đã khiến người dùng trở nên phấn khích khi lột xác hoàn toàn trong thiết kế đến từ chiếc tai thỏ phá cách cũng như hiệu năng được cải tiến vượt bậc so với các đối thủ của nó.\r\nSự phá cách trong thiết kế', 1),
(10, 'Sony Xperia XZ2', 11990000, 'https://cdn.tgdd.vn/Products/Images/42/146014/sony-xperia-xz2-2-400x460.png', 'Xperia XZ2 là chiếc flagship mới được Sony giới thiệu tại MWC 2018 với sự thay đổi lớn về thiết kế so với những người tiền nhiệm.\r\nLột xác về thiết kế\r\nVới điện thoại Xperia XZ2, ngôn ngữ thiết kế \"Omni Balance\" trứ danh một thời của Sony đã đi vào dĩ vãng để nhường chỗ cho một thiết kế mới mẻ và đột phá hơn hẳn', 1),
(11, 'Hp bs647TU', 10690000, 'https://cdn.tgdd.vn/Products/Images/44/158020/hp-15-bs647tu-i3-6006u-3mr94pa-450-300a-600x600.jpg', 'Laptop HP 15 bs647TU i3 6006U là chiếc laptop có màn hình 15.4 inch độ phân giải Full HD phù hợp cho người dùng cần một chiếc laptop để học tập, làm việc, giải trí. Kết hợp cùng vi xử lý core i3 6006U và 4 GB DDR4 máy có thể đáp ứng các nhu cầu cơ bản một cách trơn tru.\r\nThiết kế tinh tế hiện đại\r\nMáy vẫn mang thiết kế truyền thống của các mẫu laptop phổ thông khác với phần khung nhựa cứng cáp, bền bỉ. Tuy nhiên HP cũng khéo léo mang lại vẻ cao cấp cho chiếc laptop của mình bằng việc sử dụng màu sắc vàng đồng bắt mắt, phần kê tay được in vân cách điệu giúp chiếc laptop trông đẹp và thu hút ', 2),
(12, 'Lenovo IdeaPad ', 9790000, 'https://cdn.tgdd.vn/Products/Images/44/187012/lenovo-ideapad-130-14ikb-81h60017vn-ava-600x600.jpg', 'Laptop Lenovo IdeaPad 130 14IKB có cấu hình ở mức khá với hệ điều hành Windows 10 bản quyền, chip Intel Core i3 thế hệ thứ 7, 4 GB RAM cùng ổ cứng lưu trữ HDD 1 TB, cho hiệu năng hoạt động ổn định đối với các tác vụ cơ bản như soạn thảo văn bản, nhập liệu, học anh văn, làm bài thuyết trình...Đây sẽ là chiếc máy tính phù hợp với đối tượng người dùng như nhân viên văn phòng, học sinh - sinh viên.\r\nMàn hình 14 nhỏ gọn\r\nIdeaPad 130 14IKB có màn hình với kích thước nhỏ gọn 14 inch với độ phân giải HD kết hợp cùng card đồ họa tích hợp Intel® HD Graphics 620 mang đến sự rõ nét, chân thực trong chất lượng hình ảnh hiển thị.', 2),
(13, 'Lenovo Yoga', 11690000, 'https://cdn.tgdd.vn/Products/Images/44/139279/lenovo-yoga-520-14ikb-i3-7130u-8080106vn-450x300.jpg', 'Máy tính xách tay Lenovo IdeaPad Yoga 520 là mẫu máy tính thuộc dòng phân khúc mỏng nhẹ của thương hiệu laptop Lenovo. Máy có thiết kế hiện đại cùng một cấu hình thế hệ mới và với giá thành khá hợp lý, phù hợp với nhu cầu giải trí hay làm việc.\r\nThiết kế sang trọng\r\nLenovo IdeaPad này là mẫu máy tính giá thành hợp lý tiếp cận với nhiều người, cho nên máy thiết kế trang nhã và đầy mới mẽ, máy dùng chất liệu vỏ nhựa với độ mỏng 19.9 mm ấn tượng. Thích hợp cho những ai mang máy di chuyển nhiều nơi.', 2),
(14, 'Asus X510UA', 13290000, 'https://cdn.tgdd.vn/Products/Images/44/135191/asus-x510ua-i5-8250u-br543t-dai-dien-1-450x300.jpg', 'Asus X510UA i5 8250U là kiểu máy tính xách tay có thiết kế mỏng nhẹ, hiện đại và được trang bị cấu hình CPU thế hệ thứ 8 của Intel cho khả năng tiết kiệm điện tốt. Qua hai đặc điểm nổi bật trên thì có thể thấy được laptop Asus này là dòng máy định hướng phục vụ cho người dùng thường mang máy di chuyển nhiều nơi.\r\nThiết kế máy hiện đại, tinh tế\r\nAsus X510UA i5 8250U có thiết kế chất liệu vỏ nhựa nhưng nhìn khá giống với vỏ kim loại sang trọng, cao cấp. Máy có trọng lượng 1.5 kg cùng độ dày 19.4 mm mà thôi, rất dễ để mang theo mọi lúc mọi nơi. ', 2),
(15, 'Sony Xperia XZ1', 8990000, 'https://cdn.tgdd.vn/Products/Images/42/108680/sony-xperia-xz1-xanh-2-400x460.png', 'Sony Xperia XZ1 là mẫu flagship kế tiếp của Sony tiếp nối sự thành công của chiếc Xperia XZs đã ra mắt trước đó với những nâng cấp nhẹ về mặt cấu hình và thiết kế.\r\nThiết kế quen thuộc\r\nĐiện thoại Sony Xperia XZ1 vẫn mang trong mình ngôn ngữ thiết kế quen thuộc của Sony trong những năm gần đây với các góc cạnh vuông vức, nam tính.', 1),
(16, 'Sony Xperia XZ Dual', 9990000, 'https://cdn.tgdd.vn/Products/Images/42/85235/sony-xperia-xz-h-1-400x460.png', 'Sony Xperia XZ Dual với thiết kế cực đẹp, cùng camera chất lượng hơn, nhiều tính năng tiện ích hơn.\r\nThiết kế sang trọng\r\nCải tiến hơn so với thiết kế truyền thống của dòng điện thoại Sony, Xperia XZ mang trên mình diện mạo mới - đẹp hơn, sang trọng hơn.', 1),
(17, 'Vivo Y85', 5990000, 'https://cdn.tgdd.vn/Products/Images/42/156205/vivo-y85-red-docquyen-400x460.png', 'Vivo Y85 là chiếc smartphone tầm trung mới của Vivo với thiết kế “tai thỏ”, camera kép đặt dọc ở mặt lưng.\r\nThiết kế theo xu thế\r\nĐầu năm 2018 dường như là thời gian ra mắt của một loạt smartphone thiết kế “tai thỏ”, và Vivo Y85 cũng không phải ngoại lệ.', 1),
(18, 'Sony Xperia XA1', 4490000, 'https://cdn.tgdd.vn/Products/Images/42/145451/sony-xperia-xa1-ultra-pink-2-400x460.png', 'Sau một thời gian xuất hiện tại Việt Nam và nhận được nhiều sự quan tâm từ người dùng thì mới đây Sony đã tung ra phiên bản màu hồng cho chiếc Sony Xperia XA1 Ultra để phục vụ riêng cho \"phái đẹp\".\r\nThiết kế quyến rũ\r\nĐiện thoại Sony Xperia XA1 Ultra Pink vẫn duy trì thiết kế rất \"sexy\" của dòng XA với 2 cạnh viền màn hình 2 bên siêu mỏng.', 1),
(19, 'HP Pavilion 14bf034TU', 11990000, 'https://cdn.tgdd.vn/Products/Images/44/156178/hp-pavilion-14-bf034tu-i3-7100u-3ms06pa-450-300-450x300-450x300-600x600.jpg', 'Pavilion 14 bf034TU i3 (3MS06PA) đến từ thương hiệu HP sở hữu cấu hình vừa đủ cùng mức giá bán hấp dẫn sẽ là chiếc máy tính xách tay phù hợp với học sinh, sinh viên hay những người thường xuyên sử dụng với những ứng dụng không quá nặng.\r\nCấu hình i3 thế hệ 7 ổn định\r\nMẫu laptop HP Core i3 sử dụng chip thế hệ thứ 7 mang lại hiệu năng hoạt động khá ổn định và có thể chơi các tựa game online khá ổn. Tiếp đó là RAM DDR4 4 GB hiệu quả và có hiệu suất hoạt động ổn định, cùng ổ cứng HDD 1 TB có thể lưu trữ nhiều dữ liệu cá nhân.', 2),
(20, 'Apple Macbook Pro ', 44490000, 'https://cdn.tgdd.vn/Products/Images/44/184384/apple-macbook-pro-touch-mr9q2sa-a-2018-thumb-1-600x600.jpg', 'Apple đã cho ra mắt phiên bản Macbook Pro Touch 2018 vẫn là vẻ ngoài sang trọng, thiết kế Unibody quen thuộc của dòng máy tính xách tay cao cấp đến từ thương hiệu \"Táo Khuyết\". Sự khác biệt của Macbook Pro Touch so với phiên bản cũ phải nói đến sự nâng cấp cũng như có nhiều cải tiến mới mẻ đến từ bên trong với một cấu hình mạnh mẽ hơn.\r\nThiết kế vỏ nhôm nguyên khối cao cấp\r\nLà một chiếc máy tính thuộc dòng sản phẩm cao đến từ Apple, Macbook Pro Touch MR9Q2SA được trang bị lớp vỏ nhôm nguyên khối Unibody sang trọng, tinh tế và chắc chắn, bên cạnh đó máy với trọng lượng chỉ 1.37 kg, người dùng có thể mang máy theo sử dụng mọi lúc mọi nơi.', 2),
(21, 'Apple iPhone 7 ', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/74110/iphone-7-32gb-den-400x460.png', 'Âm thanh stereo phát ra từ 2 phía\r\nKhông còn khó chịu khi bạn cầm máy chơi game nhưng bị bít loa như ở những phiên bản trước đó.\r\n\r\nĐiện thoại iPhone 7 32 GB được trang bị dàn loa trên dưới cho âm thanh phát ra hay hơn.', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_ad`
--
ALTER TABLE `admin_ad`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chitietsp`
--
ALTER TABLE `chitietsp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_ad`
--
ALTER TABLE `admin_ad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `chitietsp`
--
ALTER TABLE `chitietsp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
