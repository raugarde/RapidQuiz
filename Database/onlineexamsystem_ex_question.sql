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
-- Table structure for table `ex_question`
--

DROP TABLE IF EXISTS `ex_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ex_question` (
  `Id` bigint NOT NULL,
  `examName` varchar(225) DEFAULT NULL,
  `questionName` varchar(225) DEFAULT NULL,
  `option1` varchar(225) DEFAULT NULL,
  `option2` varchar(225) DEFAULT NULL,
  `option3` varchar(225) DEFAULT NULL,
  `option4` varchar(225) DEFAULT NULL,
  `correctAns` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `q_type` int DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ex_question`
--

LOCK TABLES `ex_question` WRITE;
/*!40000 ALTER TABLE `ex_question` DISABLE KEYS */;
INSERT INTO `ex_question` VALUES (1,'D Pharma','Question 1','Ans1','Ans2','Ans3','Ans4','Ans1','Admin@gmail.com','Admin@gmail.com','2018-12-09 08:40:34','2018-12-09 08:40:34',1),(2,'Manual testing','Question 2','Ans1','Ans2','Ans3','Ans4','Ans4','Admin@gmail.com','Admin@gmail.com','2018-12-11 18:15:36','2018-12-11 18:15:56',1),(3,'Manual testing','Question 3','Ans1','Ans2','Ans3','Ans4','Ans3','Admin@gmail.com','Admin@gmail.com','2018-12-11 18:16:16','2018-12-11 18:16:16',1),(4,'Java','Pene de Dani','pequeño','grande','feo','bonito','feo','Admin@gmail.com','Admin@gmail.com','2020-04-18 15:04:56','2020-04-18 15:04:56',1),(5,'Manual testing','Test','1','2','3','4','1','Admin@gmail.com','Admin@gmail.com','2020-04-21 11:02:01','2020-04-21 11:02:01',1),(6,'Manual testing','Test2','1','2','3','4','2','Admin@gmail.com','Admin@gmail.com','2020-04-21 11:02:10','2020-04-21 11:02:10',1),(7,'Manual testing','Test3','1','2','3','4','3','Admin@gmail.com','Admin@gmail.com','2020-04-21 11:02:18','2020-04-21 11:02:18',1),(8,'Manual testing','Test4','1','2','3','4','4','Admin@gmail.com','Admin@gmail.com','2020-04-21 11:02:26','2020-04-21 11:02:26',1),(9,'Java','rfer','1','2','3','4','2','Kay@gmail.com','Kay@gmail.com','2020-05-12 19:31:42','2020-05-12 19:31:42',1),(10,'Java Teorical','Que es Java','La vida','La droga','Alcohol','Perras','Alcohol','Admin@gmail.com','Admin@gmail.com','2020-05-12 23:41:09','2020-05-12 23:41:09',1),(11,'Java Teorical','Ole','1','2','3','4','1','teacher@gmail.com','teacher@gmail.com','2020-05-12 23:42:44','2020-05-12 23:42:44',1),(12,'Java Final','Que','Es','Eso','Eso es','Queso','Queso','teacher@gmail.com','teacher@gmail.com','2020-05-13 09:31:59','2020-05-13 09:31:59',1),(13,'Java Final','3','3','1','2','4','3','teacher@gmail.com','teacher@gmail.com','2020-05-13 12:38:52','2020-05-13 12:38:52',1);
/*!40000 ALTER TABLE `ex_question` ENABLE KEYS */;
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
