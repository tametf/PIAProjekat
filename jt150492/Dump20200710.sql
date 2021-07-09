CREATE DATABASE  IF NOT EXISTS `projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projekat`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: projekat
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `detalji`
--

DROP TABLE IF EXISTS `detalji`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalji` (
  `idDetalja` int NOT NULL,
  `idProdavnice` int NOT NULL,
  `korisnickoImePolj` varchar(45) NOT NULL,
  `ocena` int NOT NULL,
  `komentar` varchar(100) NOT NULL,
  PRIMARY KEY (`idDetalja`),
  UNIQUE KEY `unique` (`idProdavnice`,`korisnickoImePolj`) /*!80000 INVISIBLE */,
  KEY `fk2_detalji_idx` (`korisnickoImePolj`) /*!80000 INVISIBLE */,
  KEY `fk1_detalji_idx` (`idProdavnice`),
  CONSTRAINT `fk1_detalji` FOREIGN KEY (`idProdavnice`) REFERENCES `onlineprodavnica` (`idProdavnice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2_detalji` FOREIGN KEY (`korisnickoImePolj`) REFERENCES `poljoprivrednik` (`korisnickoImePolj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalji`
--

LOCK TABLES `detalji` WRITE;
/*!40000 ALTER TABLE `detalji` DISABLE KEYS */;
INSERT INTO `detalji` VALUES (7,15,'tamara',5,'Odlican'),(9,24,'tamara',5,'Dobra!'),(10,19,'tamara',5,'Izuzetno kvalitetan proizvod!'),(11,2,'tamara',5,'Prezadovoljna sam ovim proizvodom!');
/*!40000 ALTER TABLE `detalji` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `korisnickoIme` varchar(45) NOT NULL,
  `lozinka` varchar(45) NOT NULL,
  `tip` varchar(45) NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES ('admin','Admin123!','admin',1),('brankica','Brankica123!','poljoprivrednik',1),('BreskvaDOO','Breskva123!','preduzetnik',1),('cvetic','Cvet123!','preduzetnik',1),('dunja','Dunja123!','poljoprivrednik',0),('dusan','Dusan123!','poljoprivrednik',0),('gojko','Gojko123!','poljoprivrednik',1),('jelica','Jelica123!','poljoprivrednik',1),('kosta','Kosta123!','poljoprivrednik',0),('lena','Lena123!','poljoprivrednik',0),('marko','Marko123!','poljoprivrednik',1),('milica','Milica123!','poljoprivrednik',0),('pavle','Pavle123!','poljoprivrednik',1),('preduzece','Preduzece123!','preduzetnik',0),('Preduzece Crljenacki Vinogradi','Crljeni123!','preduzetnik',1),('Preduzece Novosadnski Vinogradi','Vinograd123!','preduzetnik',1),('Rasa','Rasa123!','preduzetnik',0),('Rasadnik Hrizantema Petrovic','Hrizantema123!','preduzetnik',1),('Rasadnik Ruza Petrovic','Ruza123!','preduzetnik',0),('Rasadnik Svemirko','Svemirko123!','preduzetnik',1),('sale','Sale123!','poljoprivrednik',0),('sofija','Sofija123!','preduzetnik',1),('tamara','Tam123!','poljoprivrednik',1),('VasRasadnik','Rasadnik123!','preduzetnik',0);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magacin`
--

DROP TABLE IF EXISTS `magacin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magacin` (
  `idMagacina` int NOT NULL,
  `idProdavnice` int NOT NULL,
  `idRasadnika` int NOT NULL,
  `kolicina` int NOT NULL,
  PRIMARY KEY (`idMagacina`),
  UNIQUE KEY `fk3_magacin_idx` (`idMagacina`),
  KEY `fk1_magacin_idx` (`idRasadnika`) /*!80000 INVISIBLE */,
  KEY `fk2_magacin_idx` (`idProdavnice`),
  CONSTRAINT `fk1_magacin` FOREIGN KEY (`idRasadnika`) REFERENCES `rasadnik` (`idRasadnika`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2_magacin` FOREIGN KEY (`idProdavnice`) REFERENCES `onlineprodavnica` (`idProdavnice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magacin`
--

LOCK TABLES `magacin` WRITE;
/*!40000 ALTER TABLE `magacin` DISABLE KEYS */;
INSERT INTO `magacin` VALUES (4,19,4,2),(5,15,4,10),(7,2,7,4),(8,18,7,2),(9,15,7,1),(10,20,7,7),(12,19,7,10),(13,15,5,6),(14,19,5,11),(15,18,5,5),(16,18,4,11),(24,20,4,4),(25,24,4,7),(26,25,20,105),(27,26,19,200),(28,15,20,4),(29,26,4,461),(30,2,4,55);
/*!40000 ALTER TABLE `magacin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzbine`
--

DROP TABLE IF EXISTS `narudzbine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `narudzbine` (
  `idNarudzbine` int NOT NULL,
  `idRasadnika` int NOT NULL,
  `datum` date DEFAULT NULL,
  `nazivProizvoda` varchar(45) NOT NULL,
  `tipProizvoda` varchar(45) NOT NULL,
  `preduzece` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `brojNarucenih` int NOT NULL,
  PRIMARY KEY (`idNarudzbine`),
  KEY `fk1_narudzbine_idx` (`idRasadnika`),
  CONSTRAINT `fk1_narudzbine` FOREIGN KEY (`idRasadnika`) REFERENCES `rasadnik` (`idRasadnika`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzbine`
--

LOCK TABLES `narudzbine` WRITE;
/*!40000 ALTER TABLE `narudzbine` DISABLE KEYS */;
INSERT INTO `narudzbine` VALUES (6,4,'2020-07-05','Preparat1','preparat','sofija','isporucena',10),(7,4,'2020-07-05','ruzica','sadnica','sofija','isporucena',10),(8,7,'2020-07-07','Sadnica1','sadnica','sofija','isporucena',2),(9,7,'2020-07-07','paprika','sadnica','sofija','isporucena',2),(10,7,'2020-07-07','ruzica','sadnica','sofija','isporucena',1),(11,7,'2020-07-07','krastavac','sadnica','sofija','isporucena',1),(12,7,'2020-07-07','Preparat1','preparat','sofija','isporucena',5),(13,7,'2020-07-07','paradajiz','sadnica','sofija','isporucena',5),(14,7,'2020-07-07','Preparat1','preparat','sofija','isporucena',3),(15,7,'2020-07-07','krastavac','sadnica','sofija','isporucena',5),(16,7,'2020-07-07','Sadnica1','sadnica','sofija','isporucena',0),(19,5,'2020-07-08','ruzica','sadnica','sofija','isporucena',3),(20,5,'2020-07-08','paprika','sadnica','sofija','isporucena',2),(21,5,'2020-07-08','paradajiz','sadnica','sofija','isporucena',5),(22,5,'2020-07-08','ruzica','sadnica','sofija','isporucena',3),(23,5,'2020-07-08','paprika','sadnica','sofija','isporucena',3),(24,5,'2020-07-08','paradajiz','sadnica','sofija','isporucena',6),(25,4,'2020-07-08','Preparat1','preparat','sofija','isporucena',1000),(26,4,'2020-07-08','Preparat1','preparat','sofija','isporucena',10),(27,4,'2020-07-08','ruzica','sadnica','sofija','na cekanju',1000),(28,4,'2020-07-08','Preparat1','preparat','sofija','isporucena',3),(29,4,'2020-07-08','Sadnica1','sadnica','sofija','isporucena',55),(30,4,'2020-07-08','paprika','sadnica','sofija','isporucena',5),(31,4,'2020-07-08','krastavac','sadnica','sofija','isporucena',6),(32,4,'2020-07-08','paprika','sadnica','sofija','isporucena',10),(45,4,'2020-07-10','kruska','sadnica','sofija','isporucena',5),(46,4,'2020-07-10','kruska','sadnica','sofija','isporucena',2),(47,4,'2020-07-10','Preparat1','preparat','sofija','isporucena',200),(49,19,'2020-07-10','Sadnica1','sadnica','sofija','isporuka u toku',2),(50,19,'2020-07-10','paradajiz','sadnica','sofija','isporuka u toku',2),(51,19,'2020-07-10','Preparat1','preparat','sofija','isporucena',200),(52,20,'2020-07-10','ruzica','sadnica','sofija','isporucena',4),(53,20,'2020-07-10','preparat2','preparat','sofija','isporucena',5),(54,20,'2020-07-10','preparat2','preparat','sofija','isporucena',100),(55,21,'2020-07-10','Sadnica1','sadnica','sofija','na cekanju',30),(56,21,'2020-07-10','Preparat1','preparat','sofija','na cekanju',400),(57,22,'2020-07-10','Sadnica1','sadnica','sofija','na cekanju',3),(58,22,'2020-07-10','ruzica','sadnica','sofija','na cekanju',3),(59,22,'2020-07-10','paprika','sadnica','sofija','na cekanju',3),(60,4,'2020-07-10','Preparat1','preparat','sofija','isporucena',300);
/*!40000 ALTER TABLE `narudzbine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `onlineprodavnica`
--

DROP TABLE IF EXISTS `onlineprodavnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `onlineprodavnica` (
  `idProdavnice` int NOT NULL AUTO_INCREMENT,
  `korisnickoImePred` varchar(45) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `kolicina` varchar(45) NOT NULL,
  `tip` varchar(45) NOT NULL,
  `uputstvo` int DEFAULT NULL,
  `jedinicnaCena` int NOT NULL,
  `srednjaOcena` decimal(5,2) NOT NULL,
  `idProizvoda` int NOT NULL,
  PRIMARY KEY (`idProdavnice`),
  KEY `fk1_onlineProdavnica_idx1` (`korisnickoImePred`),
  KEY `fk2_onlineprodavnica_idx` (`idProizvoda`),
  CONSTRAINT `fk1_onlineProdavnica` FOREIGN KEY (`korisnickoImePred`) REFERENCES `preduzece` (`korisnickoImePred`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onlineprodavnica`
--

LOCK TABLES `onlineprodavnica` WRITE;
/*!40000 ALTER TABLE `onlineprodavnica` DISABLE KEYS */;
INSERT INTO `onlineprodavnica` VALUES (2,'sofija','Sadnica1','3935','sadnica',NULL,15,5.00,2),(15,'sofija','ruzica','731','sadnica',NULL,8,5.00,15),(18,'sofija','paprika','429','sadnica',NULL,100,5.00,18),(19,'sofija','paradajiz','445','sadnica',NULL,100,5.00,19),(20,'sofija','krastavac','514','sadnica',NULL,100,5.00,20),(22,'sofija','djubrivo','48220','preparat',10,500,5.00,22),(24,'sofija','kruska','3','sadnica',NULL,200,5.00,23),(25,'sofija','preparat2','895','preparat',20,200,5.00,25),(26,'sofija','Preparat1','19300','preparat',10,500,5.00,26),(27,'sofija','breza','500','sadnica',NULL,1000,5.00,27);
/*!40000 ALTER TABLE `onlineprodavnica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poljoprivrednik`
--

DROP TABLE IF EXISTS `poljoprivrednik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poljoprivrednik` (
  `korisnickoImePolj` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `datumRodjenja` date NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mestoRodjenja` varchar(45) NOT NULL,
  PRIMARY KEY (`korisnickoImePolj`),
  CONSTRAINT `fk1_poljoprivrednik` FOREIGN KEY (`korisnickoImePolj`) REFERENCES `korisnik` (`korisnickoIme`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poljoprivrednik`
--

LOCK TABLES `poljoprivrednik` WRITE;
/*!40000 ALTER TABLE `poljoprivrednik` DISABLE KEYS */;
INSERT INTO `poljoprivrednik` VALUES ('brankica','Brankica','Sebastijanovic','1993-05-05','0645454545','brankica@gmail.com','Beograd'),('dunja','Dunja','Racic','1996-09-03','0644587884','dunja@gmail.com','Banja Luka'),('dusan','Dusan','Popadic','1995-12-28','0645689654','dusan@gmail.com','Beograd'),('gojko','Gojko','Jevdjevic','1970-01-25','0654578236','gojko@gmail.com','Lazarevac'),('jelica','Jelica','Jevdjevic','1972-09-12','0645568953','jelica@gmail.com','Obrenovac'),('kosta','Kosta','Hrenic','1994-01-01','0658989565','kosta@gmail.com','Beograd'),('lena','Lena','Trifunovic','2002-06-15','0645655896','lena@gmail.com','Lazarevac'),('marko','Marko','Micovic','1997-04-24','0601245895','marko@gmail.com','Beograd'),('milica','Milica','Milic','2002-03-20','0645889558','milica@gmail.com','Trebinje'),('pavle','Pavle','Jevdjevic','1998-08-27','0654578965','pavle@gmail.com','Beograd'),('sale','Aleksandar','Djordjevic','1996-02-26','0645223147','sale@gmail.com','Novi Sad'),('tamara','Tamara','Jevdjevic','1996-01-14','0644521193','tamara@gmail.com','Lazarevac');
/*!40000 ALTER TABLE `poljoprivrednik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preduzece`
--

DROP TABLE IF EXISTS `preduzece`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preduzece` (
  `korisnickoImePred` varchar(45) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `datum` date NOT NULL,
  `mesto` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`korisnickoImePred`),
  CONSTRAINT `fk1_preduzece` FOREIGN KEY (`korisnickoImePred`) REFERENCES `korisnik` (`korisnickoIme`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preduzece`
--

LOCK TABLES `preduzece` WRITE;
/*!40000 ALTER TABLE `preduzece` DISABLE KEYS */;
INSERT INTO `preduzece` VALUES ('BreskvaDOO','breskva','2000-01-01','Ivanjica','breskva@gmail.com'),('cvetic','cvet','2000-01-01','Lazarevac','cvet@gmail.com'),('preduzece','preduzece','1564-01-01','Vreoci','preduzece@gmail.com'),('Preduzece Crljenacki Vinogradi','Preduzece Crljenacki Vinogradi','2020-01-01','Veliki Crljeni','velikicrljeni@gmail.com'),('Preduzece Novosadnski Vinogradi','Preduzece Novosadnski Vinogradi','2020-01-01','Novi Sad','vinograd@gmail.com'),('Rasadnik Hrizantema Petrovic','Rasadnik Hrizantema Petrovic','2020-01-01','Beograd','hrizantema@gmail.com'),('Rasadnik Ruza Petrovic','Rasadnik Ruza Petrovic','2019-01-01','Beograd','ruza@gmail.com'),('Rasadnik Svemirko','svemirko','1995-01-01','Surdulica','svemirko@gmail.com'),('sofija','Sofijino','2020-10-01','Beograd','sofija@gmail.com'),('VasRasadnik','rasadnik','2000-01-01','Beograd','rasadnik@gmail.com');
/*!40000 ALTER TABLE `preduzece` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proizvod`
--

DROP TABLE IF EXISTS `proizvod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proizvod` (
  `idProizvoda` int NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `kolicina` int DEFAULT NULL,
  `tip` varchar(45) NOT NULL,
  `idMagacina` int NOT NULL,
  `proizvodjac` varchar(45) NOT NULL,
  PRIMARY KEY (`idProizvoda`),
  KEY `fk1_proizvod` (`idMagacina`),
  KEY `fk2_proizvod` (`proizvodjac`),
  CONSTRAINT `fk1_proizvod` FOREIGN KEY (`idMagacina`) REFERENCES `magacin` (`idMagacina`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2_proizvod` FOREIGN KEY (`proizvodjac`) REFERENCES `preduzece` (`korisnickoImePred`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proizvod`
--

LOCK TABLES `proizvod` WRITE;
/*!40000 ALTER TABLE `proizvod` DISABLE KEYS */;
/*!40000 ALTER TABLE `proizvod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rasadnik`
--

DROP TABLE IF EXISTS `rasadnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rasadnik` (
  `idRasadnika` int NOT NULL AUTO_INCREMENT,
  `korisnickoImePolj` varchar(45) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `mesto` varchar(45) NOT NULL,
  `brZasadjenih` int NOT NULL,
  `brSlobodnih` int DEFAULT NULL,
  `voda` int DEFAULT '200',
  `temperatura` decimal(5,2) DEFAULT NULL,
  `duzina` int NOT NULL,
  `sirina` int NOT NULL,
  PRIMARY KEY (`idRasadnika`),
  KEY `fk1_rasadnik_idx1` (`korisnickoImePolj`),
  CONSTRAINT `fk1_rasadnik` FOREIGN KEY (`korisnickoImePolj`) REFERENCES `poljoprivrednik` (`korisnickoImePolj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rasadnik`
--

LOCK TABLES `rasadnik` WRITE;
/*!40000 ALTER TABLE `rasadnik` DISABLE KEYS */;
INSERT INTO `rasadnik` VALUES (4,'tamara','RasadnikTamara','Beograd',5,4,60,46.50,3,3),(5,'tamara','dodatGUI','BGD',0,25,165,0.00,5,5),(6,'tamara','test','test',0,100,166,15.00,10,10),(7,'tamara','rasadnik cveca','bgd',1,3,166,11.00,2,2),(8,'tamara','rasadnik tikvica','Novi Sad',0,9,166,10.00,3,3),(10,'tamara','Rasadnik Lubenica','Kikinda',0,16,184,10.00,4,4),(11,'tamara','Rasadnik Malina','Krusevac',0,9,198,17.00,3,3),(12,'tamara','Rasa','Krusevac',0,9,198,17.00,3,3),(13,'tamara','Rasadnik Kupina','Kragujevac',0,9,198,17.00,3,3),(14,'tamara','Jabuke','Rakovica',0,4,198,17.00,2,2),(17,'tamara','Rasadnik Malinjak','Krusevac',0,4,198,17.00,2,2),(18,'tamara','Rasadnik krompira','Sabac',0,9,198,17.00,3,3),(19,'pavle','Pavlov rasadnik','Beograd',0,9,199,17.50,3,3),(20,'jelica','Jelicin Rasadnik','Beograd',0,9,199,17.50,3,3),(21,'gojko','Gojkov Rasadnik','Beograd',0,9,199,17.50,3,3),(22,'marko','Markov Rasadnik','Vidikovac',0,9,200,18.00,3,3);
/*!40000 ALTER TABLE `rasadnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sadnicezasadjene`
--

DROP TABLE IF EXISTS `sadnicezasadjene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sadnicezasadjene` (
  `idSadnice` int NOT NULL AUTO_INCREMENT,
  `idMagacina` int NOT NULL,
  `napredovanje` int NOT NULL,
  `red` int NOT NULL,
  `kolona` int NOT NULL,
  PRIMARY KEY (`idSadnice`),
  KEY `fk1_sadnice_idx` (`idMagacina`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk1_sadnice` FOREIGN KEY (`idMagacina`) REFERENCES `magacin` (`idMagacina`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sadnicezasadjene`
--

LOCK TABLES `sadnicezasadjene` WRITE;
/*!40000 ALTER TABLE `sadnicezasadjene` DISABLE KEYS */;
INSERT INTO `sadnicezasadjene` VALUES (29,9,22,0,1),(36,5,0,0,1),(37,24,0,0,0),(38,16,0,0,0),(39,16,0,0,0),(40,16,100,0,2);
/*!40000 ALTER TABLE `sadnicezasadjene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stavka` (
  `idStavke` int NOT NULL AUTO_INCREMENT,
  `idProdavnice` int NOT NULL,
  `idNarudzbine` int NOT NULL,
  `kolicina` int NOT NULL,
  PRIMARY KEY (`idStavke`),
  KEY `fk1_stavka_idx` (`idNarudzbine`) /*!80000 INVISIBLE */,
  KEY `fk2_stavka_idx` (`idProdavnice`),
  CONSTRAINT `fk1_stavka` FOREIGN KEY (`idProdavnice`) REFERENCES `onlineprodavnica` (`idProdavnice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2_stavka` FOREIGN KEY (`idNarudzbine`) REFERENCES `narudzbine` (`idNarudzbine`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-10 16:32:51
