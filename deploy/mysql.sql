CREATE DATABASE  IF NOT EXISTS `weibo_backup` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `weibo_backup`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: weibo_backup
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `monitor`
--

DROP TABLE IF EXISTS `monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `weibo_nickname` varchar(100) DEFAULT NULL COMMENT '微博展示名字',
  `weibo_uid` varchar(100) DEFAULT NULL,
  `weibo_url` varchar(200) DEFAULT NULL COMMENT '主页网址',
  `last_weibo_time` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '上一次更新到的那条微博的时间',
  `is_stop` tinyint(4) DEFAULT '0' COMMENT '是否停止监控，0表示不停止，1表示停止',
  `weibo_interface_url` varchar(800) DEFAULT NULL COMMENT '请求的接口地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monitor_weibo`
--

DROP TABLE IF EXISTS `monitor_weibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitor_weibo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monitor_id` int(11) unsigned NOT NULL,
  `weibo_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weibo`
--

DROP TABLE IF EXISTS `weibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weibo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `weibo_nickname` varchar(100) DEFAULT NULL,
  `weibo_text` varchar(600) DEFAULT NULL,
  `weibo_time` datetime DEFAULT NULL,
  `weibo_pics` varchar(1600) DEFAULT NULL,
  `retweet_weibo_id` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-18 19:26:18