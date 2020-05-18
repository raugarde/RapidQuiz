-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: onlineexamsystem
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
-- Table structure for table `ex_exam`
--

DROP TABLE IF EXISTS `ex_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ex_exam` (
  `ID` bigint NOT NULL,
  `examName` varchar(225) DEFAULT NULL,
  `examDate` date DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `examCategory` varchar(225) DEFAULT NULL,
  `subject_fk` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `subject_fk` (`subject_fk`),
  CONSTRAINT `ex_exam_ibfk_1` FOREIGN KEY (`subject_fk`) REFERENCES `userids`.`subject_typology` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ex_exam`
--

LOCK TABLES `ex_exam` WRITE;
/*!40000 ALTER TABLE `ex_exam` DISABLE KEYS */;
INSERT INTO `ex_exam` VALUES (1,'Java','1992-11-11','Admin@gmail.com','Admin@gmail.com','2018-12-09 08:13:36','2018-12-09 08:13:36','Computer',NULL),(2,'D Pharma','2019-02-10','Admin@gmail.com','Admin@gmail.com','2018-12-09 08:39:39','2018-12-09 08:39:39','Madical',NULL),(3,'Manual testing','2018-12-12','Admin@gmail.com','Admin@gmail.com','2018-12-11 18:14:57','2018-12-11 18:14:57','QA',NULL),(4,'Java Teorical','2021-03-05','Admin@gmail.com','Admin@gmail.com','2020-05-12 19:37:48','2020-05-12 19:38:11','Mid-Term',NULL),(5,'Java Practical','2021-08-05','Admin@gmail.com','Admin@gmail.com','2020-05-12 19:38:53','2020-05-12 19:38:53','Practical-Exam',NULL),(6,'Java Final','2020-01-06','Admin@gmail.com','Admin@gmail.com','2020-05-12 19:39:13','2020-05-12 19:39:13','Final Exam',NULL),(7,'Java Test','2020-02-06','teacherdatabase@gmail.com','teacherdatabase@gmail.com','2020-05-14 17:16:43','2020-05-14 17:16:43','Mid-Term',1),(8,'asdasdasd','2020-02-06','teacherdatabase@gmail.com','teacherdatabase@gmail.com','2020-05-14 17:18:31','2020-05-14 17:18:31','Mid-Term',1),(9,'Prueba','2021-02-05','teacherdatabase@gmail.com','teacherdatabase@gmail.com','2020-05-14 17:21:56','2020-05-14 17:21:56','Prueba',1),(10,'Teorical','2020-01-06','teacherdatabase@gmail.com','teacherdatabase@gmail.com','2020-05-14 17:35:42','2020-05-14 17:35:42','Computer',1);
/*!40000 ALTER TABLE `ex_exam` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-18  3:47:56
