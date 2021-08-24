CREATE DATABASE `ID222177_g23` /*!40100 DEFAULT CHARACTER SET latin1 */;

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

CREATE TABLE `Spel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

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

CREATE TABLE `Stapel` (
  `stapelID` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) NOT NULL,
  `spelID` int(11) NOT NULL,
  `spelerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`stapelID`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=latin1;

CREATE TABLE `Stapel_Kaart` (
  `stapelID` int(11) NOT NULL,
  `kaartID` int(11) NOT NULL,
  PRIMARY KEY (`stapelID`,`kaartID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




