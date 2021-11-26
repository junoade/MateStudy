-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: matestudy-db.czep59ugjex2.ap-northeast-2.rds.amazonaws.com    Database: JungukTest
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `ASSIGN_HW`
--

DROP TABLE IF EXISTS `ASSIGN_HW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ASSIGN_HW` (
  `hwId` bigint NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `instId` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `isDone` bit(1) DEFAULT NULL,
  `lecCode` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `subCode` bigint DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`hwId`),
  KEY `FK8j5oeftxx482x3nqg0a3vpftp` (`instId`,`lecCode`,`subCode`),
  CONSTRAINT `FK8j5oeftxx482x3nqg0a3vpftp` FOREIGN KEY (`instId`, `lecCode`, `subCode`) REFERENCES `TEACH_LECTURE` (`instId`, `lecCode`, `subCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ASSIGN_HW`
--

LOCK TABLES `ASSIGN_HW` WRITE;
/*!40000 ALTER TABLE `ASSIGN_HW` DISABLE KEYS */;
INSERT INTO `ASSIGN_HW` VALUES (3,'2021-11-25 13:11:13','2021-11-25 13:11:13','안녕하세요 테스트 과제입니다.','2021-12-10 23:00:00','2017120002',_binary '\0','CSE4058',1,'테스트 과제1'),(9,'2021-11-25 13:31:30','2021-11-25 13:31:30','1234','2021-11-12 16:34:00','2017120002',_binary '\0','CSE4058',1,'123'),(10,'2021-11-25 13:40:34','2021-11-25 13:40:34','과제과제과제1','2021-11-06 03:42:00','2017120002',_binary '\0','CSE4058',1,'테스트과제1'),(12,'2021-11-25 17:59:08','2021-11-25 17:59:08','되려나','2021-11-26 17:03:00','2017120002',_binary '\0','CSE4058',1,'다시'),(13,'2021-11-25 18:19:12','2021-11-25 18:19:12','MMM','2021-11-27 10:23:00','2017120002',_binary '\0','CSE4058',1,'MMM'),(14,'2021-11-25 18:20:16','2021-11-25 18:20:16','ddd','2021-12-02 10:24:00','2017120002',_binary '\0','CSE4058',1,'ddd'),(16,'2021-11-25 18:44:16','2021-11-25 18:44:16','111','2021-11-26 18:48:00','2017120002',_binary '\0','CSE4036',1,'인공지능 과제'),(37,'2021-11-26 18:27:43','2021-11-26 18:27:43','1','2021-11-24 10:31:00','2017120002',_binary '\0','CSE4036',1,'파일첨부테스트'),(38,'2021-11-26 18:35:27','2021-11-26 18:35:27','222','2021-11-17 10:39:00','2017120002',_binary '\0','CSE4036',1,'파일첨부테스트2'),(40,'2021-11-26 18:39:43','2021-11-26 18:39:43','ㅍㅍㅍ','2021-11-26 18:43:00','2017120002',_binary '\0','CSE4036',1,'ㅍㅍㅍ'),(42,'2021-11-26 18:47:54','2021-11-26 18:47:54','bbbbb','2021-11-26 06:47:00','2017120002',_binary '\0','CSE4036',1,'bbbb'),(44,'2021-11-26 18:58:10','2021-11-26 18:58:10','ㅎㅎㅎㅎ','2021-11-20 09:01:00','2017120002',_binary '\0','CSE4036',1,'ㅎㅎㅎㅎ');
/*!40000 ALTER TABLE `ASSIGN_HW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ASSIGN_HW_FILE`
--

DROP TABLE IF EXISTS `ASSIGN_HW_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ASSIGN_HW_FILE` (
  `Fid` bigint NOT NULL,
  `hwId` bigint NOT NULL,
  PRIMARY KEY (`Fid`,`hwId`),
  KEY `FKs5m2ah10a67seurw8goo2py2i` (`hwId`),
  CONSTRAINT `FKg5asdd29dgdfdl3e5pfn380sb` FOREIGN KEY (`Fid`) REFERENCES `File` (`id`),
  CONSTRAINT `FKs5m2ah10a67seurw8goo2py2i` FOREIGN KEY (`hwId`) REFERENCES `ASSIGN_HW` (`hwId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ASSIGN_HW_FILE`
--

LOCK TABLES `ASSIGN_HW_FILE` WRITE;
/*!40000 ALTER TABLE `ASSIGN_HW_FILE` DISABLE KEYS */;
INSERT INTO `ASSIGN_HW_FILE` VALUES (1,16),(25,16),(34,16),(43,42),(45,44);
/*!40000 ALTER TABLE `ASSIGN_HW_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Board`
--

DROP TABLE IF EXISTS `Board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `content` text COLLATE utf8mb4_bin NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `fileId` bigint DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Board`
--

LOCK TABLES `Board` WRITE;
/*!40000 ALTER TABLE `Board` DISABLE KEYS */;
/*!40000 ALTER TABLE `Board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `File`
--

DROP TABLE IF EXISTS `File`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `File` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `filePath` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `filename` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `originFilename` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `File`
--

LOCK TABLES `File` WRITE;
/*!40000 ALTER TABLE `File` DISABLE KEYS */;
INSERT INTO `File` VALUES (1,'/adsasd','Homework1','Homework1.txt'),(25,'/muyaho','Homework3.txt','Homework3.txt'),(26,'/muyaho','Homework3.txt','Homework3.txt'),(34,'/muyaho','Homework3.txt','Homework3.txt'),(43,'/Users/junguk/IdeaProjects/MateStudy/assignHWFiles/669d8fdf3ddd1692815acccfdff779bb','669d8fdf3ddd1692815acccfdff779bb','파일첨부테스트용.pdf'),(45,'/Users/junguk/IdeaProjects/MateStudy/assignHWFiles/669d8fdf3ddd1692815acccfdff779bb','669d8fdf3ddd1692815acccfdff779bb','파일첨부테스트용.pdf');
/*!40000 ALTER TABLE `File` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Homework`
--

DROP TABLE IF EXISTS `Homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Homework` (
  `hno` bigint NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `dueDate` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`hno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Homework`
--

LOCK TABLES `Homework` WRITE;
/*!40000 ALTER TABLE `Homework` DISABLE KEYS */;
/*!40000 ALTER TABLE `Homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LECTURE`
--

DROP TABLE IF EXISTS `LECTURE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LECTURE` (
  `subCode` bigint NOT NULL,
  `lecCode` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `lecTitle` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`subCode`,`lecCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LECTURE`
--

LOCK TABLES `LECTURE` WRITE;
/*!40000 ALTER TABLE `LECTURE` DISABLE KEYS */;
INSERT INTO `LECTURE` VALUES (1,'CSE4036','2021-11-24 20:16:54','2021-11-24 20:16:54','인공지능'),(1,'CSE4038','2021-11-27 03:08:41','2021-11-27 03:08:41','데이터통신입문'),(1,'CSE4041','2021-11-27 03:07:55','2021-11-24 20:16:54','데이터베이스프로그래밍'),(1,'CSE4058','2021-11-24 20:16:54','2021-11-24 20:16:54','소프트웨어공학개론'),(2,'CSE4036','2021-11-24 20:16:54','2021-11-24 20:16:54','인공지능'),(2,'CSE4038','2021-11-27 03:08:30','2021-11-27 03:08:30','데이터통신입문'),(2,'CSE4041','2021-11-27 03:08:11','2021-11-27 03:05:46','데이터베이스프로그래밍'),(2,'CSE4058','2021-11-27 03:05:20','2021-11-27 03:05:20','소프트웨어공학개론');
/*!40000 ALTER TABLE `LECTURE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBER`
--

DROP TABLE IF EXISTS `MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBER` (
  `id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `pwd` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBER`
--

LOCK TABLES `MEMBER` WRITE;
/*!40000 ALTER TABLE `MEMBER` DISABLE KEYS */;
INSERT INTO `MEMBER` VALUES ('0000000000','2021-11-25 18:46:50','2021-11-24 20:15:09','0000@0000','운영자','000-0000-0000','$2a$10$C/wNkL7IHkbj0xksbRkPguQrVH37pK8v34ZQ/sPQpWrAyl.jOAftm'),('2016112000','2021-11-25 18:46:50','2021-11-24 20:15:08','stdTest2@test.kr','김학생','010-1200-0001','$2a$10$757UcyNy3u6gfuO8IzlY2.MMSxrwErvE7ZlRtIQ9GnsOlFBpHfkb6'),('2016112144','2021-11-27 02:17:20','2021-11-27 02:17:20','al@d.ac.kr','김민석','010-0000-0000','$2a$10$Gu1lwxzWj060MJWjCs30a.DvCJePb/jsVjZEKbIlbPw/N.KlAGUWS'),('2017110000','2021-11-25 18:46:49','2021-11-24 20:15:08','stdTest@test.com','최학생','010-1200-0000','$2a$10$XKC07KlVX8whCYkI8NHsCugukDbCyj5HlcszpG8ke66wJRMcGYViu'),('2017110120','2021-11-25 18:46:44','2021-11-25 18:46:14','010-2987-4527','Wallache Cancellario','wcancellario1r@sciencedaily.com','$2a$10$4E4LBOT7cZTn.Yb/xxkE1.6X.QPVuYPlbWxN.GOkCCjuLQcwEFaWS'),('2017110140','2021-11-25 18:46:35','2021-11-25 18:46:05','010-5376-1203','Derron Goldstone','dgoldstone3@ft.com','$2a$10$1.g2KffEeY5bGrOarQEL7OetnrocWkRxfKPhm41dWpVip9jmmHUxa'),('2017110166','2021-11-25 18:46:43','2021-11-25 18:46:13','010-2107-2619','Anetta Ferraresi','aferraresi1j@miitbeian.gov.cn','$2a$10$wezCamcqUHZQGf7Gxn.EzecSVbA1NAVOQ4Ywvu5Xb4HIFjtiBECua'),('2017110169','2021-11-25 18:46:34','2021-11-25 18:46:05','010-3764-9212','Winnah D\'Aubney','wdaubney0@seattletimes.com','$2a$10$B7IeNJCcmUxhXPlo7eNgdubD1ZBGcKMv/MJ4b9VRNV7OZawjtUp1i'),('2017110199','2021-11-25 18:46:43','2021-11-25 18:46:13','010-9776-7651','Thekla de Lloyd','tde1k@csmonitor.com','$2a$10$VnYlAC91GxyZVXihZkuj3OhWRgey5uhBTdILoFazFsrBJo4vXb5vW'),('2017110315','2021-11-25 18:46:39','2021-11-25 18:46:09','010-7264-5465','Sigfrid Work','sworkt@merriam-webster.com','$2a$10$kzPop83QW8a3yuM5n8ZXo.DkQF1Bmn1.9MUh1/geUTNFT2YhXGNSy'),('2017110398','2021-11-25 18:46:45','2021-11-25 18:46:15','010-4130-7478','Hayden Brechin','hbrechin1x@ibm.com','$2a$10$xI6t8WeTwyMfBvYCnFDv9O3uu1hPjFZRDONWlR.gnUsXiV9yeKL96'),('2017110456','2021-11-25 18:46:42','2021-11-25 18:46:12','010-6967-4296','Farrell Bourbon','fbourbon1e@jugem.jp','$2a$10$meI5gLhVx2tyJfumwlBiSuiJUSCmYOKCh9YB3wB4KpCTd9Mjx4Vgi'),('2017110481','2021-11-25 18:46:36','2021-11-25 18:46:06','010-3984-1283','Jessamyn Chalfain','jchalfaina@cbc.ca','$2a$10$pIZLlXolDl194dp4PHmNiu9dEgvzUW7FNPEGxW9sofPMoOXBcOq6a'),('2017110658','2021-11-25 18:46:49','2021-11-25 18:46:19','010-4320-8482','Carmon Gillard','cgillard2q@bizjournals.com','$2a$10$NzDDRnwDOkFgXjiHybiy1emNcvk.wIwSLIDI.GyzJyVjNAN7EXPdm'),('2017110723','2021-11-25 18:46:45','2021-11-25 18:46:15','010-9286-9810','Ashely Mooreed','amooreed1z@blinklist.com','$2a$10$wu7aFK2BnxbWsuPE7ag46uys8DCds7aTtlCh6maBHa/Zed19GzLqe'),('2017110946','2021-11-25 18:46:37','2021-11-25 18:46:08','010-6681-9799','Clarita Fildes','cfildesl@youku.com','$2a$10$vrrbKQpfYMWjfdKLY3yc/OOyfTzObnOS4Ocs2VVi8fVZSO0p8jJsW'),('2017110951','2021-11-25 18:46:48','2021-11-25 18:46:18','010-4756-7476','Liva Heakey','lheakey2i@printfriendly.com','$2a$10$gzYDfxlrJk1ogz8JEYs3duhAfw/yjlaUaOLOxpuT0OB2pJx91NxJ.'),('2017110971','2021-11-25 18:46:39','2021-11-25 18:46:09','010-1996-0699','Lanie Gatheridge','lgatheridger@hp.com','$2a$10$VlwjaQIem6HmjgXsgdFKvegl85XYhrWnvpyFMzgUH07YObilzWUga'),('2017111043','2021-11-25 18:46:39','2021-11-25 18:46:10','010-6541-7600','Janine Warstall','jwarstally@woothemes.com','$2a$10$tWgWzdd7xewFNhmtZwBDUeeOqWxeIxM6B6LO0cdwMwGamTepeYXHK'),('2017111048','2021-11-25 18:46:37','2021-11-25 18:46:07','010-1108-9981','Giralda Woolbrook','gwoolbrookh@cargocollective.com','$2a$10$.EWLQc3yjI6Oztr2ZXa4ke0GsSONi/rMh.X.ZxVcm.InohLBzUZzO'),('2017111116','2021-11-25 18:46:42','2021-11-25 18:46:11','010-3247-5355','Natassia Langley','nlangley19@goo.ne.jp','$2a$10$2kI5eAwP.Q8458Vs06fGs.8n.haE/mnb/o5uhWNRPmzUVYkm72e3i'),('2017111212','2021-11-25 18:46:49','2021-11-25 18:46:19','010-0340-1245','Celine Kleinstern','ckleinstern2p@ehow.com','$2a$10$g93skhGv5Hjwpw7/iSwBC.FldZCocGSnf3mBQEC0WAhnEXJEOi11e'),('2017111411','2021-11-25 18:46:46','2021-11-25 18:46:16','010-1936-6840','Lamond Backe','lbacke24@ycombinator.com','$2a$10$9QpKFusP3p7UmRnN0xP.nu3eO5gBGX2XH6JpisQAP1Pm1rYaeHzii'),('2017111628','2021-11-25 18:46:46','2021-11-25 18:46:16','010-4842-6032','Coleman Dummett','cdummett22@oakley.com','$2a$10$bXr79YZCXBbD2ex2lNMRjOa60uWSPGHLv0kisiZY.tc89PA2hu7ci'),('2017111638','2021-11-25 18:46:44','2021-11-25 18:46:15','010-0872-1034','Imogen Connerry','iconnerry1u@slate.com','$2a$10$No1zHlp7DRaroqhDinpyAeMxvc1.CpNi3boNveACPiKs5vktQ8X52'),('2017111672','2021-11-25 18:46:35','2021-11-25 18:46:06','010-4800-2894','Laetitia Simister','lsimister6@163.com','$2a$10$x06iuW.uzCOqgXGnzgiUVelkUQPluOVBOPn.iMJ..6p8GpHBe1lIu'),('2017111853','2021-11-25 18:46:35','2021-11-25 18:46:06','010-0890-2881','Justina Rubke','jrubke7@time.com','$2a$10$vih6HqS/ttQa7n4689.HP.FWIB3.BBFGxujCangTtBadKiQDweUSy'),('2017111950','2021-11-25 18:46:46','2021-11-25 18:46:16','010-1888-8957','Antonia Giacobini','agiacobini20@ning.com','$2a$10$pkcxg.T3JB5s6qHltinR7eQFEdAYAKxdlyXw6AAXnn.tMNEESX7sO'),('2017112001','2021-11-25 18:46:50','2021-11-24 20:15:08','stdTest3@test.com','이학생','010-1200-0002','$2a$10$mPc1U1/5kJ2D1emGB6NHV.ViLxyXwQX5QhdbhBl0xe9.XVE7SsvEO'),('2017112002','2021-11-25 18:46:50','2021-11-24 20:15:09','stdTest4@test.com','범학생','010-1200-0003','$2a$10$gsVIpp77GSTljboJ56NXueYG.K3bb0hk6E1QS3rxLpTH3CITHrolu'),('2017112036','2021-11-25 18:46:45','2021-11-25 18:46:15','010-2713-4600','Kimberly Greeve','kgreeve1w@typepad.com','$2a$10$UJUnt6o.JAWgROyclwIGGODjqCcLnR5KQ3Ox6yALyO2uJI7uXVx36'),('2017112083','2021-11-27 02:17:20','2021-11-27 02:17:20','k@g.com','김범규','010-0000-0000','$2a$10$XwYxgxbQwiWRUHdQ.OWmMOz44kYxGz8MRyP5FSxDapldNo06DuwCq'),('2017112095','2021-11-27 02:17:20','2021-11-27 02:17:20','a@g.com','최준호','010-0000-0000','$2a$10$m.bus6L9BwcZHdkhwJt2DuWMsnhDM5ub1zY/5IRRU/bIOhkDqSOLa'),('2017112123','2021-11-25 00:58:06','2021-11-25 00:58:06','123@123','이이이','111-1111-1111','$2a$10$vIYTb1igA1YsxXnogM6N8eKpQ6gGjsZlPm1bJmwpBO/mqpWeil7Ea'),('2017112129','2021-11-27 02:17:20','2021-11-27 02:17:20','j@g.com','이정욱','010-0000-0000','$2a$10$zkzZh093Oq86UDWWlxOqd.ShZyjfkWIGZUFdltkZjUMAawi7svSSa'),('2017112136','2021-11-25 18:46:35','2021-11-25 18:46:06','010-0545-9747','Joanie Batiste','jbatiste4@printfriendly.com','$2a$10$izpCkBFL9vS6GME6sUnyJ..Px2G2eaTPF72YEMTFH52iY5w4R.qze'),('2017112415','2021-11-25 18:46:43','2021-11-25 18:46:12','010-9604-6239','Jobie Laneham','jlaneham1i@friendfeed.com','$2a$10$b/MlIofEnzzrkiiFkdZlT.bXNsVtK8/q9wS6cojIgyd1K8anagI/q'),('2017112487','2021-11-25 18:46:42','2021-11-25 18:46:11','010-3595-6238','Hubert Bolgar','hbolgar1b@xrea.com','$2a$10$uzykOLxX/4hiYW9D6OpJJuiCGxGjcd9Ckf3bOSeF/0/JEmPW2SPuS'),('2017112651','2021-11-25 18:46:36','2021-11-25 18:46:07','010-3579-8474','Birch Holyland','bholylandd@cdc.gov','$2a$10$.L/lZ/nVG5jWr76BQGu1MOxyDIfGAGT5cdhZ777rh2wmC8lbyNZUa'),('2017112720','2021-11-25 18:46:38','2021-11-25 18:46:08','010-3759-4678','Rosie Jefferies','rjefferiesp@smh.com.au','$2a$10$wVWh.otedsHqtWiSYoYs..IIvBKlLn61k7C3GcGglmcdBPpJvx1rS'),('2017112731','2021-11-25 18:46:40','2021-11-25 18:46:10','010-1374-6926','Jeddy Lucian','jlucianz@hud.gov','$2a$10$uu1MNOYmwuzO4GTF9xSs5.wWzH7KHoB71ioORkT2jqBy4MnDwI3kS'),('2017112733','2021-11-25 18:46:37','2021-11-25 18:46:08','010-4218-4719','Walker Abbet','wabbetk@domainmarket.com','$2a$10$fzrt29jOw/aD7lij4EecR.CJ9QXjE4HY56iWGaWum7paewhEUfEmq'),('2017112793','2021-11-25 18:46:36','2021-11-25 18:46:06','010-1908-1430','Malvina Stienton','mstientonb@usa.gov','$2a$10$cEpkuuo7WDQrtedBEjnCve.lfKRvB.z72A/Jh67D4xSS0PmLYHhE.'),('2017112894','2021-11-25 18:46:45','2021-11-25 18:46:15','010-0097-5173','Whitaker Dissman','wdissman1v@si.edu','$2a$10$o3YRyD0wBauvatn5ci524.jCCaj/y8LElOMKFbp7hb5YLwu8u31cS'),('2017113148','2021-11-25 18:46:46','2021-11-25 18:46:16','010-7748-7730','Emlen Haward','ehaward23@csmonitor.com','$2a$10$pz0OIwBbORh4TSJNVPA./.vKhc23F8e/T6eSlNWI1CHTzBt6c05jO'),('2017113382','2021-11-25 18:46:36','2021-11-25 18:46:06','010-5515-1083','Alessandra Mendel','amendel9@ifeng.com','$2a$10$TG9Uuy2vCdga/Q7eSgNnAO677OYM5ilQWjcXP9QlFygVBT2NLrlIu'),('2017113391','2021-11-25 18:46:48','2021-11-25 18:46:17','010-9783-2306','Pip Paulat','ppaulat2d@webeden.co.uk','$2a$10$jmmAbb1RaKrh4kEHNeEb0eVu3m28.lZxnX0oeZ24RUAInw.PZAwhe'),('2017113399','2021-11-25 18:46:41','2021-11-25 18:46:11','010-3441-8674','Ingrim Benediktsson','ibenediktsson17@constantcontact.com','$2a$10$uf3cAvWYAnPiZPQnLpcLWer585RvPUdVb1ixPrfR1zZzF4wMniLni'),('2017113494','2021-11-25 18:46:46','2021-11-25 18:46:16','010-9271-8621','Rogers Enden','renden21@moonfruit.com','$2a$10$qS3zOrumrZxd3ilFqtON1.Vsjhgi.OcGGB4RymV03jaIdbQr3S/xi'),('2017113511','2021-11-25 18:46:34','2021-11-25 18:46:05','010-1247-4161','Ignatius Claxson','iclaxson2@cmu.edu','$2a$10$S3Zerass84GATiFMO8FXIeYwHChi33oFW8GQjlNhhZPqXn8t5sjJ.'),('2017113521','2021-11-25 18:46:48','2021-11-25 18:46:18','010-2716-3905','Nonnah Ivanisov','nivanisov2h@yale.edu','$2a$10$tDuNB2l1.cHzj46urI8OJexfc.Byq802EZYYnD7Z3OcfFfArKzWaO'),('2017113529','2021-11-25 18:46:43','2021-11-25 18:46:13','010-8027-5211','Delores Knappen','dknappen1l@goo.ne.jp','$2a$10$852Gg5HyoDLEO9Ra5XYRTu1SWXQhlN8Et0kincj5aignpsLuXRfHS'),('2017113533','2021-11-25 18:46:40','2021-11-25 18:46:10','010-5404-9944','Reagan Hakking','rhakking11@eventbrite.com','$2a$10$EpCZK2c9mRRkH1mNTh9AOeLALgnpDIlwp4kO2jROn7iSxd2dDBsKy'),('2017113889','2021-11-25 18:46:45','2021-11-25 18:46:15','010-0619-0079','Lil Sammon','lsammon1y@hhs.gov','$2a$10$oqubJ37LPETOchpVwce90.Qly47dDbd/QPbIcbHpeh8PDRGo6aj0a'),('2017114045','2021-11-25 18:46:39','2021-11-25 18:46:09','010-9885-3295','Shina Yelden','syeldenu@addthis.com','$2a$10$mWr5NC3FUAVM0PJ0RAomtOCJlP7ECKFLcCwALnzceOLNwhY9qF2fC'),('2017114055','2021-11-25 18:46:48','2021-11-25 18:46:18','010-4495-4693','Karleen Graves','kgraves2g@usnews.com','$2a$10$Aw7lXVVz1epJdOTr3a/h1OPlgP52KTgMYUY4KGxIFqLdNrDIfHm.i'),('2017114186','2021-11-25 18:46:44','2021-11-25 18:46:14','010-8924-2066','Wald Dodding','wdodding1p@dyndns.org','$2a$10$qZK3syexEwxyThUSqtxLmOUTlbKtIJKDoNYbx.7OhQZldnsFA/oVW'),('2017114344','2021-11-25 18:46:39','2021-11-25 18:46:09','010-2224-8359','Antonin Laye','alayes@hugedomains.com','$2a$10$BM4tzDLq4RF8ZDiFTaOY6eM1uSaxa4e2iFO.10MW1MsTYremMadXi'),('2017114438','2021-11-25 18:46:44','2021-11-25 18:46:14','010-0299-9879','Emlynn Brougham','ebrougham1o@meetup.com','$2a$10$Wgm0xwAdkU4my0CqgOnL8.guNs7SD./soSivB37fut/Qvns1/PdTq'),('2017114452','2021-11-25 18:46:43','2021-11-25 18:46:12','010-2901-7806','Lauretta Bettleson','lbettleson1h@google.com.hk','$2a$10$CpHbEXqweSyYpG0TbTEE0uBP7i0WNwNI4TzOt1Kwk5D7MFxoRrXuO'),('2017114702','2021-11-25 18:46:43','2021-11-25 18:46:12','010-7728-6842','Adriana Hallatt','ahallatt1g@barnesandnoble.com','$2a$10$BkbjLK36TDzZnKloAJ72KOmMXCMNdihQIgjeou.PZtASzLwOIVV9i'),('2017114779','2021-11-25 18:46:47','2021-11-25 18:46:17','010-3979-2129','Liz Jeandet','ljeandet28@utexas.edu','$2a$10$go6ySGIY7Ncc1KIoFF8RMuM0oFNd3OKLLndcUELmv00BWu2YDesBO'),('2017114796','2021-11-25 18:46:44','2021-11-25 18:46:14','010-2347-4719','Nanice Grigoliis','ngrigoliis1t@vistaprint.com','$2a$10$M.dyowV9ke/1N6O.QOB6weLNCwc/Qy/G/m9BXlo/8M2stmw0aO4Vy'),('2017114872','2021-11-25 18:46:46','2021-11-25 18:46:16','010-4079-5828','Earvin Revelle','erevelle25@comsenz.com','$2a$10$h.21rZfPfYMhpNlNaRCY1e4LXWZhimjgIGyJdQNxkQSxZ2FmKtBlG'),('2017114876','2021-11-25 18:46:35','2021-11-25 18:46:06','010-8561-6280','Kendre Parslow','kparslow8@surveymonkey.com','$2a$10$NYFOEKQ5vOcaWAm8bYqFpetv17E5u/FPPF3xV0cM9iUH/Vw1SdNYG'),('2017115102','2021-11-25 18:46:47','2021-11-25 18:46:17','010-8958-7404','Annetta Hall-Gough','ahallgough27@51.la','$2a$10$hpP59DpWDytrpOqajBo7nehy7Pc73AQd0IoMmYXdcduX1Gx8J5dH2'),('2017115147','2021-11-25 18:46:39','2021-11-25 18:46:10','010-6729-6899','Annice Senter','asenterx@google.es','$2a$10$B9S1jPaIdCIRpad7DLUI7urp/utInXGIRHA1LLbuxnFGCvERDCKy6'),('2017115170','2021-11-25 18:46:37','2021-11-25 18:46:07','010-8466-3266','Ara Sprigg','aspriggi@live.com','$2a$10$yHItIATl4nk2SI4ecxEb5ONaXNRWDTKHKB7obcjfF.pZPYeJiVTXC'),('2017115201','2021-11-25 18:46:49','2021-11-25 18:46:19','010-5321-6810','Oralia Cantu','ocantu2o@fastcompany.com','$2a$10$Am8dRP/F.AtAYysn39UHpuW.Ooxpd2jbKbVPWAMXP6a.h3e/.bEr2'),('2017115299','2021-11-25 18:46:39','2021-11-25 18:46:09','010-4372-5605','Yoko Pavie','ypaview@dagondesign.com','$2a$10$L5BCupgbXxqt1fXJe2Hoo.NjlkEIVKklw8zrQnCKEg1J.eDULwdPS'),('2017115707','2021-11-25 18:46:40','2021-11-25 18:46:10','010-1045-6456','Gertrude Fitter','gfitter13@engadget.com','$2a$10$KQKbJwnRItsJbHLTt1R0mez924.FpU1KlTcLI9njf7xUPE1aEqRsi'),('2017115743','2021-11-25 18:46:42','2021-11-25 18:46:12','010-7089-9985','Laryssa Vasyuchov','lvasyuchov1c@xinhuanet.com','$2a$10$NNlg6.I4vrUQQD1UBrb15eIqBNmlhn753o7OLpFqUU/b4KcZuuTTu'),('2017115917','2021-11-25 18:46:37','2021-11-25 18:46:08','010-4459-5313','Iosep Dunguy','idunguyj@about.me','$2a$10$33vy6mAGBKtCUR/xgbCBwOcyAieldogvxBCvhmn82EbfeRTmJwqeO'),('2017116002','2021-11-25 18:46:38','2021-11-25 18:46:08','010-8994-0735','Marcello Drewet','mdrewetq@ibm.com','$2a$10$I3lmx0vKYjny7A/Lc1Sr4.Vpxv1L.z40ByYefBMBIREwcWbvAk0Se'),('2017116113','2021-11-25 18:46:41','2021-11-25 18:46:11','010-1716-2329','Piper Joesbury','pjoesbury14@uiuc.edu','$2a$10$kppaJhJ2/95XMlXFBB3PHeZJ2lzOpMN0mSp0NDs6YtNpuKuZbrEfu'),('2017116126','2021-11-25 18:46:47','2021-11-25 18:46:17','010-8239-5029','Debi Sinnie','dsinnie2c@360.cn','$2a$10$VCByJcEkw9cp8XxUsGCcReGxL4Zci6WOhDB3.vlH7JFTsQN/l/3S6'),('2017116190','2021-11-25 18:46:42','2021-11-25 18:46:12','010-4378-8940','Hy Filippazzo','hfilippazzo1f@unc.edu','$2a$10$yrRHpuS72L6U.5rWPeIFmOJLJwUoB8gByyELP8JSmgS57DrlJCCQq'),('2017116311','2021-11-25 18:46:47','2021-11-25 18:46:17','010-1979-4396','Alard Le Barr','ale2b@cisco.com','$2a$10$xIB/Z4QOjwNyZ6f504G58OeoYMASA.3icWIU6H02q1Qw0WoICYN2i'),('2017116596','2021-11-25 18:46:37','2021-11-25 18:46:07','010-7407-0907','Lara Samways','lsamwaysg@oakley.com','$2a$10$kym4cLC.OXRLE2tgCK90zOf5k1W8nz3MslFuB/3H5FoZWTb3EB6jS'),('2017116658','2021-11-25 18:46:36','2021-11-25 18:46:07','010-2851-8696','Eddie Drever','edreverf@e-recht24.de','$2a$10$6TyC7wWfJuQHNgpPBeYWyeFnLyHqqs2yAB7qBoOJ2I97gJA1j5/Ri'),('2017116723','2021-11-25 18:46:49','2021-11-25 18:46:18','010-2925-5781','Flo Glasbey','fglasbey2m@deviantart.com','$2a$10$bU2Bh8/jfpaLvVRqIIuyD.SykWB2T08pL8QK6YR0bAeTInTk5QTa2'),('2017116779','2021-11-25 18:46:48','2021-11-25 18:46:18','010-0125-0429','Cherlyn Dog','cdog2f@mail.ru','$2a$10$TJL3LbSxw8sEDzV2qvs42eegF2U4Xw4KqrJ6B67IMbp2KcgXvkkse'),('2017116947','2021-11-25 18:46:41','2021-11-25 18:46:11','010-4722-8316','Una Cordsen','ucordsen18@a8.net','$2a$10$kjveaH9ntjE6N.dLs6f1sO2HBB0RQHqgjsSRZUUoswmlJbMDiSVEO'),('2017117262','2021-11-25 18:46:47','2021-11-25 18:46:17','010-4440-2290','Kelby Woolfoot','kwoolfoot29@umn.edu','$2a$10$dOVixYN6Nmq5Gy40BKDjie83pVKrWq8j.wSuSswB91xyYY3qljZLu'),('2017117317','2021-11-25 18:46:44','2021-11-25 18:46:14','010-5706-2429','Tillie Sibbson','tsibbson1q@weather.com','$2a$10$e1n/f8VgBV4coF4MANHza.2J8k9M5mMKJR0ynE8KA4X7Hihirlpwe'),('2017117699','2021-11-25 18:46:44','2021-11-25 18:46:13','010-2209-6979','Ogdan Ambrogi','oambrogi1n@amazon.co.uk','$2a$10$MQcEzqMWvTpe0fU42dIwO.dy5IKMgvJc/MvgMPmUvNMIBn7TwUBdi'),('2017117745','2021-11-25 18:46:42','2021-11-25 18:46:11','010-5271-4407','Culver Nerne','cnerne1a@latimes.com','$2a$10$SULs/KflMwyGyDhMkdeBUuw6pcuHHkjEWSdqyMWskFioZ5gF/cJIi'),('2017117814','2021-11-25 18:46:42','2021-11-25 18:46:12','010-6955-6659','Maribelle Bonallick','mbonallick1d@baidu.com','$2a$10$v/.ihuKxNF2NIzNA6yEg..Fz0b802NcG/rFFgI1xyZ92.b4lAgk7y'),('2017118106','2021-11-25 18:46:35','2021-11-25 18:46:06','010-7286-7343','Gerianne Kobke','gkobke5@admin.ch','$2a$10$A6IubWdqAQFMHrBOS1xN1O5XCX2qyVWJm7q6w0jjccoIDH8LSSXzq'),('2017118160','2021-11-25 18:46:47','2021-11-25 18:46:17','010-0175-9265','Pen Andersson','pandersson2a@acquirethisname.com','$2a$10$7sn8g3swYjJzagp7kJbKY.TxXn8KOAiF9yg6iKdad9jRo/P3ldKPu'),('2017118203','2021-11-25 18:46:40','2021-11-25 18:46:10','010-3190-7302','Melisenda Pietzker','mpietzker12@adobe.com','$2a$10$soC6Y1wbKIy7qP7dvSXr8OrtZ99G6yXnKJaQB6p4R8HfxLR0Lt2Fi'),('2017118269','2021-11-25 18:46:48','2021-11-25 18:46:18','010-3041-3851','Gizela Emeney','gemeney2j@macromedia.com','$2a$10$PPeygHr4GlxKl6bHDF6.k.xemysvt4ISuTrbDrJEW.y.NVkYJkV2K'),('2017118278','2021-11-25 18:46:48','2021-11-25 18:46:17','010-8483-1798','Ola Foster','ofoster2e@over-blog.com','$2a$10$Ln2AS0m7XqWhYAPWsrJmWePZfgPEbS5sw9zRj7PoCzR/BZJs1uzPC'),('2017118296','2021-11-25 18:46:40','2021-11-25 18:46:10','010-2857-3755','Haskell Middas','hmiddas10@sitemeter.com','$2a$10$5DR9CDbr3amCm1E2KSqDk.abFS/hzcjEEYyYy90TOrslbhOgH7NZ.'),('2017118317','2021-11-25 18:46:36','2021-11-25 18:46:07','010-7127-3826','Brigit Lowensohn','blowensohne@instagram.com','$2a$10$DXRj6AqqhU7VZGYd1pZ2Q.sB0pJsmcyCmtF56S1Zlca/scwHFBewm'),('2017118336','2021-11-25 18:46:41','2021-11-25 18:46:11','010-8091-3167','Allissa Kertess','akertess15@fema.gov','$2a$10$SgYzJ846LunlAuLzU38Q7.502gjcvqRSt1ra0KJx1Qgu4.OwJrUyW'),('2017118431','2021-11-25 18:46:38','2021-11-25 18:46:08','010-5773-2388','Tori Lorie','tlorien@yahoo.com','$2a$10$7j9mTsi9k3EcjNbiCLcER.4JHgB/AV.V/.GxTQb3jydxAjbvqejkO'),('2017118466','2021-11-25 18:46:44','2021-11-25 18:46:14','010-5451-5128','Llywellyn Wigginton','lwigginton1s@who.int','$2a$10$u6w8Atmtx5MovviT3AOyge.pxBf91iJuVVScPhxHNdUou94k93aCu'),('2017118607','2021-11-25 18:46:48','2021-11-25 18:46:18','010-2265-0391','Rickie Murray','rmurray2k@reference.com','$2a$10$TukAp78hm.pj469mChp0f.8CzqXNhfhCk/6lP90BIWtW7o6jhMuMW'),('2017118757','2021-11-25 18:46:41','2021-11-25 18:46:11','010-2475-3719','Janean Duignan','jduignan16@google.ru','$2a$10$PLyXIidJtv8b/wh1/xWm5uv0CPdbONymyT.PztvWQ7pnXCU2Rc8X2'),('2017119251','2021-11-25 18:46:43','2021-11-25 18:46:13','010-0069-5318','Tiebold MacAlroy','tmacalroy1m@narod.ru','$2a$10$taY5XVB8N3VcAyB6DTO/a.y835kDdPd0vPbJ.zTJKn73oJnBCuEB6'),('2017119308','2021-11-25 18:46:34','2021-11-25 18:46:05','010-5852-1138','Hubie Billitteri','hbillitteri1@cbslocal.com','$2a$10$CNsXPYuR2SfbPScFQiAaTenRJaqfytUOPOW7fJjGmCGtMs1ml7PMu'),('2017119325','2021-11-25 18:46:38','2021-11-25 18:46:08','010-1941-6173','Courtenay De Vaux','cdeo@issuu.com','$2a$10$zy.dAHapTGO8EQrD2HUqXOqP1d6oW4Im6bq9EqoDGMkBQ/ckCq04u'),('2017119332','2021-11-25 18:46:49','2021-11-25 18:46:18','010-5284-6317','Ham Foster','hfoster2l@parallels.com','$2a$10$lixMNLjXMowJqIIoPoZ3Su.aqZHB3VDoRcJYnt4NKtrWVAme9RhLu'),('2017119426','2021-11-25 18:46:49','2021-11-25 18:46:19','010-1496-7645','Minna Beazey','mbeazey2n@scientificamerican.com','$2a$10$uaYTDtcG.Ko/BLhVjg2hRecHQgTG45fgZ0d5j5AbXeY4H6VsSv5ga'),('2017119503','2021-11-25 18:46:47','2021-11-25 18:46:16','010-5954-4423','Jeralee Bruckental','jbruckental26@cargocollective.com','$2a$10$EmrrMK3olD4R14hND17kPOsMy4Y0jv3H1AhvhGv1hi/YWA.trQrjW'),('2017119738','2021-11-25 18:46:36','2021-11-25 18:46:07','010-9149-7605','Ambrosio Lilie','aliliec@house.gov','$2a$10$bbLgca1.d08NggvEau7EsO6x6/dPsP9wDwHTuvG4N1odQ3ay.SQaW'),('2017119818','2021-11-25 18:46:38','2021-11-25 18:46:08','010-5527-5692','Thomas Hewkin','thewkinm@kickstarter.com','$2a$10$W57NMrHTOPS0xBrDYpmJwOkpu8fzLdmFkMZ4M323KkVWMXJzgVAD2'),('2017119924','2021-11-25 18:46:39','2021-11-25 18:46:09','010-5261-2021','Arturo Pershouse','apershousev@alexa.com','$2a$10$XYaHDPrxqQxKOKu5W9XeBeXCiTxlBW97bPs9STX56.8giPOfObGam'),('2017120000','2021-11-25 18:46:33','2021-11-24 20:15:07','kimProfTest@test.com','김교수','010-1234-5678','$2a$10$OZhWtPc7zzEEQn5Hbrqtk.uKSxOMu0/FQDbxYFaFZ4m6bjzguF7Sy'),('2017120001','2021-11-25 18:46:34','2021-11-24 20:15:08','leeProfTest@test.com','이교수','010-1234-5679','$2a$10$3amoGV0a0pzfAQBk5pSsW../MfFU5gD.X0jDWwJmDvs.PDCkqbX1u'),('2017120002','2021-11-25 18:46:34','2021-11-24 20:15:08','choiProfTest@test.com','최교수','010-1234-5670','$2a$10$9lyn7BnsDY2ZBaLe3OErZOGcCxzvygFpax/e77h.A2fRA.pVkOWk.'),('2017120003','2021-11-25 18:46:34','2021-11-24 20:15:08','kimProfTest@test.com','김교수','010-1234-5671','$2a$10$EzzXlH9Kxo8gnLvRSpE9GODoTIEk.w8aouYk0TY/8zqpm6XWjfgju');
/*!40000 ALTER TABLE `MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member_role`
--

DROP TABLE IF EXISTS `Member_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Member_role` (
  `Member_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `role` int DEFAULT NULL,
  KEY `FK2x1u3al2x8ikors71hdmnmdnf` (`Member_id`),
  CONSTRAINT `FK2x1u3al2x8ikors71hdmnmdnf` FOREIGN KEY (`Member_id`) REFERENCES `MEMBER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member_role`
--

LOCK TABLES `Member_role` WRITE;
/*!40000 ALTER TABLE `Member_role` DISABLE KEYS */;
INSERT INTO `Member_role` VALUES ('2017120000',2),('2017120001',2),('2017120002',2),('2017120003',2),('2017110000',1),('2016112000',1),('2017112001',1),('2017112002',1),('0000000000',3),('2017110169',1),('2017119308',1),('2017113511',1),('2017110140',1),('2017112136',1),('2017118106',1),('2017111672',1),('2017111853',1),('2017114876',1),('2017113382',1),('2017110481',1),('2017112793',1),('2017119738',1),('2017112651',1),('2017118317',1),('2017116658',1),('2017116596',1),('2017111048',1),('2017115170',1),('2017115917',1),('2017112733',1),('2017110946',1),('2017119818',1),('2017118431',1),('2017119325',1),('2017112720',1),('2017116002',1),('2017110971',1),('2017114344',1),('2017110315',1),('2017114045',1),('2017119924',1),('2017115299',1),('2017115147',1),('2017111043',1),('2017112731',1),('2017118296',1),('2017113533',1),('2017118203',1),('2017115707',1),('2017116113',1),('2017118336',1),('2017118757',1),('2017113399',1),('2017116947',1),('2017111116',1),('2017117745',1),('2017112487',1),('2017115743',1),('2017117814',1),('2017110456',1),('2017116190',1),('2017114702',1),('2017114452',1),('2017112415',1),('2017110166',1),('2017110199',1),('2017113529',1),('2017119251',1),('2017117699',1),('2017114438',1),('2017114186',1),('2017117317',1),('2017110120',1),('2017118466',1),('2017114796',1),('2017111638',1),('2017112894',1),('2017112036',1),('2017110398',1),('2017113889',1),('2017110723',1),('2017111950',1),('2017113494',1),('2017111628',1),('2017113148',1),('2017111411',1),('2017114872',1),('2017119503',1),('2017115102',1),('2017114779',1),('2017117262',1),('2017118160',1),('2017116311',1),('2017116126',1),('2017113391',1),('2017118278',1),('2017116779',1),('2017114055',1),('2017113521',1),('2017110951',1),('2017118269',1),('2017118607',1),('2017119332',1),('2017116723',1),('2017119426',1),('2017115201',1),('2017111212',1),('2017110658',1),('2017112095',1),('2016112144',1),('2017112129',1),('2017112083',1);
/*!40000 ALTER TABLE `Member_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBMIT_HW`
--

DROP TABLE IF EXISTS `SUBMIT_HW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SUBMIT_HW` (
  `submitId` bigint NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `instId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `lecCode` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `stId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `subCode` bigint NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `hwId` bigint NOT NULL,
  PRIMARY KEY (`submitId`),
  KEY `FKpoosp90awiy17egaohxr5g60o` (`hwId`),
  KEY `FK4gkm3xgdnaqvr66uoc6ebgrpw` (`instId`,`lecCode`,`stId`,`subCode`),
  CONSTRAINT `FK4gkm3xgdnaqvr66uoc6ebgrpw` FOREIGN KEY (`instId`, `lecCode`, `stId`, `subCode`) REFERENCES `TAKE_LECTURE` (`instId`, `lecCode`, `stId`, `subCode`),
  CONSTRAINT `FKpoosp90awiy17egaohxr5g60o` FOREIGN KEY (`hwId`) REFERENCES `ASSIGN_HW` (`hwId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBMIT_HW`
--

LOCK TABLES `SUBMIT_HW` WRITE;
/*!40000 ALTER TABLE `SUBMIT_HW` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUBMIT_HW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBMIT_HW_FILE`
--

DROP TABLE IF EXISTS `SUBMIT_HW_FILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SUBMIT_HW_FILE` (
  `Fid` bigint NOT NULL,
  `submitId` bigint NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Fid`,`submitId`),
  KEY `FKsdcavbu2xgwvhjkj89llliwh3` (`submitId`),
  CONSTRAINT `FK2o4apul8e809lvdsg5h82r6ty` FOREIGN KEY (`Fid`) REFERENCES `File` (`id`),
  CONSTRAINT `FKsdcavbu2xgwvhjkj89llliwh3` FOREIGN KEY (`submitId`) REFERENCES `SUBMIT_HW` (`submitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBMIT_HW_FILE`
--

LOCK TABLES `SUBMIT_HW_FILE` WRITE;
/*!40000 ALTER TABLE `SUBMIT_HW_FILE` DISABLE KEYS */;
/*!40000 ALTER TABLE `SUBMIT_HW_FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TAKE_LECTURE`
--

DROP TABLE IF EXISTS `TAKE_LECTURE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TAKE_LECTURE` (
  `instId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `lecCode` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `stId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `subCode` bigint NOT NULL,
  PRIMARY KEY (`instId`,`lecCode`,`stId`,`subCode`),
  KEY `FKkdjg2riv7sd3the4kxnlv0fkt` (`stId`),
  KEY `FK7t8764maegd60kh0wgtu5qylg` (`instId`,`lecCode`,`subCode`),
  CONSTRAINT `FK7t8764maegd60kh0wgtu5qylg` FOREIGN KEY (`instId`, `lecCode`, `subCode`) REFERENCES `TEACH_LECTURE` (`instId`, `lecCode`, `subCode`),
  CONSTRAINT `FKkdjg2riv7sd3the4kxnlv0fkt` FOREIGN KEY (`stId`) REFERENCES `MEMBER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TAKE_LECTURE`
--

LOCK TABLES `TAKE_LECTURE` WRITE;
/*!40000 ALTER TABLE `TAKE_LECTURE` DISABLE KEYS */;
INSERT INTO `TAKE_LECTURE` VALUES ('2017120001','CSE4041','2016112144',1),('2017120002','CSE4058','2016112144',1),('2017120003','CSE4038','2016112144',2),('2017120001','CSE4041','2017112083',1),('2017120002','CSE4058','2017112083',1),('2017120003','CSE4038','2017112083',2),('2017120001','CSE4041','2017112095',1),('2017120002','CSE4058','2017112095',1),('2017120003','CSE4038','2017112095',2),('2017120001','CSE4041','2017112129',1),('2017120002','CSE4058','2017112129',1),('2017120003','CSE4038','2017112129',2);
/*!40000 ALTER TABLE `TAKE_LECTURE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEACH_LECTURE`
--

DROP TABLE IF EXISTS `TEACH_LECTURE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEACH_LECTURE` (
  `instId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `lecCode` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `subCode` bigint NOT NULL,
  `modDate` datetime DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  PRIMARY KEY (`instId`,`lecCode`,`subCode`),
  KEY `FKrj6hvu7ikvet1wsxcjqic8kpa` (`subCode`,`lecCode`),
  CONSTRAINT `FK40pmy183huj7c0m20ruhc3lja` FOREIGN KEY (`instId`) REFERENCES `MEMBER` (`id`),
  CONSTRAINT `FKrj6hvu7ikvet1wsxcjqic8kpa` FOREIGN KEY (`subCode`, `lecCode`) REFERENCES `LECTURE` (`subCode`, `lecCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEACH_LECTURE`
--

LOCK TABLES `TEACH_LECTURE` WRITE;
/*!40000 ALTER TABLE `TEACH_LECTURE` DISABLE KEYS */;
INSERT INTO `TEACH_LECTURE` VALUES ('2017120000','CSE4036',2,'2021-11-27 03:12:15','2021-11-27 03:12:15'),('2017120000','CSE4058',2,'2021-11-27 03:12:45','2021-11-27 03:12:45'),('2017120001','CSE4041',1,'2021-11-27 03:13:16','2021-11-27 03:13:16'),('2017120001','CSE4041',2,'2021-11-27 03:13:04','2021-11-27 03:13:04'),('2017120002','CSE4036',1,'2021-11-24 20:17:13','2021-11-24 20:17:13'),('2017120002','CSE4058',1,'2021-11-24 21:05:06','2021-11-24 20:19:13'),('2017120003','CSE4038',1,'2021-11-27 03:13:34','2021-11-27 03:13:34'),('2017120003','CSE4038',2,'2021-11-27 03:13:47','2021-11-27 03:13:47');
/*!40000 ALTER TABLE `TEACH_LECTURE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (46);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'JungukTest'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-27  4:01:48
