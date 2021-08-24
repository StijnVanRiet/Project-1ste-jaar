-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: ID222177_g23.db.webhosting.be    Database: ID222177_g23
-- ------------------------------------------------------
-- Server version	5.7.20-18

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
-- Table structure for table `Kaart`
--

DROP TABLE IF EXISTS `Kaart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Kaart` (
  `kaartID` int(11) NOT NULL AUTO_INCREMENT,
  `kaartType` varchar(2) NOT NULL,
  `naam` varchar(64) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `badStuff` varchar(256) DEFAULT NULL,
  `reward` int(11) DEFAULT NULL,
  `equipmentType` varchar(1) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `info` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`kaartID`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Kaart`
--

LOCK TABLES `Kaart` WRITE;
/*!40000 ALTER TABLE `Kaart` DISABLE KEYS */;
INSERT INTO `Kaart` VALUES (1,'E','Helm of Courage',NULL,NULL,NULL,'H',200,'+1 Bonus.'),(2,'E','Pointy Hat of Power',NULL,NULL,NULL,'H',400,'+3 Bonus.'),(3,'E','Bad-Ass Bandana',NULL,NULL,NULL,'H',400,'+3 Bonus. Usable by Human Only.'),(4,'E','Horny Helmet',NULL,NULL,NULL,'H',600,'+1 Bonus. +3 for Elf.'),(5,'E','Flaming Armor',NULL,NULL,NULL,'A',400,'+2 Bonus.'),(6,'E','Short Wide Armor',NULL,NULL,NULL,'A',400,'+3 Bonus. Usable by Dwarf Only.'),(7,'E','Leather Armor',NULL,NULL,NULL,'A',200,'+1 Bonus.'),(8,'E','Slimy Armor',NULL,NULL,NULL,'A',200,'+1 Bonus.'),(9,'E','Mithril Armor',NULL,NULL,NULL,'A',600,'+3 Bonus.'),(10,'E','Boots of Butt-Kicking',NULL,NULL,NULL,'F',400,'+2 Bonus.'),(11,'E','Boots of Running Really Fast',NULL,NULL,NULL,'F',400,'Gives you +2 to Run Away.'),(12,'E','Shield of Ubiquity',NULL,NULL,NULL,'W',600,'+4 Bonus.'),(13,'E','Buckler of Swashing',NULL,NULL,NULL,'W',400,'+2 Bonus.'),(14,'E','Singing & Dancing Sword',NULL,NULL,NULL,'W',400,'+2 Bonus.'),(15,'E','Swiss Army Polearm',NULL,NULL,NULL,'W',600,'+4 Bonus. Usable by Human Only.'),(16,'E','Eleven Foot Pole',NULL,NULL,NULL,'W',200,'+1 Bonus.'),(17,'E','Dagger of Treachery',NULL,NULL,NULL,'W',400,'+3 Bonus.'),(18,'E','Chainsaw of Bloody Dismemberment',NULL,NULL,NULL,'W',600,'+3 Bonus.'),(19,'E','Rapier of Unfairness',NULL,NULL,NULL,'W',600,'+3 Bonus. Usable by Elf Only.'),(20,'E','Cheese Grater of Peace',NULL,NULL,NULL,'W',400,'+3 Bonus.'),(21,'E','Staff of Napalm',NULL,NULL,NULL,'W',800,'+5 Bonus.'),(23,'E','Rat on a Stick',NULL,NULL,NULL,'W',0,'+1 Bonus.'),(24,'E','Tuba of Charm',NULL,NULL,NULL,'W',300,'This melodious instrument captivates your foes. Gives you +3 to Run Away.'),(25,'E','Bow with Ribbons',NULL,NULL,NULL,'W',800,'+4 Bonus. Usable by Elf Only.'),(26,'E','Huge Rock',NULL,NULL,NULL,'W',0,'+3 Bonus.'),(27,'E','Hammer of Kneecapping',NULL,NULL,NULL,'W',600,'+4 Bonus. Usable by Dwarf Only.'),(29,'E','Mace of Sharpness',NULL,NULL,NULL,'W',600,'+4 Bonus.'),(30,'E','Sneaky Bastard Sword',NULL,NULL,NULL,'W',400,'+2 Bonus.'),(31,'TC','Yuppie Water',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +2 in the battle.'),(32,'TC','Magic Missile',NULL,NULL,NULL,NULL,300,'Use during any combat. Usable once only. +5 in the battle.'),(33,'TC','Magic Missile',NULL,NULL,NULL,NULL,300,'Use during any combat. Usable once only. +5 in the battle.'),(34,'TC','Magic Missile',NULL,NULL,NULL,NULL,300,'Use during any combat. Usable once only. +5 in the battle.'),(35,'TC','Potion of Halitosis',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +2 in the battle. Instantly kills the Floating Nose.'),(36,'TC','Nasty-Tasty Sports Drink',NULL,NULL,NULL,NULL,200,'Use during any combat. Usable once only. +2 in the battle.'),(37,'TC','Flaming Poison Potion',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +3 in the battle.'),(38,'TC','Freezing Explosive Potion',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +3 in the battle.'),(39,'TC','Pretty Balloons',NULL,NULL,NULL,NULL,0,'Use during any combat, for distraction. Usable once only. +5 in the battle.'),(40,'TC','Cotion of Ponfusion',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +3 in the battle.'),(41,'TC','Electric Radioactive Acid Potion',NULL,NULL,NULL,NULL,200,'Use during any combat. Usable once only. +5 in the battle.'),(42,'TC','Sleep Potion',NULL,NULL,NULL,NULL,100,'Use during any combat. Usable once only. +2 in the battle.'),(43,'TC','Convenient Addition Error',NULL,NULL,NULL,NULL,0,'Go Up a Level.'),(44,'TC','1000 Gold Pieces',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(45,'TC','Whine At The Gym',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(46,'TC','Bribe GM With Food',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(47,'TC','Boil An Anthill',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(48,'TC','Mutilate The Bodies',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(49,'TC','Invoke Obscure Rules',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(50,'TC','Potion of General Studliness',NULL,NULL,NULL,NULL,0,'Go Up A Level.'),(51,'M','Wannabe Vampire',12,'Blocks the door and tells you about his character. Lose 3 levels.',3,NULL,NULL,''),(54,'M','Maul Rat',1,'She whacks you. Lose 1 level.',1,NULL,NULL,'A creature from Hell.'),(55,'M','Mr. Bones',2,'His bony touch costs you 2 levels.',1,NULL,NULL,'Undead'),(56,'M','Flying Frogs',2,'They bite! Lose 2 levels.',1,NULL,NULL,'-1 to Run Away.'),(59,'M','Potted Plant',1,'None',1,NULL,NULL,'Escape is automatic.'),(60,'M','Gazebo',8,'Lose 3 levels.',2,NULL,NULL,''),(61,'M','Pit Bull',2,'Fang marks in your butt. Lose 2 levels.',1,NULL,NULL,''),(63,'M','Lame Goblin',1,'He whacks you with his crutch. Lose 1 level.',1,NULL,NULL,'+1 to Run Away.'),(67,'M','Large Angry Chicken',2,'Very painful pecking. Lose 1 level.',1,NULL,NULL,''),(69,'M','Harpies',4,'Their music is really, really bad. Lose 2 levels.',2,NULL,NULL,''),(72,'M','Undead Horse',4,'Kicks, bites and smells awful. Lose 2 levels.',2,NULL,NULL,'+5 against Dwarf.'),(86,'R','Dwarf',NULL,NULL,NULL,NULL,NULL,'You can carry 1 extra weapon.'),(87,'R','Dwarf',NULL,NULL,NULL,NULL,NULL,'You can carry 1 extra weapon.'),(88,'R','Dwarf',NULL,NULL,NULL,NULL,NULL,'You can carry 1 extra weapon.'),(89,'R','Halfling',NULL,NULL,NULL,NULL,NULL,'You may sell items each turn for double price.'),(90,'R','Halfling',NULL,NULL,NULL,NULL,NULL,'You may sell items each turn for double price.'),(91,'R','Halfling',NULL,NULL,NULL,NULL,NULL,'You may sell items each turn for double price.'),(92,'R','Elf',NULL,NULL,NULL,NULL,NULL,'+1 to Run Away.'),(93,'R','Elf',NULL,NULL,NULL,NULL,NULL,'+1 to Run Away.'),(94,'R','Elf',NULL,NULL,NULL,NULL,NULL,'+1 to Run Away.'),(99,'C','Curse!',NULL,NULL,NULL,NULL,NULL,'Lose 1 level.'),(100,'C','Curse!',NULL,NULL,NULL,NULL,NULL,'Lose 1 level.'),(102,'C','Curse! Duck of Doom',NULL,NULL,NULL,NULL,NULL,'You should know better than to pick up a duck in a dungeon. Lose 2 levels.'),(106,'DC','Enraged',NULL,NULL,NULL,NULL,NULL,'Play during combat. +5 Monster.'),(107,'DC','Humongous',NULL,NULL,NULL,NULL,NULL,'Play during combat. +10 Monster.'),(108,'DC','Baby',NULL,NULL,NULL,NULL,NULL,'Play during combat. -5 Monster.'),(109,'DC','Intelligent',NULL,NULL,NULL,NULL,NULL,'Play during combat. +5 Monster.'),(110,'DC','Ancient',NULL,NULL,NULL,NULL,NULL,'Play during combat. +10 Monster.');
/*!40000 ALTER TABLE `Kaart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Spel`
--

DROP TABLE IF EXISTS `Spel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Spel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Spel`
--

LOCK TABLES `Spel` WRITE;
/*!40000 ALTER TABLE `Spel` DISABLE KEYS */;
INSERT INTO `Spel` VALUES (36,'fgdjgdgjf001');
/*!40000 ALTER TABLE `Spel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Speler`
--

DROP TABLE IF EXISTS `Speler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Speler` (
  `spelerID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(64) NOT NULL,
  `niveau` int(11) NOT NULL,
  `geslacht` varchar(1) NOT NULL,
  `spelID` int(11) NOT NULL,
  `gold` int(11) NOT NULL,
  `aanBeurt` tinyint(4) NOT NULL,
  PRIMARY KEY (`spelerID`)
) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Speler`
--

LOCK TABLES `Speler` WRITE;
/*!40000 ALTER TABLE `Speler` DISABLE KEYS */;
INSERT INTO `Speler` VALUES (337,'aaaaaaaaaaaa',1,'M',36,0,1),(338,'ccccccccccc',1,'F',36,0,0),(339,'ffffffff',2,'F',36,0,0);
/*!40000 ALTER TABLE `Speler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stapel`
--

DROP TABLE IF EXISTS `Stapel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stapel` (
  `stapelID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) NOT NULL,
  `spelID` int(11) NOT NULL,
  `spelerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`stapelID`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stapel`
--

LOCK TABLES `Stapel` WRITE;
/*!40000 ALTER TABLE `Stapel` DISABLE KEYS */;
INSERT INTO `Stapel` VALUES (223,'hand',36,337),(224,'afgelegd',36,337),(225,'hand',36,338),(226,'afgelegd',36,338),(227,'hand',36,339),(228,'afgelegd',36,339),(229,'kerkerstapel',36,0),(230,'schatstapel',36,0);
/*!40000 ALTER TABLE `Stapel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stapel_Kaart`
--

DROP TABLE IF EXISTS `Stapel_Kaart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stapel_Kaart` (
  `stapelID` int(11) NOT NULL,
  `kaartID` int(11) NOT NULL,
  PRIMARY KEY (`stapelID`,`kaartID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stapel_Kaart`
--

LOCK TABLES `Stapel_Kaart` WRITE;
/*!40000 ALTER TABLE `Stapel_Kaart` DISABLE KEYS */;
INSERT INTO `Stapel_Kaart` VALUES (223,34),(223,50),(223,60),(223,86),(225,3),(225,23),(225,91),(225,100),(227,5),(227,48),(227,88),(227,94),(228,17),(229,51),(229,54),(229,55),(229,56),(229,59),(229,61),(229,63),(229,67),(229,69),(229,72),(229,87),(229,89),(229,90),(229,92),(229,93),(229,99),(229,102),(229,106),(229,107),(229,108),(229,109),(229,110),(230,1),(230,2),(230,4),(230,6),(230,7),(230,8),(230,9),(230,10),(230,11),(230,12),(230,13),(230,14),(230,16),(230,18),(230,19),(230,20),(230,21),(230,25),(230,26),(230,29),(230,32),(230,33),(230,35),(230,37),(230,38),(230,40),(230,41),(230,43),(230,44),(230,45),(230,46),(230,49);
/*!40000 ALTER TABLE `Stapel_Kaart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-14  0:03:45
