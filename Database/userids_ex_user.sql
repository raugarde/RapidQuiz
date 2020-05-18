-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: userids
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ex_user`
--

DROP TABLE IF EXISTS `ex_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ex_user` (
  `Id` bigint DEFAULT NULL,
  `fName` varchar(225) DEFAULT NULL,
  `lName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `gender` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Role_Name` varchar(225) DEFAULT NULL,
  `Role_ID` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ex_user`
--

LOCK TABLES `ex_user` WRITE;
/*!40000 ALTER TABLE `ex_user` DISABLE KEYS */;
INSERT INTO `ex_user` VALUES (1,'Admin','Admin','Admin@gmail.com','Pass@123',NULL,'1997-10-06',NULL,NULL,'root','root','2018-12-09 08:07:10','2018-12-09 08:06:48','ADMIN',1),(4,'Raul','Garcia','raulgarciadelapoza@gmail.com','Pass@123',NULL,'1996-02-04',NULL,NULL,'Admin@gmail.com','Admin@gmail.com','2020-05-12 19:34:32','2020-05-12 19:34:41','STUDENT',2),(5,'Student','Random','student@gmail.com','Pass@123',NULL,'1997-09-06',NULL,NULL,'Admin@gmail.com','Admin@gmail.com','2020-05-13 09:29:42','2020-05-13 09:29:42','STUDENT',2),(2,'Teacher','Database','teacherdatabase@gmail.com','Pass@123',NULL,'1956-11-06',NULL,NULL,'root','Admin@gmail.com','2018-12-09 07:07:10','2020-05-14 15:42:14','TEACHER',3),(6,'Teacher','Programming','teacherprogramming@gmail.com','Pass@123',NULL,'1962-05-06',NULL,NULL,'root','Admin@gmail.com','2018-12-09 07:07:10','2020-05-14 13:24:16','TEACHER',3),(7,'Teacher','Security','teachersecurity@gmail.com','Pass@123',NULL,'1967-04-06',NULL,NULL,'root','Admin@gmail.com','2018-12-09 07:07:10','2020-05-14 13:24:24','TEACHER',3);
/*!40000 ALTER TABLE `ex_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-18  3:47:55
