-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 03 Eyl 2021, 12:30:05
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `depo_project`
--

DELIMITER $$
--
-- Yordamlar
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `fullTextSearch` (IN `searchData` VARCHAR(50))  BEGIN
	#Routine body goes here...
	SELECT * FROM customer as cu
	WHERE MATCH (cu.cu_name,cu.cu_surname)AGAINST(searchData IN BOOLEAN MODE);

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admin`
--

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `admin`
--

INSERT INTO `admin` (`aid`, `email`, `name`, `password`) VALUES
(1, 'ali@mail.com', 'Ali Bilmem', '12345'),
(2, 'yusuf@mail.com', 'Yusuf Efe', '123');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `boxaction`
--

CREATE TABLE `boxaction` (
  `box_bid` int(11) NOT NULL,
  `box_rid` varchar(255) DEFAULT NULL,
  `customer_cu_id` int(11) DEFAULT NULL,
  `quantity_id_quantity_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `boxaction`
--

INSERT INTO `boxaction` (`box_bid`, `box_rid`, `customer_cu_id`, `quantity_id_quantity_id`) VALUES
(3, '337190281', 3, 3),
(4, '337688649', 18, 4);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `checkout`
--

CREATE TABLE `checkout` (
  `id` int(11) NOT NULL,
  `checkout_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `checkout`
--

INSERT INTO `checkout` (`id`, `checkout_amount`) VALUES
(1, 2004);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `cu_id` int(11) NOT NULL,
  `cu_address` varchar(500) DEFAULT NULL,
  `cu_code` bigint(20) NOT NULL,
  `cu_company_title` varchar(255) DEFAULT NULL,
  `cu_email` varchar(500) DEFAULT NULL,
  `cu_mobile` varchar(255) DEFAULT NULL,
  `cu_name` varchar(255) DEFAULT NULL,
  `cu_password` varchar(32) DEFAULT NULL,
  `cu_phone` varchar(255) DEFAULT NULL,
  `cu_status` int(11) NOT NULL,
  `cu_surname` varchar(255) DEFAULT NULL,
  `cu_tax_administration` varchar(255) DEFAULT NULL,
  `cu_tax_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`cu_id`, `cu_address`, `cu_code`, `cu_company_title`, `cu_email`, `cu_mobile`, `cu_name`, `cu_password`, `cu_phone`, `cu_status`, `cu_surname`, `cu_tax_administration`, `cu_tax_number`) VALUES
(1, '', 324234234, '', '', '436', 'Mustafa', '', '123123123', 1, 'Bilmez', '', 23423423),
(2, '', 379519179, '', '', '213213', 'Veli', '', '', 1, 'Bilmem', '', 123213),
(3, '', 379793359, '', '', '213123', 'Halil', '', '', 1, 'Bilmem', '', 213123),
(4, '', 442553966, '', '', '213213', 'Erkan', '', '213213', 1, 'Bilmem', '', 123123),
(15, '231432143', 459785407, 'Usta', 'murat@mail.com', '1231241234', 'Murat ', '541684984', '31423141', 2, 'Bilir', 'İzmir', 123151),
(16, 'Julius-Hatry-Straße, 68163 Mannheim - Lindenhof', 903709274, 'Usta', 'ali@mail.com', '213123', 'Ali', '234234', '6782176', 2, 'Bilir', 'İzmir', 1231243245),
(17, 'Julius-Hatry-Straße, 68163 Mannheim - Lindenhof', 904178318, 'Usta', 'halil@mail.com', '123123', 'Halil', '123123', '6782176', 1, 'Bilmez', 'İzmir', 2134),
(18, 'Julius-Hatry-Straße, 68163 Mannheim - Lindenhof', 905383159, 'Usta', 'erkan@mail.com', '213213', 'Erkan', '123213', '6782176', 1, 'Bilir', 'İzmir', 234234),
(19, 'Julius-Hatry-Straße, 68163 Mannheim - Lindenhof', 345357605, 'Mühendis', 'leyla@mail.com', '213123', 'Leyla', '123', '9254457', 2, 'Bilir', 'İzmir', 32145678);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `payin`
--

CREATE TABLE `payin` (
  `pay_id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `payment_amount` varchar(255) DEFAULT NULL,
  `payment_detail` varchar(255) DEFAULT NULL,
  `customer_cu_id` int(11) DEFAULT NULL,
  `receipt_re_rid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `payin`
--

INSERT INTO `payin` (`pay_id`, `date`, `payment_amount`, `payment_detail`, `customer_cu_id`, `receipt_re_rid`) VALUES
(1, '2021-09-01 20:10:57', '2', '2 TL Ödendi', 1, '448069776'),
(2, '2021-09-01 20:15:04', '3', '3 TL Ödendi', 2, '448094785'),
(3, '2021-09-01 20:25:24', '2', '2 TL Ödendi', 2, '448094785'),
(4, '2021-09-01 20:26:44', '2', '2 TL Ödendi', 2, '448094785'),
(5, '2021-09-01 20:27:36', '3', '3 TL Ödendi', 2, '448094785'),
(6, '2021-09-01 21:19:33', '2', '2 TL Ödendi', 2, '448094785'),
(7, '2021-09-01 21:26:45', '1', '1 TL Ödendi', 2, '448094785'),
(8, '2021-09-02 03:06:39', '2', '2 TL Ödendi', 2, '448094785'),
(9, '2021-09-02 03:10:37', '2', '2 TL Ödendi', 2, '448094785'),
(10, '2021-09-02 19:22:52', '3', '3 TL Ödendi', 2, '448094785'),
(11, '2021-09-02 19:24:14', '2', '2 TL Ödendi', 2, '448094785'),
(12, '2021-09-02 22:26:36', '5', '5 TL Ödendi', 19, '345423928');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `payout`
--

CREATE TABLE `payout` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `payOutDetail` varchar(255) DEFAULT NULL,
  `payOutTitle` varchar(255) DEFAULT NULL,
  `payOutTotal` varchar(255) DEFAULT NULL,
  `payOutType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `payout`
--

INSERT INTO `payout` (`id`, `date`, `payOutDetail`, `payOutTitle`, `payOutTotal`, `payOutType`) VALUES
(1, '2021-09-01 22:08:50', '5 TL Ödendi', 'Toptancı', '5', 0),
(3, '2021-09-01 22:44:48', '30 TL Ödendi', 'Toptancı', '30', 1),
(4, '2021-09-02 03:11:04', '3 TL Ödendi', 'Toptancı', '3', 0),
(5, '2021-09-02 19:34:27', '3 TL Ödendi', 'Toptancı', '3', 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `quantity`
--

CREATE TABLE `quantity` (
  `quantity_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `quantity`
--

INSERT INTO `quantity` (`quantity_id`, `quantity`) VALUES
(1, 5),
(2, 3),
(3, 2),
(4, 10),
(5, 10),
(6, 5),
(7, 7),
(8, 5),
(9, 10),
(10, 5),
(11, 10),
(12, 5),
(13, 2),
(14, 3),
(15, 5),
(16, 5),
(17, 10),
(18, 20),
(19, 30),
(20, 5),
(21, 2),
(22, 10),
(23, 5),
(24, 5),
(25, 5),
(26, 3),
(27, 5),
(28, 3),
(29, 2),
(30, 3),
(31, 2),
(32, 2),
(33, 2),
(34, 2),
(35, 1),
(36, 1),
(37, 2),
(38, 2),
(39, 1),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 2),
(45, 3),
(46, 5),
(47, 5),
(48, 2),
(49, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `quantity_stock`
--

CREATE TABLE `quantity_stock` (
  `Quantity_quantity_id` int(11) NOT NULL,
  `stocks_st_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `quantity_stock`
--

INSERT INTO `quantity_stock` (`Quantity_quantity_id`, `stocks_st_id`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 2),
(6, 2),
(7, 3),
(8, 1),
(9, 1),
(10, 2),
(11, 1),
(12, 2),
(13, 3),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 2),
(19, 3),
(20, 1),
(21, 2),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(26, 2),
(27, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1),
(33, 1),
(34, 1),
(35, 1),
(36, 1),
(37, 1),
(38, 1),
(39, 1),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 2),
(47, 1),
(48, 2),
(49, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `receipt`
--

CREATE TABLE `receipt` (
  `re_rid` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `repayment` varchar(255) DEFAULT NULL,
  `customer_cu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `receipt`
--

INSERT INTO `receipt` (`re_rid`, `active`, `amount`, `repayment`, `customer_cu_id`) VALUES
('345423928', b'1', '85', '5', 19),
('448069776', b'1', '15', '12', 1),
('448080922', b'0', '30', '30', 2),
('448094785', b'1', '37', '22', 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `stock`
--

CREATE TABLE `stock` (
  `st_id` int(11) NOT NULL,
  `st_code` bigint(20) NOT NULL,
  `st_detail` varchar(500) DEFAULT NULL,
  `st_purchase_price` varchar(255) DEFAULT NULL,
  `st_quantity` varchar(255) DEFAULT NULL,
  `st_sell_price` varchar(255) DEFAULT NULL,
  `st_tax_value` int(11) NOT NULL,
  `st_title` varchar(255) DEFAULT NULL,
  `st_unit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `stock`
--

INSERT INTO `stock` (`st_id`, `st_code`, `st_detail`, `st_purchase_price`, `st_quantity`, `st_sell_price`, `st_tax_value`, `st_title`, `st_unit`) VALUES
(1, 336866499, '', '3', '910', '5', 2, 'Su', 0),
(2, 336879099, '', '3', '458', '6', 2, 'Soğan', 1),
(3, 337168508, '', '10', '570', '20', 2, 'Yumurta', 0),
(4, 626645382, 'Yoğurt Detay', '4', '50', '15', 2, 'Yoğurt', 0);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`aid`);

--
-- Tablo için indeksler `boxaction`
--
ALTER TABLE `boxaction`
  ADD PRIMARY KEY (`box_bid`),
  ADD KEY `FK1d88p8eiub1i0f9p1efyubnal` (`customer_cu_id`),
  ADD KEY `FKtjxe5f7k2hflrgr3b7f465b7b` (`quantity_id_quantity_id`);

--
-- Tablo için indeksler `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cu_id`);
ALTER TABLE `customer` ADD FULLTEXT KEY `name_surname_fulltext` (`cu_name`,`cu_surname`);

--
-- Tablo için indeksler `payin`
--
ALTER TABLE `payin`
  ADD PRIMARY KEY (`pay_id`),
  ADD KEY `FK5qg3e3ki9j05s1biym5muhep0` (`customer_cu_id`),
  ADD KEY `FKi9xenmko8w7m3ttd7y916krwj` (`receipt_re_rid`);

--
-- Tablo için indeksler `payout`
--
ALTER TABLE `payout`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `quantity`
--
ALTER TABLE `quantity`
  ADD PRIMARY KEY (`quantity_id`);

--
-- Tablo için indeksler `quantity_stock`
--
ALTER TABLE `quantity_stock`
  ADD KEY `FKpp95g14x2iomeruo2pmy0k9qs` (`stocks_st_id`),
  ADD KEY `FKd2glux88lvqibdo0x03bh43y` (`Quantity_quantity_id`);

--
-- Tablo için indeksler `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`re_rid`),
  ADD KEY `FKjb3t17ekufii4foddysck3vpk` (`customer_cu_id`);

--
-- Tablo için indeksler `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`st_id`),
  ADD UNIQUE KEY `UK_lor6qapcdi3qhwcw93bl9xbn0` (`st_title`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `admin`
--
ALTER TABLE `admin`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `boxaction`
--
ALTER TABLE `boxaction`
  MODIFY `box_bid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `cu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Tablo için AUTO_INCREMENT değeri `payin`
--
ALTER TABLE `payin`
  MODIFY `pay_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `payout`
--
ALTER TABLE `payout`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `quantity`
--
ALTER TABLE `quantity`
  MODIFY `quantity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Tablo için AUTO_INCREMENT değeri `stock`
--
ALTER TABLE `stock`
  MODIFY `st_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `boxaction`
--
ALTER TABLE `boxaction`
  ADD CONSTRAINT `FK1d88p8eiub1i0f9p1efyubnal` FOREIGN KEY (`customer_cu_id`) REFERENCES `customer` (`cu_id`),
  ADD CONSTRAINT `FKtjxe5f7k2hflrgr3b7f465b7b` FOREIGN KEY (`quantity_id_quantity_id`) REFERENCES `quantity` (`quantity_id`);

--
-- Tablo kısıtlamaları `payin`
--
ALTER TABLE `payin`
  ADD CONSTRAINT `FK5qg3e3ki9j05s1biym5muhep0` FOREIGN KEY (`customer_cu_id`) REFERENCES `customer` (`cu_id`),
  ADD CONSTRAINT `FKi9xenmko8w7m3ttd7y916krwj` FOREIGN KEY (`receipt_re_rid`) REFERENCES `receipt` (`re_rid`);

--
-- Tablo kısıtlamaları `quantity_stock`
--
ALTER TABLE `quantity_stock`
  ADD CONSTRAINT `FKd2glux88lvqibdo0x03bh43y` FOREIGN KEY (`Quantity_quantity_id`) REFERENCES `quantity` (`quantity_id`),
  ADD CONSTRAINT `FKpp95g14x2iomeruo2pmy0k9qs` FOREIGN KEY (`stocks_st_id`) REFERENCES `stock` (`st_id`);

--
-- Tablo kısıtlamaları `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FKjb3t17ekufii4foddysck3vpk` FOREIGN KEY (`customer_cu_id`) REFERENCES `customer` (`cu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
