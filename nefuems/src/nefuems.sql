-- MySQL dump 10.13  Distrib 5.6.14, for Win32 (x86)
--
-- Host: localhost    Database: nefuems
-- ------------------------------------------------------
-- Server version	5.6.14-enterprise-commercial-advanced

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员标识id',
  `username` varchar(255) DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '时间',
  `lastLoginIp` varchar(255) DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin',NULL,'2014-04-03 10:16:14','127.0.0.1');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_opera_record`
--

DROP TABLE IF EXISTS `admin_opera_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_opera_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operaTime` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `adminID` int(11) DEFAULT NULL,
  `operaContent` varchar(255) DEFAULT NULL,
  `operaAdminName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id` (`adminID`),
  CONSTRAINT `admin_id` FOREIGN KEY (`adminID`) REFERENCES `admin` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_opera_record`
--

LOCK TABLES `admin_opera_record` WRITE;
/*!40000 ALTER TABLE `admin_opera_record` DISABLE KEYS */;
INSERT INTO `admin_opera_record` VALUES (1,'2014-03-26 16:42:59',NULL,1,'管理员登陆',NULL),(2,'2014-03-27 09:11:07','127.0.0.1',1,'管理员登陆',NULL),(3,'2014-03-27 09:13:20','127.0.0.1',1,'增加用户,工号：0001',NULL),(4,'2014-03-27 09:16:10','127.0.0.1',1,'增加用户,工号：0002',NULL),(5,'2014-03-27 09:21:11','127.0.0.1',1,'管理员登陆',NULL),(6,'2014-03-27 09:57:19','127.0.0.1',1,'管理员登陆',NULL),(7,'2014-03-27 15:29:16','127.0.0.1',1,'管理员登陆',NULL),(8,'2014-03-27 15:34:27','127.0.0.1',1,'管理员登陆',NULL),(9,'2014-04-02 10:16:36','127.0.0.1',1,'管理员登陆',NULL),(10,'2014-04-02 10:49:29','127.0.0.1',1,'管理员登陆',NULL),(11,'2014-04-02 12:44:07','127.0.0.1',1,'管理员登陆',NULL),(12,'2014-04-02 13:49:07','127.0.0.1',1,'管理员登陆',NULL),(13,'2014-04-02 15:54:15','127.0.0.1',1,'管理员登陆',NULL),(14,'2014-04-03 10:16:15','127.0.0.1',1,'管理员登陆',NULL);
/*!40000 ALTER TABLE `admin_opera_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) DEFAULT NULL,
  `empNum` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (17,'圆通快递',NULL,'2014-03-03 11:17:33','http://www.yto.net.cn/cn/index/index.html','Y'),(18,'顺丰快递',NULL,'2014-03-20 15:28:40','wwwa','Y');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `eaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_cid` (`cid`),
  CONSTRAINT `employee_cid` FOREIGN KEY (`cid`) REFERENCES `company` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'0001','0000','邢晓琦',NULL,17,'clerk',NULL),(2,'0002','0000','卢文争',NULL,17,'clerk',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mes_oid` (`oid`),
  KEY `mes_cid` (`cid`),
  CONSTRAINT `mes_cid` FOREIGN KEY (`cid`) REFERENCES `company` (`id`),
  CONSTRAINT `mes_oid` FOREIGN KEY (`oid`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (10,'习近平参观沃尔沃汽车公司根特工厂','新华网比利时根特4月1日电(记者孟娜 魏建华)国家主席习近平1日上午在比利时国王菲利普陪同下参观了沃尔沃汽车公司根特工厂。\r\n\r\n　　习近平和夫人彭丽媛、菲利普国王和玛蒂尔德王后一同走进生产车间，参观汽车展示和生产线，受到当地员工的热情欢迎。企业负责人向习近平介绍汽车研发、生产和销售情况。习近平和菲利普国王共同为公司出口中国的第30万辆汽车揭幕。\r\n\r\n　　根特工厂是沃尔沃在瑞典以外规模最大的汽车总装厂，2010年被中国吉利集团收购，产量稳步增长，增加了当地就业。\r\n\r\n　　王沪宁、栗战书、杨洁篪等陪同参观。','2014-04-03 10:19:57'),(11,'苏州新增1例人感染H7N9禽流感','江南时报讯(记者 李晓静)最新消息，记者昨日从江苏省卫生厅获悉，苏州新增1例人感染H7N9禽流感确诊病例。据介绍，患者陆某，男，28岁，4月2日被苏州市专家组诊断为人感染H7N9禽流感确诊病例。患者家中饲养鸡2只、鸽子4只。目前该患者在苏州市某医院治疗，病情较重。','2014-04-03 10:20:49'),(12,'苏州新增1例人感染H7N9禽流感','江南时报讯(记者 李晓静)最新消息，记者昨日从江苏省卫生厅获悉，苏州新增1例人感染H7N9禽流感确诊病例。据介绍，患者陆某，男，28岁，4月2日被苏州市专家组诊断为人感染H7N9禽流感确诊病例。患者家中饲养鸡2只、鸽子4只。目前该患者在苏州市某医院治疗，病情较重。','2014-04-03 10:20:49');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL COMMENT '订单号码',
  `name` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `tel` varchar(255) DEFAULT NULL COMMENT '收件人电话号码',
  `address` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `status` varchar(255) DEFAULT NULL COMMENT '状态：（未发送，已发送，待签收，已签收）',
  `insertTime` datetime DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `receiveTime` datetime DEFAULT NULL,
  `cid` int(11) NOT NULL,
  `isNoticed` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_cid` (`cid`),
  CONSTRAINT `order_cid` FOREIGN KEY (`cid`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('E001','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','3','2014-04-03 10:13:01',NULL,NULL,17,NULL,'1'),('E002','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','3','2014-04-03 10:13:07',NULL,NULL,17,NULL,'1'),('E003','华泰','2200003333','南京市洪武路华泰证券','2','2014-04-02 15:55:19','2014-04-02 15:55:23',NULL,17,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `oid` varchar(255) DEFAULT NULL,
  `receiveName` varchar(255) DEFAULT NULL,
  `receiveTel` varchar(255) DEFAULT NULL,
  `receiveAddress` varchar(255) DEFAULT NULL,
  `ostatus` varchar(255) DEFAULT NULL,
  `nowAddress` varchar(255) DEFAULT NULL,
  `nowTime` datetime DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `order_eid` (`ename`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,17,'E002','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 12:57:02','1'),(2,17,'E001','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 12:57:03','1'),(3,17,'E001','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 14:56:21','1'),(4,17,'E002','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 14:56:26','1'),(7,17,'E001','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 15:16:01','1'),(8,17,'E002','邢晓琦','15104579686','黑龙江省哈尔滨市和兴路26号','1',NULL,'2014-03-27 15:16:12','1'),(11,17,'E003','华泰','2200003333','南京市洪武路华泰证券','1',NULL,'2014-04-02 15:55:23','邢晓琦');
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-03 10:28:43
