CREATE DATABASE `foode`;

CREATE TABLE `foode`.`users`
(
    `Id`       int          NOT NULL AUTO_INCREMENT,
    `Name`     varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `roule`    int          NOT NULL,
    `isactive` tinyint(1)   NOT NULL DEFAULT '1',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `Name_UNIQUE` (`Name`)
) COMMENT ='Таблица пользователей';

CREATE TABLE `foode`.`posts`
(
    `PostId`     int          NOT NULL AUTO_INCREMENT,
    `UserId`     int          NOT NULL,
    `caption`    varchar(250) NOT NULL,
    `text`       text         NOT NULL,
    `rating`     int                   DEFAULT NULL,
    `visible`    tinyint(1)   NOT NULL DEFAULT '1',
    `datecreate` datetime     NOT NULL,
    PRIMARY KEY (`PostId`)
);

CREATE TABLE `foode`.`comments`
(
    `CommentId`   INT          NOT NULL  AUTO_INCREMENT,
    `PostId`      INT          NOT NULL,
    `UserName`    VARCHAR(50)  NOT NULL,
    `Email`       VARCHAR(45)  NOT NULL,
    `DateCreate`  DATETIME     NULL,
    `TextComment` VARCHAR(500) NULL,
    PRIMARY KEY (`CommentId`)
)
    COMMENT = 'таблица комментариев';

CREATE TABLE `foode`.`profile`
(
    `ProfileId` int  NOT NULL AUTO_INCREMENT,
    `UserId`    int  NOT NULL,
    `Email`     varchar(100) DEFAULT NULL,
    `Photo`     blob,
    `Age`       date         DEFAULT NULL,
    `Sex`       varchar(1)   DEFAULT NULL,
    `DataReg`   date NOT NULL,
    PRIMARY KEY (`ProfileId`)
) COMMENT ='Профили пользователей';

CREATE TABLE `foode`.`rating`
(
    `RatingId`     INT      NOT NULL  AUTO_INCREMENT,
    `PostId`       INT      NOT NULL,
    `UserId`       INT      NOT NULL,
    `ReatingValue` INT      NULL,
    `DateCreate`   DATETIME NULL,
    PRIMARY KEY (`RatingId`)
)
    COMMENT = 'Таблица рейтингов постов';

DROP TRIGGER IF EXISTS `foode`.`rating_BEFORE_INSERT`;

DELIMITER $$
USE `foode`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `rating_BEFORE_INSERT` BEFORE INSERT ON `rating` FOR EACH ROW BEGIN
    set @newRating = (select max(rating) + new.ReatingValue
                      from posts where postid = new.PostId);

    update posts
    set rating = @newRating
    where postid = new.PostId;
END$$
DELIMITER ;
