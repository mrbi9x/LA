-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.72-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema manageuser_nguyenvanminh
--

CREATE DATABASE IF NOT EXISTS manageuser_nguyenvanminh;
USE manageuser_nguyenvanminh;

--
-- Temporary table structure for view `view_user_infor`
--
DROP TABLE IF EXISTS `view_user_infor`;
DROP VIEW IF EXISTS `view_user_infor`;
CREATE TABLE `view_user_infor` (
  `user_id` int(11),
  `group_id` int(11),
  `group_name` varchar(255),
  `login_name` varchar(15),
  `full_name` varchar(255),
  `full_name_kana` varchar(255),
  `email` varchar(255),
  `tel` varchar(15),
  `birthday` date,
  `code_level` varchar(15),
  `name_level` varchar(255),
  `start_date` date,
  `end_date` date,
  `total` int(11)
);

--
-- Definition of table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
CREATE TABLE `mst_group` (
  `group_id` int(15) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mst_group`
--

/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` (`group_id`,`group_name`) VALUES 
 (1,'Group 1'),
 (2,'Group 2'),
 (3,'Group 3'),
 (4,'Group 4');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;


--
-- Definition of table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) NOT NULL,
  `name_level` varchar(255) NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mst_japan`
--

/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` (`code_level`,`name_level`) VALUES 
 ('N1','Level 1'),
 ('N2','Level 2'),
 ('N3','Level 3'),
 ('N4','Level 4'),
 ('N5','Level 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code_level` varchar(15) CHARACTER SET latin1 NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  KEY `code_level` (`code_level`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_2` FOREIGN KEY (`code_level`) REFERENCES `mst_japan` (`code_level`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_detail_user_japan`
--

/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` (`detail_user_japan_id`,`user_id`,`code_level`,`start_date`,`end_date`,`total`) VALUES 
 (1,1,'N4','2016-06-13','2016-06-14',285),
 (6,26,'N5','2016-01-12','2017-06-30',600),
 (11,31,'N4','2016-06-29','2017-06-29',999),
 (12,32,'N1','2016-06-29','2017-06-29',25),
 (13,33,'N2','2016-06-29','2017-06-29',900),
 (14,36,'N1','2016-06-29','2017-06-29',223),
 (16,44,'N1','2016-07-06','2017-07-06',2),
 (17,43,'N5','2016-07-06','2017-07-06',236),
 (19,45,'N3','2016-07-06','2017-07-06',999),
 (20,46,'N3','2016-07-08','2017-07-08',6969),
 (21,47,'N1','2016-07-12','2017-07-12',456),
 (24,38,'N1','2016-07-13','2017-07-13',123);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_report`
--

DROP TABLE IF EXISTS `tbl_report`;
CREATE TABLE `tbl_report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `total_user` int(11) NOT NULL,
  `total_user_n` int(11) NOT NULL,
  `total_user_n0` int(11) DEFAULT NULL,
  `total_user_n1` int(11) NOT NULL,
  `total_user_n2` int(11) NOT NULL,
  `total_user_n3` int(11) NOT NULL,
  `total_user_n4` int(11) NOT NULL,
  `total_user_n5` int(11) NOT NULL,
  `date_report` date NOT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_report`
--

/*!40000 ALTER TABLE `tbl_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_report` ENABLE KEYS */;


--
-- Definition of table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `login_name` varchar(15) CHARACTER SET latin1 NOT NULL,
  `pass` varchar(50) CHARACTER SET latin1 NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `full_name_kana` varchar(255) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 NOT NULL,
  `tel` varchar(15) CHARACTER SET latin1 NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_user`
--

/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`pass`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`) VALUES 
 (1,1,'nguyenle','ABCD','Nguyễn Thị Lệ','','nguyenle0987666@gmail.com','1-1-1','1994-04-27'),
 (18,3,'LoginName001','11111','FullNameTest1','カタカナカタカカタカナカタカカタカナカタカ','emailte.Ast@luvina.net','0164-223-1399','2016-06-28'),
 (19,2,'bcde5Te','111111','nguyễn','','nguyenle09287666@gmail.com','098-4561-7899','1999-01-12'),
 (20,2,'bcde5Te2','111111','nguyễn','アミニ','nguyenle09222287666@gmail.com','098-4561-7899','1999-01-12'),
 (24,3,'SQLExc1','1508416','SQLExc1Edit','アミニ','SQLExc1@gmail.com','098-4561-7899','1981-04-12'),
 (25,1,'test001','1111','test001','アミニ','test001@gmail.com','098-4561-7899','1999-03-13'),
 (26,3,'jquertet00','1111','jquertet00','アミニ','jquertet00@gmail.com','098-4561-7899','1999-03-13'),
 (31,1,'test0002','1111','test0002','アミニ','test0002@aa.aa','123-4561-7899','1998-02-13'),
 (32,1,'nguyenle001___','46760945','nguyenle0013222','ア ミ ニ','EmailExited@email.email','098-4561-7899','1999-02-27'),
 (33,1,'abababat','1111','tagaga','','nguyenl222e09287666@gmail.com','098-4561-7899','1998-06-29'),
 (34,4,'aaag','1111','gagag','','gagagaga@aa.aa','098-4561-7899','1998-06-29'),
 (35,2,'testErr','1111','testErr','','minhtestErr@gmail.com','123-4561-7899','1999-06-29'),
 (36,2,'lelt','ABCABC','Leej Roi','','lelt@luvina.net','123-4561-7899','1998-06-29'),
 (38,1,'asfgdfg','111111','asdfgsfdfs','','fdsafasdfds@gmail.com','123-4561-7899','1997-06-30'),
 (43,2,'update001','1508416','update001','','update001@gmail.com','1-1-1','1998-07-06'),
 (44,2,'update002','1508416','update002','','update002@gmail.com','1111-1111-1111','2016-07-06'),
 (45,1,'testADd1','1508416','testADd1','カタカナ','testADd1@aa.aa','123-4561-7899','1998-07-06'),
 (46,2,'testHtml','46760945','<html><font color = \"red\">Data test</font></html>','','testHtml@luvina.net','1-1-1','1999-03-04'),
 (47,2,'trantrungtest','96e79218965eb72c92a549dd5a330112','trantrungtest','カタカナ','trantrungtest@luvina.net','1234-1234-1234','1999-07-12'),
 (48,3,'<html></html>','1234546','<html><font color = \"red\">Data test</font></html>','<html><font color = \"red\">Data test</font></html>','<html><font color = \"red\">Data test</font></html>','<html></html>','1999-03-04'),
 (49,1,'fadsfasd','1508416','testUpdate1','カタカナ','testUpdate1@luvina.net','1234-1234-1234','1999-03-04'),
 (50,3,'ahiby12','46760945','ahiby12','','ahiby112@gmail.com','123-4561-7899','1998-07-13'),
 (51,2,'123abc','46760945','ahiby12123',NULL,'ahiby12@gmail.com','123-4561-7899','1998-07-13'),
 (52,2,'ntm','46760945','a123hiby12123',NULL,'ahib123y12@gmail.com','123-4561-7899','1998-07-13');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;


--
-- Definition of view `view_user_infor`
--

DROP TABLE IF EXISTS `view_user_infor`;
DROP VIEW IF EXISTS `view_user_infor`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_user_infor` AS select `tbl_user`.`user_id` AS `user_id`,`tbl_user`.`group_id` AS `group_id`,`mst_group`.`group_name` AS `group_name`,`tbl_user`.`login_name` AS `login_name`,`tbl_user`.`full_name` AS `full_name`,`tbl_user`.`full_name_kana` AS `full_name_kana`,`tbl_user`.`email` AS `email`,`tbl_user`.`tel` AS `tel`,`tbl_user`.`birthday` AS `birthday`,`tbl_detail_user_japan`.`code_level` AS `code_level`,`mst_japan`.`name_level` AS `name_level`,`tbl_detail_user_japan`.`start_date` AS `start_date`,`tbl_detail_user_japan`.`end_date` AS `end_date`,`tbl_detail_user_japan`.`total` AS `total` from ((`tbl_user` join `mst_group` on((`tbl_user`.`group_id` = `mst_group`.`group_id`))) left join (`tbl_detail_user_japan` join `mst_japan` on((`tbl_detail_user_japan`.`code_level` = `mst_japan`.`code_level`))) on((`tbl_user`.`user_id` = `tbl_detail_user_japan`.`user_id`)));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
