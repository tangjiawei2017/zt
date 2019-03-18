-- MySQL dump 10.14  Distrib 5.5.56-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.5.56-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `T_ACCOUNT`
--

DROP TABLE IF EXISTS `T_ACCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(30) NOT NULL,
  `PASSWORD` char(32) NOT NULL,
  `ROLE` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ACCOUNT`
--

LOCK TABLES `T_ACCOUNT` WRITE;
/*!40000 ALTER TABLE `T_ACCOUNT` DISABLE KEYS */;
INSERT INTO `T_ACCOUNT` VALUES (1,'zshop','96e79218965eb72c92a549dd5a330112','ROLE_ADMIN');
/*!40000 ALTER TABLE `T_ACCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ADVISE`
--

DROP TABLE IF EXISTS `T_ADVISE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ADVISE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAYERID` int(11) NOT NULL,
  `ACCOUNT` varchar(50) NOT NULL,
  `PHONE` char(11) DEFAULT NULL,
  `CONTENT` text NOT NULL,
  `CREATETIME` datetime NOT NULL,
  `ANSWER` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ADVISE`
--

LOCK TABLES `T_ADVISE` WRITE;
/*!40000 ALTER TABLE `T_ADVISE` DISABLE KEYS */;
INSERT INTO `T_ADVISE` VALUES (1,38,'tangjiawei','18810594763','还行吧','2018-01-07 21:36:11','adfafsd'),(2,38,'tangjiawei','18810594763','热闹的终会离场，明艳的也会归于沉寂。你看，岁月的飞雪将心事覆盖成眠，而喧嚣也开始变的郑重其事。\n我们都是在最深最美的时空里流浪着，静静的行走，静静的感悟，静静的与光阴相依，拂去一世烟尘，只为值得的爱坚守。张小娴说：“有些思念，怎么也放不下；有些爱，怎么也断不了。人生的各种羁绊，都不过是斩断旧的，又有新的。”','2018-01-07 22:22:03','热闹的终会离场，明艳的也会归于沉寂。你看，岁月的飞雪将心事覆盖成眠，而喧嚣也开始变的郑重其事。 我们都是在最深最美的时空里流浪着，静静的行走，静静的感悟，静静的与光阴相依，拂去一世烟尘，只为值得的爱坚守。张小娴说：“有些思念，怎么也放不下；有些爱');
/*!40000 ALTER TABLE `T_ADVISE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_CUSTOMER`
--

DROP TABLE IF EXISTS `T_CUSTOMER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_CUSTOMER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `SEX` tinyint(1) DEFAULT '0',
  `IMAGE_PATH` varchar(500) DEFAULT NULL,
  `CELLPHONE` char(11) DEFAULT NULL,
  `EMAIL` varchar(20) DEFAULT NULL,
  `REGTIME` datetime NOT NULL,
  `STATUS` tinyint(1) NOT NULL DEFAULT '1',
  `SOURCE` tinyint(4) DEFAULT '0',
  `LOGINCOUNT` int(11) DEFAULT '0',
  `LOGINTIME` datetime DEFAULT NULL,
  `WXID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1929 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_CUSTOMER`
--

LOCK TABLES `T_CUSTOMER` WRITE;
/*!40000 ALTER TABLE `T_CUSTOMER` DISABLE KEYS */;
INSERT INTO `T_CUSTOMER` VALUES (39,'李良',NULL,NULL,0,'http://wx.qlogo.cn/mmopen/vi_32/pDkzKWNmQick2VaETEyqIwByN7RIGk6wMocRia8H9eOfzuCSZ4p7gicz521AjM55icmT9icic2hI4H9HXHibthfMRzK0g/0',NULL,NULL,'2017-12-27 23:49:01',1,0,1,'2017-12-27 23:49:01',19),(40,'angelina','4b68af8f3b4d7da154df470a40f2bf28',NULL,0,NULL,'16510101010',NULL,'2017-12-28 05:21:03',0,0,7814,'2018-01-04 06:20:31',NULL),(1914,'黄',NULL,NULL,0,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJzZ2mgIibI3jOfSV5DjmGJZr0eKLqXPTFXZA9FBFRdJAwJAiaV3ichib8QxXC4f7pjcMicJx7rvxOlBw/0',NULL,NULL,'2018-01-02 11:37:14',1,0,1,'2018-01-02 11:37:14',20),(1921,'杨 迪',NULL,NULL,0,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJBY8JpLHboHg8cCycAeBu7W7yTH6JOLuCAfOPR2sOAGpja9eneQKhnxVeykXic54XozS1V1WIILDg/0',NULL,NULL,'2018-01-03 16:26:45',1,0,1,'2018-01-03 16:26:45',21),(1926,'tangjiawei','96e79218965eb72c92a549dd5a330112','汤加伟',2,'/fileRepository/customer/ft6v5iqc4i.png','18810594763',NULL,'2018-01-09 22:34:39',1,0,26,'2018-02-14 09:48:01',36),(1927,'测试','96e79218965eb72c92a549dd5a330112',NULL,NULL,NULL,'15879234339',NULL,'2018-01-12 10:57:12',1,0,3,'2018-01-12 10:57:59',NULL),(1928,'zzz','96e79218965eb72c92a549dd5a330112',NULL,NULL,NULL,'18654785412',NULL,'2018-01-16 14:20:36',1,0,4,'2018-01-22 22:28:57',NULL);
/*!40000 ALTER TABLE `T_CUSTOMER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_CUSTOMER_ADDRESS`
--

DROP TABLE IF EXISTS `T_CUSTOMER_ADDRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_CUSTOMER_ADDRESS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `RECEIVER` varchar(30) NOT NULL,
  `PHONE` char(11) NOT NULL,
  `PROVINCE` varchar(20) NOT NULL,
  `CITY` varchar(20) NOT NULL,
  `COUNTY` varchar(20) NOT NULL,
  `AREA` varchar(50) NOT NULL,
  `ISDEFAULT` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_CUSTOMER_ADDRESS`
--

LOCK TABLES `T_CUSTOMER_ADDRESS` WRITE;
/*!40000 ALTER TABLE `T_CUSTOMER_ADDRESS` DISABLE KEYS */;
INSERT INTO `T_CUSTOMER_ADDRESS` VALUES (28,1914,'黄','15370761949','河北省','唐山市','路北区','李良是傻逼',1),(34,1926,'汤加伟','18810594763','湖北省','黄冈市','黄梅县','分路镇五条路村15组',0),(39,1926,'测试','15879234339','江西省','九江市','浔阳区','测试了',0),(40,1926,'周琪','18810594763','湖北省','黄冈市','黄梅县','小池',1),(41,1928,'test','13454685472','江西省','九江市','庐山区','sdsfsdfsf',1);
/*!40000 ALTER TABLE `T_CUSTOMER_ADDRESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_CUSTOMER_COLLECT`
--

DROP TABLE IF EXISTS `T_CUSTOMER_COLLECT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_CUSTOMER_COLLECT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCTID` int(11) NOT NULL,
  `CUSTOMERID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_CUSTOMER_COLLECT`
--

LOCK TABLES `T_CUSTOMER_COLLECT` WRITE;
/*!40000 ALTER TABLE `T_CUSTOMER_COLLECT` DISABLE KEYS */;
INSERT INTO `T_CUSTOMER_COLLECT` VALUES (30,174,1928),(31,164,1928),(32,178,1926);
/*!40000 ALTER TABLE `T_CUSTOMER_COLLECT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER`
--

DROP TABLE IF EXISTS `T_ORDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(50) NOT NULL,
  `PAYERID` int(11) NOT NULL,
  `PAYERNAME` varchar(50) DEFAULT NULL,
  `NUMBER` char(16) NOT NULL,
  `CTIME` datetime NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `CONSIGNEENAME` varchar(50) NOT NULL,
  `CONSIGNEEMOBILE` char(11) NOT NULL,
  `CONSIGNEEADDRESS` varchar(500) NOT NULL,
  `SHIPTYPEID` int(11) NOT NULL,
  `SHIPTYPENAME` varchar(500) NOT NULL,
  `SHIPTIMEID` int(11) DEFAULT NULL,
  `SHIPTIME` varchar(500) NOT NULL,
  `PAYMETHODID` int(11) DEFAULT NULL,
  `PAYMETHODNAME` varchar(50) DEFAULT NULL,
  `PAYTYPECODE` tinyint(4) NOT NULL,
  `TRADENO` varchar(50) DEFAULT NULL,
  `PAYSTATUS` tinyint(4) NOT NULL,
  `PAYTIME` datetime DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `FINISHTIME` datetime DEFAULT NULL,
  `CANCLEREASON` tinyint(4) DEFAULT NULL,
  `CANCLECOMMENT` varchar(500) DEFAULT NULL,
  `ISNEEDINV` tinyint(4) DEFAULT '0',
  `INVTITLE` varchar(100) DEFAULT NULL,
  `INVID` tinyint(4) DEFAULT NULL,
  `INV` varchar(50) DEFAULT NULL,
  `TAXPAYERNUMBER` varchar(50) DEFAULT NULL,
  `FREIGHT` double(5,2) DEFAULT '0.00',
  `PAYSCOST` double(5,2) DEFAULT '0.00',
  `TOTAL` double(5,2) DEFAULT '0.00',
  `ISREVIEW` tinyint(1) DEFAULT '0',
  `ISSERVICEREVIEW` tinyint(1) DEFAULT '0',
  `GRADELEVEL` tinyint(1) DEFAULT '5',
  `REVIEWCONTENT` varchar(500) DEFAULT NULL,
  `SERVICEREVIEWTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER`
--

LOCK TABLES `T_ORDER` WRITE;
/*!40000 ALTER TABLE `T_ORDER` DISABLE KEYS */;
INSERT INTO `T_ORDER` VALUES (101,'黄',1914,NULL,'2018010252455740','2018-01-02 11:56:50',5,'黄','15370761949','唐山市路北区  李良是傻逼',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,5.00,1,0,NULL,NULL,NULL),(103,'tangjiawei',38,'汤加伟','2018010376216088','2018-01-03 21:55:09',5,'汤加伟','18810594763','九江市庐山区  测试一波',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,1,0,NULL,NULL,NULL),(106,'tangjiawei',38,'汤加伟','2018010401656985','2018-01-04 12:59:42',5,'汤加伟','18810594763','九江市庐山区  测试一波',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,0,0,NULL,NULL,NULL),(109,'tangjiawei',38,'汤加伟','2018010503974194','2018-01-05 00:08:44',5,'汤加伟','18810594763','九江市庐山区  测试一波',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,1,1,5,'服务还行！','2018-01-05 00:11:45'),(111,'tangjiawei',38,'汤加伟','2018010833111239','2018-01-08 23:04:16',5,'黄梦洲','15671741080','唐山市路北区  打的',29,'物流配送',NULL,'2018-01-09(周二) 12:00~15:00',3,'货到付款',1,NULL,1,NULL,'不要辣的',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,0,0,NULL,NULL,NULL),(112,'tangjiawei',38,'汤加伟','2018010953705782','2018-01-09 12:04:55',5,'汤加伟','18810594763','九江市九江县  测试一波',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'不要辣的',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,0,1,5,'服务不错！！！','2018-01-09 12:41:36'),(113,'tangjiawei',38,'汤加伟','2018010930820086','2018-01-09 12:43:00',5,'黄梦洲','15671741080','唐山市路北区  打的',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.00,0,0,NULL,NULL,NULL),(114,'tangjiawei',1926,'汤加伟','2018011258814997','2018-01-12 12:34:24',3,'周琪','18810594763','黄冈市黄梅县  小池',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'麻烦快点配送吧,谢谢了',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.50,0,0,NULL,NULL,NULL),(115,'tangjiawei',1926,'汤加伟','2018011298000706','2018-01-12 12:38:36',5,'汤加伟','18810594763','黄冈市黄梅县  分路镇五条路村15组',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,6.50,0,0,NULL,NULL,NULL),(116,'tangjiawei',1926,'汤加伟','2018011224026583','2018-01-12 12:42:12',5,'汤加伟','18810594763','黄冈市黄梅县  分路镇五条路村15组',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,8.50,1,0,NULL,NULL,NULL),(118,'tangjiawei',1926,'汤加伟','2018011497838798','2018-01-14 19:20:53',5,'周琪','18810594763','黄冈市黄梅县  小池',26,'门店配送',NULL,'2018-01-15(周一) 09:00~12:00',1,'微信支付',0,'4200000063201801143965049250',2,'2018-01-14 19:21:44','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0.00,0.00,0.10,0,0,NULL,NULL,NULL),(119,'zzz',1928,NULL,'2018011696307729','2018-01-16 14:23:34',4,'test','13454685472','九江市庐山区  sdsfsdfsf',29,'物流配送',NULL,'2018-01-16(周二) 立即送出',NULL,'在线支付',0,NULL,1,NULL,'',NULL,3,'',NULL,NULL,NULL,NULL,NULL,4.00,0.00,9.50,0,0,NULL,NULL,NULL),(120,'zzz',1928,NULL,'2018011619703192','2018-01-16 16:27:50',5,'test','13454685472','九江市庐山区  sdsfsdfsf',29,'物流配送',NULL,'立即送出',3,'货到付款',1,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,10.00,0,0,NULL,NULL,NULL),(121,'zzz',1928,NULL,'2018012212178930','2018-01-22 22:32:59',1,'test','13454685472','九江市庐山区  sdsfsdfsf',29,'物流配送',NULL,'立即送出',NULL,'在线支付',0,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,8.00,0,0,NULL,NULL,NULL),(122,'tangjiawei',1926,'汤加伟','2018021434408168','2018-02-14 09:48:26',1,'汤加伟','18810594763','黄冈市黄梅县  分路镇五条路村15组',29,'物流配送',NULL,'2018-02-15(周四) 09:00~12:00',NULL,'在线支付',0,NULL,1,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4.00,0.00,17.50,0,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `T_ORDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDERITEMS`
--

DROP TABLE IF EXISTS `T_ORDERITEMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDERITEMS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDNO` char(16) NOT NULL,
  `PROID` int(11) NOT NULL,
  `PROITEMID` int(11) NOT NULL,
  `PRONAME` varchar(50) NOT NULL,
  `UNIT` varchar(5) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `PRICE` double(5,2) DEFAULT '0.00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDERITEMS`
--

LOCK TABLES `T_ORDERITEMS` WRITE;
/*!40000 ALTER TABLE `T_ORDERITEMS` DISABLE KEYS */;
INSERT INTO `T_ORDERITEMS` VALUES (121,'2017122913747146',153,91,'香蕉','斤',1,0.10),(122,'2017122913747146',155,96,'测试','斤',1,0.10),(123,'2017122913747146',154,94,'南瓜','斤',1,4.00),(124,'2017122935874998',153,91,'香蕉','斤',1,0.10),(125,'2017122953235822',153,91,'香蕉','斤',1,0.10),(126,'2017122953235822',156,97,'ceshi','斤',1,12.00),(130,'2018010252455740',155,96,'测试','斤',10,1.00),(132,'2018010376216088',164,105,'西瓜','斤',1,2.00),(135,'2018010401656985',164,105,'西瓜','斤',1,2.00),(138,'2018010503974194',164,105,'西瓜','斤',1,2.00),(140,'2018010833111239',164,105,'西瓜','斤',1,2.00),(141,'2018010953705782',164,105,'西瓜','斤',1,2.00),(142,'2018010930820086',164,105,'西瓜','斤',1,2.00),(143,'2018011258814997',176,121,'番茄','斤',1,2.50),(144,'2018011298000706',176,121,'番茄','斤',1,2.50),(145,'2018011224026583',174,119,'苹果','斤',1,4.50),(146,'2018011497838798',179,127,'测试商品','斤',1,0.10),(147,'2018011696307729',178,123,'大蒜叶','斤',1,3.00),(148,'2018011696307729',176,121,'番茄','斤',1,2.50),(149,'2018011619703192',178,123,'大蒜叶','斤',2,6.00),(150,'2018012212178930',164,126,'西瓜','斤',2,4.00),(151,'2018021434408168',175,120,'橘子','斤',1,2.00),(152,'2018021434408168',178,123,'大蒜叶','斤',3,9.00),(153,'2018021434408168',176,121,'番茄','斤',1,2.50);
/*!40000 ALTER TABLE `T_ORDERITEMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDERTRACK`
--

DROP TABLE IF EXISTS `T_ORDERTRACK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDERTRACK` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `OPETIME` datetime NOT NULL,
  `OPERATOR` varchar(50) NOT NULL,
  `ORDREID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDERTRACK`
--

LOCK TABLES `T_ORDERTRACK` WRITE;
/*!40000 ALTER TABLE `T_ORDERTRACK` DISABLE KEYS */;
INSERT INTO `T_ORDERTRACK` VALUES (1,'添加了订单','2017-12-23 22:53:48','tangjiawei',83),(2,'配送了订单','2017-12-23 22:54:06','zshop',83),(3,'订单配送成功','2017-12-23 22:54:51','zshop',83),(4,'添加备注:afddsds','2017-12-23 23:27:30','zshop',82),(5,'添加备注:测试一下','2017-12-23 23:28:10','zshop',82),(6,'添加备注:还没有支付','2017-12-23 23:29:57','zshop',82),(7,'添加备注:还没有支付','2017-12-23 23:30:27','zshop',82),(8,'添加备注:还没有支付','2017-12-23 23:30:29','zshop',82),(9,'添加备注:asdfasdfsdf','2017-12-23 23:31:40','zshop',82),(10,'添加备注:adfsdsfdsa','2017-12-23 23:34:26','zshop',82),(11,'添加备注:a131212132','2017-12-23 23:34:32','zshop',82),(12,'添加备注:adfswrqe','2017-12-23 23:37:38','zshop',82),(13,'添加备注:需要开发票','2017-12-23 23:38:06','zshop',83),(14,'添加了订单','2017-12-23 23:40:30','zshop',82),(15,'取消了订单','2017-12-23 23:48:09','zshop',77),(16,'先取消货品，后联系客户，了解具体情况！','2017-12-23 23:48:10','zshop',77),(17,'配送了订单','2017-12-23 23:54:15','zshop',65),(18,'配送了订单','2017-12-23 23:55:05','zshop',48),(19,'取消了订单','2017-12-23 23:58:55','zshop',48),(20,'先取消订单，后联系客户解决客户问题','2017-12-23 23:58:56','zshop',48),(21,'取消了订单','2017-12-24 00:01:52','zshop',47),(22,'添加备注:12211231231','2017-12-24 00:01:53','zshop',47),(23,'配送了订单','2017-12-24 00:03:26','zshop',42),(24,'取消了订单','2017-12-24 00:03:34','zshop',42),(25,'添加备注:','2017-12-24 00:03:35','zshop',42),(26,'取消了订单','2017-12-24 00:06:41','zshop',41),(27,'添加备注:','2017-12-24 00:06:42','zshop',41),(28,'取消了订单','2017-12-24 00:06:48','zshop',37),(29,'添加备注:','2017-12-24 00:06:49','zshop',37),(30,'取消了订单','2017-12-24 00:10:19','zshop',76),(32,'取消了订单','2017-12-24 00:10:30','zshop',74),(33,'取消了订单','2017-12-24 10:41:46','tangjiawei',80),(36,'取消了订单','2017-12-24 10:45:55','tangjiawei',72),(37,'取消了订单','2017-12-24 10:45:56','tangjiawei',70),(38,'取消了订单','2017-12-24 10:45:57','tangjiawei',69),(39,'取消了订单','2017-12-24 10:45:59','tangjiawei',64),(40,'取消了订单','2017-12-24 10:46:01','tangjiawei',63),(41,'取消了订单','2017-12-24 10:46:02','tangjiawei',62),(42,'取消了订单','2017-12-24 10:46:03','tangjiawei',61),(43,'取消了订单','2017-12-24 10:46:04','tangjiawei',60),(44,'取消了订单','2017-12-24 10:46:06','tangjiawei',59),(47,'取消了订单','2017-12-24 10:46:09','tangjiawei',56),(48,'取消了订单','2017-12-24 10:46:10','tangjiawei',55),(50,'取消了订单','2017-12-24 10:46:13','tangjiawei',53),(51,'取消了订单','2017-12-24 10:46:14','tangjiawei',52),(52,'取消了订单','2017-12-24 10:46:16','tangjiawei',51),(53,'取消了订单','2017-12-24 10:46:17','tangjiawei',50),(54,'取消了订单','2017-12-24 10:46:18','tangjiawei',49),(55,'取消了订单','2017-12-24 10:46:20','tangjiawei',46),(56,'取消了订单','2017-12-24 10:46:25','tangjiawei',45),(60,'添加了订单','2017-12-24 11:13:07','tangjiawei',84),(61,'添加了订单','2017-12-24 21:10:53','tangjiawei',85),(62,'支付了订单','2017-12-24 21:11:33','tangjiawei',85),(63,'添加了订单','2017-12-24 22:03:33','tangjiawei',86),(64,'添加备注:afsdsfd','2017-12-24 22:29:11','zshop',85),(65,'添加备注:fsda','2017-12-24 22:29:18','zshop',85),(66,'添加了订单','2017-12-25 23:00:53','tangjiawei',87),(67,'添加了订单','2017-12-26 19:08:41','tangjiawei',88),(68,'添加了订单','2017-12-28 12:07:44','tangjiawei',89),(69,'取消了订单','2017-12-28 13:22:59','zshop',89),(70,'添加了订单','2017-12-28 13:23:46','tangjiawei',90),(71,'添加了订单','2017-12-28 13:28:26','tangjiawei',91),(72,'配送了订单','2017-12-28 13:29:32','zshop',90),(73,'完成了订单','2017-12-28 13:29:52','zshop',90),(74,'添加了订单','2017-12-28 15:01:01','tangjiawei',92),(75,'配送了订单','2017-12-28 15:01:45','zshop',92),(76,'完成了订单','2017-12-28 15:01:52','zshop',92),(77,'添加了订单','2017-12-29 01:22:25','tangjiawei',93),(78,'配送了订单','2017-12-29 01:22:39','zshop',93),(79,'完成了订单','2017-12-29 01:22:45','zshop',93),(80,'添加了订单','2017-12-29 01:30:59','tangjiawei',94),(81,'配送了订单','2017-12-29 01:31:13','zshop',94),(82,'完成了订单','2017-12-29 01:31:19','zshop',94),(83,'添加了订单','2017-12-29 12:22:12','tangjiawei',95),(84,'配送了订单','2017-12-29 12:22:24','zshop',95),(85,'完成了订单','2017-12-29 12:22:29','zshop',95),(86,'添加了订单','2017-12-29 13:08:33','tangjiawei',96),(87,'添加了订单','2017-12-29 13:08:52','tangjiawei',97),(88,'支付了订单','2017-12-29 13:09:06','tangjiawei',97),(89,'添加了订单','2017-12-29 13:09:53','tangjiawei',98),(90,'配送了订单','2017-12-29 13:10:34','zshop',98),(91,'配送了订单','2017-12-29 13:10:36','zshop',97),(92,'配送了订单','2017-12-29 13:10:39','zshop',96),(93,'完成了订单','2017-12-29 13:10:47','zshop',98),(94,'完成了订单','2017-12-29 13:10:49','zshop',97),(95,'完成了订单','2017-12-29 13:10:55','zshop',96),(96,'添加了订单','2018-01-02 11:38:18','黄',99),(97,'添加了订单','2018-01-02 11:56:13','黄',100),(98,'添加了订单','2018-01-02 11:56:50','黄',101),(101,'添加了订单','2018-01-03 17:08:50','tangjiawei',102),(103,'配送了订单','2018-01-03 21:55:28','zshop',103),(106,'添加备注:afdssd','2018-01-03 23:11:03','zshop',103),(109,'取消了订单','2018-01-04 12:43:00','zshop',99),(111,'取消了订单','2018-01-04 12:44:24','zshop',104),(112,'添加了订单','2018-01-04 12:59:10','tangjiawei',105),(113,'取消了订单','2018-01-04 12:59:19','zshop',105),(114,'添加了订单','2018-01-04 12:59:42','tangjiawei',106),(115,'配送了订单','2018-01-04 12:59:52','zshop',106),(116,'完成了订单','2018-01-04 13:11:06','zshop',106),(118,'取消了订单','2018-01-04 13:13:41','tangjiawei',107),(119,'添加了订单','2018-01-04 22:16:05','tangjiawei',108),(120,'取消了订单','2018-01-04 22:16:31','tangjiawei',108),(121,'添加了订单','2018-01-05 00:08:44','tangjiawei',109),(122,'配送了订单','2018-01-05 00:08:54','zshop',109),(123,'完成了订单','2018-01-05 00:09:39','zshop',109),(124,'添加了订单','2018-01-08 23:03:07','tangjiawei',110),(125,'添加了订单','2018-01-08 23:04:16','tangjiawei',111),(126,'添加了订单','2018-01-09 12:04:55','tangjiawei',112),(127,'完成了订单','2018-01-09 12:40:52','tangjiawei',112),(128,'完成了订单','2018-01-09 12:41:00','tangjiawei',111),(129,'取消了订单','2018-01-09 12:42:01','tangjiawei',110),(130,'添加了订单','2018-01-09 12:43:00','tangjiawei',113),(131,'完成了订单','2018-01-09 12:43:28','tangjiawei',113),(132,'添加了订单','2018-01-12 12:34:24','tangjiawei',114),(133,'添加了订单','2018-01-12 12:38:36','tangjiawei',115),(134,'添加了订单','2018-01-12 12:42:12','tangjiawei',116),(135,'完成了订单','2018-01-14 19:05:20','tangjiawei',116),(136,'配送了订单','2018-01-14 19:13:27','zshop',115),(137,'完成了订单','2018-01-14 19:13:35','zshop',115),(138,'添加了订单','2018-01-14 19:16:07','tangjiawei',117),(139,'取消了订单','2018-01-14 19:20:19','tangjiawei',117),(140,'添加了订单','2018-01-14 19:20:53','tangjiawei',118),(141,'支付了订单','2018-01-14 19:21:44','tangjiawei',118),(142,'配送了订单','2018-01-14 22:46:32','zshop',118),(143,'配送了订单','2018-01-14 22:48:54','zshop',114),(144,'完成了订单','2018-01-14 22:49:06','zshop',118),(145,'添加了订单','2018-01-16 14:23:34','zzz',119),(146,'添加了订单','2018-01-16 16:27:50','zzz',120),(147,'添加备注:test','2018-01-16 16:34:36','zshop',120),(148,'配送了订单','2018-01-16 16:39:32','zshop',120),(149,'完成了订单','2018-01-16 16:42:13','zshop',120),(150,'取消了订单','2018-01-16 16:42:29','zshop',119),(151,'添加了订单','2018-01-22 22:32:59','zzz',121),(152,'添加了订单','2018-02-14 09:48:26','tangjiawei',122);
/*!40000 ALTER TABLE `T_ORDERTRACK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_CART`
--

DROP TABLE IF EXISTS `T_ORDER_CART`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_CART` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROID` int(11) NOT NULL DEFAULT '0',
  `PROITEMID` int(11) NOT NULL DEFAULT '0',
  `QUANTITY` int(11) NOT NULL DEFAULT '0',
  `PAYERID` int(11) NOT NULL DEFAULT '0',
  `CHECKED` tinyint(1) NOT NULL DEFAULT '0',
  `ORDERCARTID` char(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=287 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_CART`
--

LOCK TABLES `T_ORDER_CART` WRITE;
/*!40000 ALTER TABLE `T_ORDER_CART` DISABLE KEYS */;
/*!40000 ALTER TABLE `T_ORDER_CART` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_ITEM`
--

DROP TABLE IF EXISTS `T_ORDER_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_ITEM` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMERID` int(11) NOT NULL,
  `ORDERID` int(11) NOT NULL,
  `PRODUCTID` int(11) NOT NULL,
  `ACTIVITYID` int(11) DEFAULT NULL COMMENT '活动id,如果购买的是活动商品则记录活动id',
  `CREATETIME` datetime NOT NULL,
  `UPDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `EXPRESSID` int(11) DEFAULT NULL COMMENT '快递公司',
  `TRANSPORTNO` varchar(60) DEFAULT NULL COMMENT '运单号',
  `ORIGINALPRICE` decimal(9,2) NOT NULL COMMENT '原价',
  `PRICE` decimal(9,2) NOT NULL COMMENT '单价',
  `QUANTITY` int(11) NOT NULL COMMENT '真实购买数量',
  `ORDERSTS` varchar(20) NOT NULL DEFAULT '0' COMMENT '已下单(等待付款),已支付(处理中),已发货,已到货,配送异常,退款中,已退款',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ORDERID` (`ORDERID`),
  KEY `IDX_CUSTOMERID` (`CUSTOMERID`),
  KEY `IDX_PRODUCTID` (`PRODUCTID`),
  KEY `IDX_ACTIVITYID` (`ACTIVITYID`),
  KEY `IDX_EXPRESSID` (`EXPRESSID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_ITEM`
--

LOCK TABLES `T_ORDER_ITEM` WRITE;
/*!40000 ALTER TABLE `T_ORDER_ITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `T_ORDER_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_PAYINFO`
--

DROP TABLE IF EXISTS `T_ORDER_PAYINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_PAYINFO` (
  `ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `PAYTYPE` tinyint(4) NOT NULL DEFAULT '0',
  `PAYCODE` int(11) NOT NULL DEFAULT '0',
  `PAYNAME` varchar(30) NOT NULL,
  `APPID` varchar(50) DEFAULT NULL,
  `PARTNERID` varchar(50) DEFAULT NULL,
  `ALIPAYPUBLICSECRET` varchar(500) DEFAULT NULL,
  `PUBLICSECRET` varchar(500) DEFAULT NULL,
  `APPSECRET` varchar(500) DEFAULT NULL,
  `SECRET` text,
  `MERCHANTNUMBER` varchar(50) DEFAULT NULL,
  `LOGOPATH` varchar(100) DEFAULT NULL,
  `BANKURL` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL,
  `ISUSE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_PAYINFO`
--

LOCK TABLES `T_ORDER_PAYINFO` WRITE;
/*!40000 ALTER TABLE `T_ORDER_PAYINFO` DISABLE KEYS */;
INSERT INTO `T_ORDER_PAYINFO` VALUES (1,0,17,'微信支付','wx182d1b6875709c85',NULL,NULL,NULL,'28ef778f0eb1a9c503b665874a589315','ZscpyfbYZw20160519ZscpyfbYZw2016','10036286','/images/wechatpay.png','https://mp.weixin.qq.com','微信支付腾讯公司旗下的中国领先的在线支付平台。',1),(2,0,18,'支付宝手机支付','2015122201022771','2088511710905814','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','2016090800464248','/images/alipay.png','https://www.alipay.com','仅适用于在手机版使用的支付方式,PC版不支持。',1),(3,1,19,'货到付款',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'/images/img-arrival.png',NULL,NULL,1);
/*!40000 ALTER TABLE `T_ORDER_PAYINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_PROMOTION`
--

DROP TABLE IF EXISTS `T_ORDER_PROMOTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_PROMOTION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SHIPID` int(11) DEFAULT NULL,
  `PAYID` int(11) DEFAULT NULL,
  `MINTOTAL` double(10,2) NOT NULL,
  `DECRTOTAL` double(10,2) DEFAULT NULL,
  `DESCRIPTION` varchar(300) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_PROMOTION`
--

LOCK TABLES `T_ORDER_PROMOTION` WRITE;
/*!40000 ALTER TABLE `T_ORDER_PROMOTION` DISABLE KEYS */;
INSERT INTO `T_ORDER_PROMOTION` VALUES (48,NULL,0,50.00,3.00,'在线支付满50元,减3元!'),(49,NULL,1,42.00,2.00,'货到付款满42元,减2元!'),(50,29,NULL,42.00,NULL,'选择物流配送配送方式,订单满42元包邮!');
/*!40000 ALTER TABLE `T_ORDER_PROMOTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_REGIONS`
--

DROP TABLE IF EXISTS `T_ORDER_REGIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_REGIONS` (
  `ID` int(11) NOT NULL,
  `PID` int(11) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_REGIONS`
--

LOCK TABLES `T_ORDER_REGIONS` WRITE;
/*!40000 ALTER TABLE `T_ORDER_REGIONS` DISABLE KEYS */;
INSERT INTO `T_ORDER_REGIONS` VALUES (1379,NULL,'江西省'),(1871,NULL,'湖北省'),(1404,1379,'九江市'),(1962,1871,'黄冈市'),(1406,1404,'庐山区'),(1407,1404,'浔阳区'),(1408,1404,'九江县'),(1409,1404,'武宁县'),(1410,1404,'修水县'),(1411,1404,'永修县'),(1412,1404,'德安县'),(1413,1404,'星子县'),(1414,1404,'都昌县'),(1415,1404,'湖口县'),(1416,1404,'彭泽县'),(1417,1404,'瑞昌市'),(1964,1962,'黄州区'),(1965,1962,'团风县'),(1966,1962,'红安县'),(1967,1962,'罗田县'),(1968,1962,'英山县'),(1969,1962,'浠水县'),(1970,1962,'蕲春县'),(1971,1962,'黄梅县'),(1972,1962,'麻城市'),(1973,1962,'武穴市');
/*!40000 ALTER TABLE `T_ORDER_REGIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_SHIP`
--

DROP TABLE IF EXISTS `T_ORDER_SHIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_SHIP` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `AREACOSTTYPE` tinyint(1) NOT NULL DEFAULT '0',
  `FREIGHT` double(10,2) NOT NULL,
  `ORDERNUM` int(11) NOT NULL,
  `DISABLED` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_SHIP`
--

LOCK TABLES `T_ORDER_SHIP` WRITE;
/*!40000 ALTER TABLE `T_ORDER_SHIP` DISABLE KEYS */;
INSERT INTO `T_ORDER_SHIP` VALUES (26,'门店配送',1,0.00,1,1),(27,'自提',1,0.00,2,1),(29,'物流配送',2,4.00,0,1);
/*!40000 ALTER TABLE `T_ORDER_SHIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_ORDER_SHIPTIME`
--

DROP TABLE IF EXISTS `T_ORDER_SHIPTIME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_ORDER_SHIPTIME` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_ORDER_SHIPTIME`
--

LOCK TABLES `T_ORDER_SHIPTIME` WRITE;
/*!40000 ALTER TABLE `T_ORDER_SHIPTIME` DISABLE KEYS */;
INSERT INTO `T_ORDER_SHIPTIME` VALUES (20,'周末配送（不是周一到周五配送）'),(21,'周一到周五');
/*!40000 ALTER TABLE `T_ORDER_SHIPTIME` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT`
--

DROP TABLE IF EXISTS `T_PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPEID` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `PRICE` double(10,2) DEFAULT '0.00',
  `SALE` tinyint(1) NOT NULL COMMENT '商品上下架状态:0 表示下架，1表示上架',
  `SORTCODE` int(11) DEFAULT '100',
  `UNIT` varchar(2) NOT NULL DEFAULT '' COMMENT '计量单位',
  `IMAGES` text,
  `SPEC` varchar(300) DEFAULT NULL COMMENT '商品描述',
  `DESCRIPTION` text COMMENT '商品描述',
  `NEWARRIVAL` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT`
--

LOCK TABLES `T_PRODUCT` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT` DISABLE KEYS */;
INSERT INTO `T_PRODUCT` VALUES (164,23,'西瓜',2.00,1,120,'斤','/upload/product/2h0rjnntqb.png,/upload/product/4ga7ocqbkx.png,/upload/product/2s03lxsrvl.png','{\"spec0\":{\"defaultValue\":[\"16~20斤\",\"11~15斤\",\"6~10斤\",\"1~5斤\"],\"value\":[\"16~20斤\",\"11~15斤\",\"6~10斤\",\"1~5斤\"]},\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p style=\"text-align: left;\">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"#999999\">产地 :</font>&nbsp; &nbsp;&nbsp;<span style=\"color: rgb(153, 153, 153);\">黄梅刘佐</span></p> \n  <p style=\"text-align: left;\">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"#999999\">口感:</font>&nbsp; &nbsp;&nbsp;<span style=\"color: rgb(153, 153, 153);\">汁多,新鲜</span></p> \n  <p style=\"text-align: left;\">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"#999999\">亮点:</font>&nbsp; &nbsp;&nbsp;<span style=\"color: rgb(153, 153, 153);\">所有的西瓜都是根据客户的</span><span style=\"color: rgb(153, 153, 153);\">前一天的订单,然后直接到种</span><span style=\"color: rgb(153, 153, 153);\">植地取货,绝对保证新鲜!</span></p> \n  <p style=\"text-align: left;\">&nbsp; &nbsp;&nbsp;&nbsp;<font color=\"#999999\">其他说明:</font>&nbsp; &nbsp;&nbsp;<font color=\"#999999\">由</font><span style=\"color: rgb(153, 153, 153);\">于西瓜的重量无法正确提供,</span><span style=\"color: rgb(153, 153, 153);\">只能提供个参考范围,所以麻烦</span><span style=\"color: rgb(153, 153, 153);\">您购买西瓜时选择货到付款</span></p> \n </body>\n</html>',1),(174,NULL,'苹果',4.50,1,60,'斤','/upload/product/nn9wv51my4.png,/upload/product/86idxk1bp4.png,/upload/product/k32uo5vlhv.png','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p><font color=\"#46acc8\">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;果品简介: &nbsp;&nbsp;&nbsp;&nbsp; 山西冰糖心富士，长的丑,口感好&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品红外观: &nbsp;&nbsp;&nbsp;&nbsp; 青红相见</font></p> \n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;口感:&nbsp; &nbsp; &nbsp; 脆甜&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 其他说明:&nbsp; &nbsp; &nbsp; 当场验货</font></p> \n </body>\n</html>',1),(175,NULL,'橘子',2.00,1,40,'斤','/upload/product/mn52lvmopy.png,/upload/product/xps28xug7w.png,/upload/product/6vxlfoh066.png','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p><img src=\"/upload/product/r3kezyo9f3.png\" style=\"max-width:100%;\"><br></p>\n </body>\n</html>',1),(176,NULL,'番茄',2.50,1,50,'斤','/upload/product/s2e9uvjqai.png,/upload/product/hjsd0u16zo.png,/upload/product/hz380cpfxt.png','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p>&nbsp;&nbsp;<font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产地:&nbsp;&nbsp;&nbsp;&nbsp;黄梅刘佐&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;口感:&nbsp;&nbsp;&nbsp;&nbsp;汁多,新鲜&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亮点:&nbsp;&nbsp;&nbsp;&nbsp;所有的水果都是根据客户的前一天的订单,然后直接到种植地取货,绝对保证新鲜!&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; 其他说明:&nbsp;&nbsp;&nbsp;&nbsp;当场验货</font></p> \n </body>\n</html>',1),(177,NULL,'白菜',0.50,1,20,'斤','/upload/product/unjltsvres.png,/upload/product/nhxn8ai7hk.png,/upload/product/kdscjjaicz.png','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 产地:&nbsp;&nbsp;&nbsp;&nbsp;黄梅分路(自己家种植)</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;口感:&nbsp;&nbsp;&nbsp;&nbsp;汁多,新鲜&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亮点:&nbsp;&nbsp;&nbsp;&nbsp;所有的蔬菜都是根据客户的前一天的订单,然后直接到种植地取货,绝对保证新鲜!&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; 其他说明:&nbsp;&nbsp;&nbsp;&nbsp;当场验货</font></p> \n </body>\n</html>',1),(178,NULL,'大蒜叶',3.00,1,10,'斤','/upload/product/ckli0u6prn.png,/upload/product/qasmtlshbc.png,/upload/product/i3dnptnnbi.png','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=\"#999999\">&nbsp;产地:&nbsp;&nbsp;&nbsp;&nbsp;黄梅分路(自己家种植)</font></p> \n  <p><font color=\"#999999\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亮点:&nbsp;&nbsp;&nbsp;&nbsp;所有的蔬菜都是根据客户的前一天的订单,然后直接到种植地取货,绝对保证新鲜!&nbsp;</font></p> \n  <p><font color=\"#999999\">&nbsp; &nbsp; &nbsp; 其他说明:&nbsp;&nbsp;&nbsp;&nbsp;当场验货</font></p> \n </body>\n</html>',1),(179,NULL,'测试商品',0.10,1,100,'斤','/upload/product/t7e5ne09no.jpg,/upload/product/e9hl047qrt.jpg,/upload/product/65g30jnrfi.jpg','{\"spec0\":\"\",\"spec1\":\"\",\"spec2\":\"\"}','<html>\n <head></head>\n <body>\n  <p><font color=\"#c24f4a\">测试使用，勿拍！！！&nbsp;</font><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3a/moren_xiaoerbuyu_org.png\" alt=\"[笑而不语]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/2c/moren_yunbei_org.png\" alt=\"[允悲]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_org.gif\" alt=\"[微笑]\" data-w-e=\"1\"></p> \n </body>\n</html>',0);
/*!40000 ALTER TABLE `T_PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_ASSOCIATE_CATEGORY`
--

DROP TABLE IF EXISTS `T_PRODUCT_ASSOCIATE_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_ASSOCIATE_CATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_ASSOCIATE_CATEGORY`
--

LOCK TABLES `T_PRODUCT_ASSOCIATE_CATEGORY` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_ASSOCIATE_CATEGORY` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_ASSOCIATE_CATEGORY` VALUES (366,174,35),(367,175,35),(370,176,36),(374,178,36),(375,177,36),(383,179,37),(391,164,35),(392,164,37);
/*!40000 ALTER TABLE `T_PRODUCT_ASSOCIATE_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_ASSOCIATE_MANAGECATEGORY`
--

DROP TABLE IF EXISTS `T_PRODUCT_ASSOCIATE_MANAGECATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_ASSOCIATE_MANAGECATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) NOT NULL,
  `MANAGECATEGORY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=517 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_ASSOCIATE_MANAGECATEGORY`
--

LOCK TABLES `T_PRODUCT_ASSOCIATE_MANAGECATEGORY` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_ASSOCIATE_MANAGECATEGORY` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_ASSOCIATE_MANAGECATEGORY` VALUES (464,174,13),(465,174,14),(466,175,13),(471,176,12),(472,176,13),(479,178,12),(480,178,13),(481,177,13),(482,177,14),(503,179,12),(504,179,13),(505,179,14),(506,179,15),(515,164,13),(516,164,14);
/*!40000 ALTER TABLE `T_PRODUCT_ASSOCIATE_MANAGECATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_CATEGORY`
--

DROP TABLE IF EXISTS `T_PRODUCT_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_CATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `SEQUENCE` char(15) NOT NULL,
  `CREATED` datetime NOT NULL,
  `MODIFIED` datetime NOT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_CATEGORY`
--

LOCK TABLES `T_PRODUCT_CATEGORY` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_CATEGORY` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_CATEGORY` VALUES (35,'水果','yh5j0x33ca','2017-11-01 21:12:50','2018-01-22 23:11:18',''),(36,'蔬菜','u960qjil4s','2017-11-01 21:12:58','2017-11-01 21:12:58',''),(37,'农产品','joswepbt3t','2017-11-01 21:14:04','2017-11-01 21:14:04',''),(38,'肉类','9r7hvs1sd1','2017-11-01 21:14:12','2017-11-01 21:14:12',''),(39,'测试1','z0z4i16tey','2018-02-09 14:48:40','2018-02-09 14:48:40',''),(40,'ceshi2','5iumcnqubs','2018-02-09 14:48:49','2018-02-09 14:48:49',''),(41,'测试3','4a2x23gjup','2018-02-09 14:48:54','2018-02-09 14:48:54',''),(42,'ceshi4','z4iobd4plf','2018-02-09 14:48:58','2018-02-09 14:48:58',''),(43,'ceshi5','fhoe8gutbg','2018-02-09 14:49:03','2018-02-09 14:49:03',''),(44,'ceshi6','mxs2j1j66t','2018-02-09 14:49:08','2018-02-09 14:49:08',''),(45,'ceshi7','z5h9kkruns','2018-02-09 14:49:13','2018-02-09 14:49:13','');
/*!40000 ALTER TABLE `T_PRODUCT_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_DEFAULT_SPEC`
--

DROP TABLE IF EXISTS `T_PRODUCT_DEFAULT_SPEC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_DEFAULT_SPEC` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPEID` int(11) NOT NULL,
  `NAME` varchar(30) NOT NULL COMMENT '规格名称',
  `SPEC` varchar(400) DEFAULT NULL COMMENT '规格值',
  `SPECINDEX` tinyint(4) NOT NULL DEFAULT '0',
  `STATUS` tinyint(1) unsigned zerofill NOT NULL COMMENT '0表示隐藏,1表示显示',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_DEFAULT_SPEC`
--

LOCK TABLES `T_PRODUCT_DEFAULT_SPEC` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_DEFAULT_SPEC` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_DEFAULT_SPEC` VALUES (73,23,'重量范围','1~5斤,6~10斤,11~15斤,16~20斤',0,1);
/*!40000 ALTER TABLE `T_PRODUCT_DEFAULT_SPEC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_ITEM`
--

DROP TABLE IF EXISTS `T_PRODUCT_ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_ITEM` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCTID` int(11) NOT NULL,
  `SPEC0` varchar(20) DEFAULT NULL,
  `SPEC1` varchar(20) DEFAULT NULL,
  `SPEC2` varchar(20) DEFAULT NULL,
  `PRICE` double(10,2) DEFAULT '0.00',
  `STATUS` char(1) NOT NULL DEFAULT '0',
  `ISDEFAULT` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_ITEM`
--

LOCK TABLES `T_PRODUCT_ITEM` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_ITEM` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_ITEM` VALUES (116,164,'11~15斤','','',22.00,'1',0),(118,164,'1~5斤','','',2.00,'1',0),(119,174,NULL,NULL,NULL,4.50,'1',1),(120,175,NULL,NULL,NULL,2.00,'1',1),(121,176,NULL,NULL,NULL,2.50,'1',1),(122,177,NULL,NULL,NULL,0.50,'1',1),(123,178,NULL,NULL,NULL,3.00,'1',1),(125,164,'6~10斤','','',2.00,'1',0),(126,164,'16~20斤','','',2.00,'1',0),(127,179,NULL,NULL,NULL,0.10,'1',1);
/*!40000 ALTER TABLE `T_PRODUCT_ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_MANAGECATEGORY`
--

DROP TABLE IF EXISTS `T_PRODUCT_MANAGECATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_MANAGECATEGORY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `SEQUENCE` char(15) NOT NULL,
  `CREATED` datetime NOT NULL,
  `MODIFIED` datetime NOT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  `STATUS` tinyint(1) unsigned zerofill NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_MANAGECATEGORY`
--

LOCK TABLES `T_PRODUCT_MANAGECATEGORY` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_MANAGECATEGORY` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_MANAGECATEGORY` VALUES (12,'春季商品','vhlg7hqty9','2017-11-01 21:15:39','2017-11-01 21:15:53','春天出售的商品！',1),(13,'夏季商品','smb5unufmt','2017-11-01 21:16:07','2017-11-01 21:16:07','夏季出售的商品！',1),(14,'秋季商品','82y3gwnsoj','2017-11-01 21:16:24','2017-11-01 21:16:24','秋天出售的商品！',1),(15,'冬季商品','fd8qfi13vg','2017-11-01 21:16:44','2017-11-01 21:16:44','冬天出售的商品！',1);
/*!40000 ALTER TABLE `T_PRODUCT_MANAGECATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_REVIEW`
--

DROP TABLE IF EXISTS `T_PRODUCT_REVIEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_REVIEW` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDERID` int(11) NOT NULL,
  `PROID` int(11) NOT NULL,
  `PITEMID` int(11) NOT NULL,
  `CUSTOMERID` int(11) NOT NULL,
  `ACCOUNT` varchar(50) NOT NULL,
  `CUSTOMERPHOTO` varchar(500) DEFAULT NULL,
  `PRODUCTNAME` varchar(100) NOT NULL,
  `PRODUCTIMAGE` varchar(100) DEFAULT NULL,
  `SPEC0` varchar(30) DEFAULT NULL,
  `SPEC1` varchar(30) DEFAULT NULL,
  `SPEC2` varchar(30) DEFAULT NULL,
  `ISREVIEW` tinyint(1) NOT NULL DEFAULT '0',
  `CONTENT` varchar(500) DEFAULT NULL,
  `GRADELEVEL` tinyint(1) DEFAULT NULL,
  `ISDISPLAY` tinyint(1) NOT NULL DEFAULT '0',
  `IMGPATH` varchar(500) DEFAULT NULL,
  `CREATETIME` datetime NOT NULL,
  `ANSWER` varchar(500) DEFAULT NULL,
  `TYPE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_REVIEW`
--

LOCK TABLES `T_PRODUCT_REVIEW` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_REVIEW` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_REVIEW` VALUES (35,116,174,119,1926,'tangjiawei','http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTILQLcP2P7XbQY3rcYBIsv98JWBIIf8ewv2dicQaFDWic1HWXmR9FoydMcembMa9Iicz7GicSfsc88z8Q/132','苹果','/upload/product/nn9wv51my4.png',NULL,NULL,NULL,1,'苹果不错，吃起来蛮香的！！！！！好评！！',5,2,NULL,'2018-01-14 19:07:08','谢谢亲的惠顾，我们一定会更加努力，做的更好的!',0),(36,115,176,121,1926,'tangjiawei','http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTILQLcP2P7XbQY3rcYBIsv98JWBIIf8ewv2dicQaFDWic1HWXmR9FoydMcembMa9Iicz7GicSfsc88z8Q/132','番茄','/upload/product/s2e9uvjqai.png',NULL,NULL,NULL,1,NULL,5,0,NULL,'2018-03-04 02:00:00',NULL,1),(37,118,179,127,1926,'tangjiawei','http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTILQLcP2P7XbQY3rcYBIsv98JWBIIf8ewv2dicQaFDWic1HWXmR9FoydMcembMa9Iicz7GicSfsc88z8Q/132','测试商品','/upload/product/t7e5ne09no.jpg',NULL,NULL,NULL,1,NULL,5,0,NULL,'2018-03-04 02:00:00',NULL,1),(38,120,178,123,1928,'zzz',NULL,'大蒜叶','/upload/product/ckli0u6prn.png',NULL,NULL,NULL,1,NULL,5,0,NULL,'2018-03-04 02:00:00',NULL,1);
/*!40000 ALTER TABLE `T_PRODUCT_REVIEW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_PRODUCT_TYPE`
--

DROP TABLE IF EXISTS `T_PRODUCT_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_PRODUCT_TYPE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `CREATED` datetime NOT NULL,
  `MODIFIED` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_PRODUCT_TYPE`
--

LOCK TABLES `T_PRODUCT_TYPE` WRITE;
/*!40000 ALTER TABLE `T_PRODUCT_TYPE` DISABLE KEYS */;
INSERT INTO `T_PRODUCT_TYPE` VALUES (23,'重量范围','2018-01-02 22:35:33','2018-01-02 22:35:33');
/*!40000 ALTER TABLE `T_PRODUCT_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_SITE`
--

DROP TABLE IF EXISTS `T_SITE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_SITE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SITENAME` varchar(100) DEFAULT NULL,
  `LOGO` varchar(100) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_SITE`
--

LOCK TABLES `T_SITE` WRITE;
/*!40000 ALTER TABLE `T_SITE` DISABLE KEYS */;
INSERT INTO `T_SITE` VALUES (2,'九江送','/upload/site/1559ff6a4ad.jpg','010-87127480','<h2 style=\"text-align: center;\">九江送</h2><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;九江送创立于2018年1月1号。是一种网上零售平台，主要针对于九江的客户进行蔬菜，水果以及其他农产品的线上下单，线下配送。电商将从如今的集中于网上交易货物及服务，向行业运作的各环节领域扩展和延伸。在企业内部，电商元素将渗透到企业管理、内部业务流程；在外部产业群领域，电商的发展将激活和带动一系列上下游产业如结算、包装、物流配送、基于位置服务等领域的发展。此外还将引导周边相关产业的创新与升级，如利用智能化远程水电煤表进行远程自动查表与收费。而这些创新反过来又将促使电商模式的不断升级拓展。商品来源 商家提供 所有网友、小商产品线 特定或有限 多样化产品线深度 不错 非常多样化欺伪 不多 有时发生商品运送 不错 品质不一产品保证 不错 需要规则提升。</p><p>&nbsp;<img src=\"/manager/upload/site/cpnxdt5bpc.jpg\" style=\"max-width: 100%;\"></p>');
/*!40000 ALTER TABLE `T_SITE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_SITECONFIG`
--

DROP TABLE IF EXISTS `T_SITECONFIG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_SITECONFIG` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDENTIFIER` varchar(30) NOT NULL,
  `VALUE` varchar(30) DEFAULT NULL,
  `JSONVALUE` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_SITECONFIG`
--

LOCK TABLES `T_SITECONFIG` WRITE;
/*!40000 ALTER TABLE `T_SITECONFIG` DISABLE KEYS */;
INSERT INTO `T_SITECONFIG` VALUES (2,'shiptime','2','{\"timeInterval\":3,\"time0\":\"06:00~09:00\",\"time1\":\"09:00~12:00\",\"time2\":\"12:00~15:00\",\"sendTimeLength\":3,\"sendNow\":1,\"canReserveDay\":1,\"reserveDay\":2}'),(4,'wechatConfig','MP_verify_5p7Q7ZoCxMaBZm7D.txt',NULL),(5,'siteRunState','1','您好，本店暂停营业，请您稍后再来...'),(6,'goodReviewDay','10',NULL);
/*!40000 ALTER TABLE `T_SITECONFIG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_SITE_PIC`
--

DROP TABLE IF EXISTS `T_SITE_PIC`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_SITE_PIC` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PATH` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_SITE_PIC`
--

LOCK TABLES `T_SITE_PIC` WRITE;
/*!40000 ALTER TABLE `T_SITE_PIC` DISABLE KEYS */;
/*!40000 ALTER TABLE `T_SITE_PIC` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_WXCUSTOMER`
--

DROP TABLE IF EXISTS `T_WXCUSTOMER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `T_WXCUSTOMER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NICKNAME` varchar(100) NOT NULL,
  `SEX` tinyint(1) NOT NULL DEFAULT '0',
  `HEADIMGURL` varchar(500) DEFAULT NULL,
  `OPENID` varchar(50) NOT NULL,
  `CUSTOMERID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_WXCUSTOMER`
--

LOCK TABLES `T_WXCUSTOMER` WRITE;
/*!40000 ALTER TABLE `T_WXCUSTOMER` DISABLE KEYS */;
INSERT INTO `T_WXCUSTOMER` VALUES (19,'李良',1,'http://wx.qlogo.cn/mmopen/vi_32/pDkzKWNmQick2VaETEyqIwByN7RIGk6wMocRia8H9eOfzuCSZ4p7gicz521AjM55icmT9icic2hI4H9HXHibthfMRzK0g/0','oM3zhslebyOYjOVUTmXdBub4aTA8',39),(20,'黄',1,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJzZ2mgIibI3jOfSV5DjmGJZr0eKLqXPTFXZA9FBFRdJAwJAiaV3ichib8QxXC4f7pjcMicJx7rvxOlBw/0','oM3zhsqanpDIViAwI6x77F34H2Gk',1914),(21,'杨 迪',1,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJBY8JpLHboHg8cCycAeBu7W7yTH6JOLuCAfOPR2sOAGpja9eneQKhnxVeykXic54XozS1V1WIILDg/0','oM3zhskX40_bOr77_EH5gTzFtFeY',1921),(36,'星愿',2,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTILQLcP2P7XbQY3rcYBIsv98JWBIIf8ewv2dicQaFDWic1HWXmR9FoydMcembMa9Iicz7GicSfsc88z8Q/132','oM3zhshYxvjjc9uZ9UgTD1CZI9dQ',1926);
/*!40000 ALTER TABLE `T_WXCUSTOMER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'shop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-04 19:03:01
