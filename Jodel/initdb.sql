CREATE SCHEMA IF NOT EXISTS `sebuit02` DEFAULT CHARACTER SET utf8 ;
USE `sebuit02` ;

-- -----------------------------------------------------
-- Table `sebuit02`.`TUSER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sebuit02`.`TUSER` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(128) NULL,
  `EMAIL` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sebuit02`.`POST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sebuit02`.`POST` (
  `ID` INT NOT NULL,
  `TEXT` VARCHAR(45) NULL,
  `LONGITUDE` DECIMAL(10) NULL,
  `LATITUDE` DECIMAL(10) NULL,
  `POSTEDAT` DATETIME NULL,
  `AUTHORID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_POST_TUSER_idx` (`AUTHORID` ASC),
  CONSTRAINT `fk_POST_TUSER`
    FOREIGN KEY (`AUTHORID`)
    REFERENCES `sebuit02`.`TUSER` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sebuit02`.`COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sebuit02`.`COMMENT` (
  `ID` INT NOT NULL,
  `TEXT` MEDIUMTEXT NULL,
  `LONGITUDE` DECIMAL(10) NULL,
  `LATITUDE` DECIMAL(10) NULL,
  `POSTEDAT` DATETIME NULL,
  `AUTHORID` INT NOT NULL,
  `POSTID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_COMMENT_TUSER1_idx` (`AUTHORID` ASC),
  INDEX `fk_COMMENT_POST1_idx` (`POSTID` ASC),
  CONSTRAINT `fk_COMMENT_TUSER1`
    FOREIGN KEY (`AUTHORID`)
    REFERENCES `sebuit02`.`TUSER` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_COMMENT_POST1`
    FOREIGN KEY (`POSTID`)
    REFERENCES `sebuit02`.`POST` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `sebuit02`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS sebuit02.RATING (
  ID INT NOT NULL,
  UserID INT(11) NULL,
  CommentID INT(11) NULL,
  Rating INT NULL,
  PRIMARY KEY (ID),
  INDEX fk_RATING_TUSER_idx (UserID ASC),
  INDEX fk_RATING_COMMENT_idx (CommentID ASC),
  CONSTRAINT fk_RATING_TUSER
    FOREIGN KEY (UserID)
    REFERENCES sebuit02.TUSER (ID)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_RATING_COMMENT
    FOREIGN KEY (CommentID)
    REFERENCES sebuit02.COMMENT (ID)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
-- -----------------------------------------------------
-- Table `sebuit02`.`Sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sebuit02`.`SEQUENCE` (
  `SEQ_NAME` varchar(255) NOT NULL,
  `SEQ_COUNT` int(11) NOT NULL,
  PRIMARY KEY (`SEQ_NAME`)
  )ENGINE=InnoDB DEFAULT CHARSET=UTF8;
  
INSERT INTO sebuit02.SEQUENCE (SEQ_NAME,SEQ_COUNT) VALUES ('TUSER',1);
INSERT INTO sebuit02.SEQUENCE (SEQ_NAME,SEQ_COUNT) VALUES ('TPOST',1);
INSERT INTO sebuit02.SEQUENCE (SEQ_NAME,SEQ_COUNT) VALUES ('TCOMMENT',1);
INSERT INTO sebuit02.SEQUENCE (SEQ_NAME,SEQ_COUNT) VALUES ('TRating',1);