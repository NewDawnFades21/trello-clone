/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 8.0.15 : Database - todo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`todo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `todo`;

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `card_id` bigint(11) DEFAULT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `file_card_fk` (`card_id`),
  KEY `file_user_fk` (`user_id`),
  CONSTRAINT `file_card_fk` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `file_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `attachment` */

insert  into `attachment`(`id`,`filename`,`path`,`card_id`,`user_id`,`create_time`) values (3,'image.jpg','C:\\uploads\\image.jpg',13,2,'2020-06-08 11:03:40'),(5,'giphy(2).gif','C:\\uploads\\giphy(2).gif',13,2,'2020-06-08 12:52:28'),(6,'OrTqoCX.gif','C:\\uploads\\OrTqoCX.gif',13,2,'2020-06-08 12:55:14'),(7,'giphy(3).gif','C:\\uploads\\giphy(3).gif',28,2,'2020-06-08 15:48:25');

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `visibility` tinyint(1) NOT NULL COMMENT '可见性',
  `user_id` bigint(11) NOT NULL COMMENT '外键，和uer表的id关联',
  `submit_date` datetime NOT NULL COMMENT '提交时间',
  `team_id` bigint(50) DEFAULT NULL COMMENT '外键，team表id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `board_ibfk_1` (`user_id`) USING BTREE,
  KEY `team_id` (`team_id`) USING BTREE,
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `board_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `board` */

insert  into `board`(`id`,`title`,`visibility`,`user_id`,`submit_date`,`team_id`) values (17,'这是一个网页测试',1,2,'2020-05-13 21:32:15',1),(18,'这是一个网页测试2',1,4,'2020-05-14 07:35:32',1),(19,'这是一个网页测试3',1,4,'2020-05-14 08:12:04',2),(20,'这是一个网页测试4',1,4,'2020-05-14 13:46:30',3),(21,'这是一个网页测试5',1,4,'2020-05-14 13:54:11',1),(23,'这是一个网页测试7',1,1,'2020-05-14 14:06:55',1);

/*Table structure for table `card` */

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL COMMENT 'card的title',
  `deck_id` bigint(11) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`),
  KEY `deck_card_fk` (`deck_id`),
  CONSTRAINT `deck_card_fk` FOREIGN KEY (`deck_id`) REFERENCES `deck` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `card` */

insert  into `card`(`id`,`title`,`deck_id`,`description`) values (2,'1.环境搭建wtf',1,'hello world'),(3,'2.复习Java语言',1,NULL),(4,'1.操作系统基础',2,NULL),(5,'damn you moron dam',3,NULL),(6,'3.1',4,NULL),(7,'哈哈哈哈',3,NULL),(8,'hello',5,NULL),(9,'3.2',4,NULL),(10,'4.2',5,NULL),(11,'4.3',5,NULL),(12,'3.3',4,NULL),(13,'2.1',3,'emmmm666666666'),(14,'2.2',3,NULL),(15,'4.4',5,NULL),(16,'3.4',4,NULL),(17,'2.3',3,NULL),(18,'2.4',3,NULL),(19,'3.5',4,NULL),(20,'3.whatever',1,NULL),(21,'4.nonono',1,NULL),(22,'5.1',12,NULL),(23,'5.2',12,NULL),(24,'5.3',12,NULL),(25,'3.6',4,NULL),(26,'5.ohhhhhhh',1,NULL),(27,'2.eeee',2,NULL),(28,'3.like heaven',2,NULL),(29,'4.for god sake',2,NULL);

/*Table structure for table `checklist` */

DROP TABLE IF EXISTS `checklist`;

CREATE TABLE `checklist` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `card_id` bigint(11) DEFAULT NULL,
  `user_id` bigint(11) DEFAULT NULL,
  `percent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `checklist` */

insert  into `checklist`(`id`,`title`,`card_id`,`user_id`,`percent`) values (1,'清单一号',10,1,0),(2,'checklist1',7,1,33),(3,'checklist1',6,1,0),(4,'moring',6,1,0),(5,'mou\'s list',13,1,25),(6,'mou\'s check',5,1,0),(7,'check',5,1,0),(8,'checklist1',5,1,0),(9,'pep\'s list',13,1,67),(10,'emmm',8,1,0),(11,'emmm',13,1,50),(12,'checlisttttttttt',13,2,100),(13,'checklistone',28,2,33),(14,'what a list',27,2,50),(15,'damn',28,2,100);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_id` bigint(11) NOT NULL COMMENT '所在卡片的id',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `user_id` bigint(11) NOT NULL COMMENT '评论者',
  PRIMARY KEY (`id`),
  KEY `card_comment_fk` (`card_id`),
  KEY `user_comment_fk` (`user_id`),
  CONSTRAINT `card_comment_fk` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `user_comment_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`card_id`,`content`,`create_time`,`user_id`) values (1,6,'创建了一个卡片','2020-05-18 12:23:45',1),(2,5,'hello ','2020-05-19 12:00:34',1),(3,5,'hello batstard','2020-05-19 19:23:34',1),(4,5,'damn you','2020-05-19 12:45:34',1),(5,2,'hola,I am juan','2020-05-19 20:23:34',1),(6,2,'???','2020-05-19 17:12:23',1),(7,13,'emmm','2020-05-20 13:20:20',1),(8,29,'god ','2020-06-07 14:23:34',2),(9,29,'god ','2020-06-07 18:34:45',2),(10,29,'god ','2020-06-07 18:18:18',2),(11,14,'emmmm','2020-06-07 18:20:30',2),(12,28,'emmmm','2020-06-07 18:21:54',2),(13,16,'emm','2020-06-07 18:22:45',2),(14,21,'what a comment','2020-06-07 00:00:00',2),(15,19,'摩西摩西','2020-06-07 00:00:00',2),(16,19,'摩西摩西','2020-06-07 00:00:00',2),(17,28,'hello','2020-06-07 00:00:00',2),(18,28,'hola','2020-06-07 00:00:00',2),(19,28,'wtf','2020-06-07 00:00:00',2),(20,28,'??????','2020-06-07 00:00:00',2),(21,28,'??????what','2020-06-07 00:00:00',2),(22,14,'what the fuck','2020-06-07 00:00:00',2),(23,27,'oh shit','2020-06-07 00:00:00',2),(24,27,'you moron','2020-06-07 00:00:00',2),(25,13,'add a comment pls','2020-06-07 15:31:44',2),(26,13,'I can\'t stand it,anymore','2020-06-07 15:32:12',2),(27,2,'emmmm666666666','2020-06-08 18:44:30',2),(28,2,'hello world','2020-06-08 18:47:12',2),(29,2,'damn you','2020-06-08 18:50:22',2),(30,2,'what a man','2020-06-08 18:56:16',2);

/*Table structure for table `deck` */

DROP TABLE IF EXISTS `deck`;

CREATE TABLE `deck` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `board_id` bigint(11) NOT NULL COMMENT '1board->ndeck',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `deck_board_fk` (`board_id`),
  CONSTRAINT `deck_board_fk` FOREIGN KEY (`board_id`) REFERENCES `board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `deck` */

insert  into `deck`(`id`,`board_id`,`title`) values (1,17,'第一章'),(2,17,'第一章'),(3,17,'第二章哈哈hh'),(4,17,'第三章haha'),(5,17,'第四章'),(12,17,'第五章'),(15,17,'第六章a');

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL COMMENT '团队类型',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队描述',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `team_type_fk` (`type_id`) USING BTREE,
  CONSTRAINT `team_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `team_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `team` */

insert  into `team`(`id`,`type_id`,`description`,`title`) values (1,1,'啊啊啊啊啊啊啊啊啊啊啊','这是'),(2,2,'啊啊啊啊啊啊啊','这是第二个测试'),(3,3,'啊啊啊啊啊啊啊啊啊','这是第三个团队');

/*Table structure for table `team_member` */

DROP TABLE IF EXISTS `team_member`;

CREATE TABLE `team_member` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(50) NOT NULL,
  `user_id` bigint(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `team_id` (`team_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `team_member_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_member_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `team_member` */

insert  into `team_member`(`id`,`team_id`,`user_id`) values (1,1,1),(2,1,2),(3,2,2),(4,3,4),(5,1,4);

/*Table structure for table `team_type` */

DROP TABLE IF EXISTS `team_type`;

CREATE TABLE `team_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '团队名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `team_type` */

insert  into `team_type`(`id`,`name`) values (1,'教育'),(2,'计算机'),(3,'机电'),(4,'电子'),(5,'人文');

/*Table structure for table `todoitem` */

DROP TABLE IF EXISTS `todoitem`;

CREATE TABLE `todoitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_done` tinyint(1) DEFAULT NULL,
  `checklist_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `checklist_fk` (`checklist_id`),
  CONSTRAINT `checklist_fk` FOREIGN KEY (`checklist_id`) REFERENCES `checklist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `todoitem` */

insert  into `todoitem`(`id`,`description`,`is_done`,`checklist_id`) values (1,'JAVA',1,5),(2,'ASP.NET',1,5),(3,'shit',0,2),(4,'hello',0,3),(5,'python',0,3),(6,'porto√',0,NULL),(7,'hola',0,5),(8,'emmm',0,5),(9,'shit',0,5),(10,'item1',1,2),(11,'emm',0,2),(12,'oh',0,5),(13,'wtf',0,5),(14,'cock',1,9),(15,'emmmm',1,9),(16,'emmm',0,9),(17,'why?',0,5),(18,'eeee',1,11),(19,'oh shit',0,11),(20,'oh year',0,13),(21,'emmmm',0,13),(22,'oh no',1,13),(23,'oh year',1,12),(24,'oh what a list',0,14),(25,'fuck yeat',1,14),(26,'puul',0,13),(27,'damn youuuu',1,15);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `introduction` varchar(100) DEFAULT NULL COMMENT '自我介绍',
  `registerdate` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`introduction`,`registerdate`) values (1,'user1','user1','hello','2020-05-17 00:00:00'),(2,'user2','user2','hello',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
