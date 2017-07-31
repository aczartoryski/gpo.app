Generator order production cards - DataBase Schema
==================================================

MySQL SQL :
-----------
```mysql

CREATE DATABASE `gpodb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gpodb`;

CREATE TABLE `field` (
  `fieldID` int(11) NOT NULL AUTO_INCREMENT,
  `fieldValueID` varchar(20) DEFAULT NULL,
  `fieldLabel` varchar(255) NOT NULL,
  `fieldOriginID` varchar(20) NOT NULL,
  `fieldShownInMainTable` tinyint(4) DEFAULT NULL,
  `fieldShownInSecondTable` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`fieldID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orderstatus` (
  `orderStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `orderStatusName` varchar(100) NOT NULL,
  PRIMARY KEY (`orderStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `productionslot` (
  `productionSlotID` int(11) NOT NULL AUTO_INCREMENT,
  `productionSlotDescription` varchar(255) NOT NULL,
  `productionSlotNumber` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`productionSlotID`),
  UNIQUE KEY `productionSlotNumber_UNIQUE` (`productionSlotNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `arrayView` (
  `arrayViewID` int(11) NOT NULL AUTO_INCREMENT,
  `arrayViewName` varchar(30) NOT NULL,
  `arrayViewDescription` VARCHAR(255) NULL DEFAULT NULL,
  `arrayViewForClosed` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`arrayViewID`),
  UNIQUE KEY `arrayViewName_UNIQUE` (`arrayViewName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fieldmapping` (
  `fieldMappingID` int(11) NOT NULL AUTO_INCREMENT,
  `fieldID` int(11) NOT NULL,
  `productionSlotID` int(11) NOT NULL,
  `fieldMappingOrder` int(11) NOT NULL,
  PRIMARY KEY (`fieldMappingID`),
  KEY `fk_field_idx` (`fieldID`),
  KEY `fk_productionSlot_idx` (`productionSlotID`),
  CONSTRAINT `fk_field` FOREIGN KEY (`fieldID`) REFERENCES `field` (`fieldID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_productionSlot` FOREIGN KEY (`productionSlotID`) REFERENCES `productionslot` (`productionSlotID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fieldMappingToView` (
  `fieldMappingToViewID` int(11) NOT NULL AUTO_INCREMENT,
  `fieldID` int(11) NOT NULL,
  `arrayViewID` int(11) NOT NULL,
  `fieldMappingToViewOrder` int(11) NOT NULL,
  PRIMARY KEY (`fieldMappingToViewID`),
  KEY `fk_fieldToview_idx` (`fieldID`),
  KEY `arrayView_idx` (`arrayViewID`),
  CONSTRAINT `fk_fieldToView` FOREIGN KEY (`fieldID`) REFERENCES `field` (`fieldID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_arrayView` FOREIGN KEY (`arrayViewID`) REFERENCES `arrayView` (`arrayViewID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orderitem` (
  `orderItemID` int(11) NOT NULL AUTO_INCREMENT,
  `orderItemName` varchar(255) NOT NULL,
  `orderItemDueDate` date NOT NULL,
  `orderStatusID` int(11) NOT NULL,
  `orderStatusDate` date DEFAULT NULL,
  `orderNumber` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`orderItemID`),
  KEY `fk_orderStatus_idx` (`orderStatusID`),
  CONSTRAINT `fk_orderStatus` FOREIGN KEY (`orderStatusID`) REFERENCES `orderstatus` (`orderStatusID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orderitemfield` (
  `orderItemfieldID` int(11) NOT NULL AUTO_INCREMENT,
  `orderItemID` int(11) NOT NULL,
  `fieldID` int(11) NOT NULL,
  `fieldText` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderItemfieldID`),
  KEY `fk_orderItemKey` (`orderItemID`),
  KEY `fk_fieldKey` (`fieldID`),
  CONSTRAINT `fk_fieldKey` FOREIGN KEY (`fieldID`) REFERENCES `field` (`fieldID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderItemKey` FOREIGN KEY (`orderItemID`) REFERENCES `orderitem` (`orderItemID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `gpodb`.`orderitemfield` DROP FOREIGN KEY `fk_fieldKey`;
ALTER TABLE `gpodb`.`orderitemfield` DROP INDEX `fk_fieldKey` ;
ALTER TABLE `gpodb`.`orderitemfield` DROP FOREIGN KEY `fk_orderItemKey`;
ALTER TABLE `gpodb`.`orderitemfield` DROP INDEX `fk_orderItemKey` ;
ALTER TABLE `gpodb`.`orderitem` CHANGE COLUMN `orderItemDueDate` `orderItemDueDate` DATE NULL ;
ALTER TABLE `gpodb`.`field` 
ADD COLUMN `fieldShownInSecondTable` TINYINT(4) NULL DEFAULT NULL AFTER `fieldShownInMainTable`;

INSERT INTO users(username,password,enabled) VALUES ('admin','<YOUR HASH PASSWORD FOR ADMIN>', true);
INSERT INTO users(username,password,enabled) VALUES ('operator','<YOUR HASH PASSWORD FOR ADMIN>', true);
INSERT INTO user_roles (username, role) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('operator', 'ROLE_USER');
```


