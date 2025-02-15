/*
SQLyog Community v12.4.0 (64 bit)
MySQL - 8.0.40 : Database - sdcian
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sdcian` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sdcian`;

/*Table structure for table `audio` */

DROP TABLE IF EXISTS `audio`;

CREATE TABLE `audio` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9xk0w9qwcu6l8tlsr1t1xjnvd` (`post_id`),
  KEY `FKqmvy72hgoepx16crluhx9eojk` (`user_id`),
  CONSTRAINT `FK9xk0w9qwcu6l8tlsr1t1xjnvd` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKqmvy72hgoepx16crluhx9eojk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `audio` */

/*Table structure for table `audio_seq` */

DROP TABLE IF EXISTS `audio_seq`;

CREATE TABLE `audio_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `audio_seq` */

insert  into `audio_seq`(`next_val`) values 
(1);

/*Table structure for table `bug` */

DROP TABLE IF EXISTS `bug`;

CREATE TABLE `bug` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_solved` bit(1) NOT NULL,
  `severity` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
  `steps` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `submitted_by_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeauj3iqdt3adxt7k8vlhd66b9` (`submitted_by_id`),
  CONSTRAINT `FKeauj3iqdt3adxt7k8vlhd66b9` FOREIGN KEY (`submitted_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bug` */

/*Table structure for table `bug_seq` */

DROP TABLE IF EXISTS `bug_seq`;

CREATE TABLE `bug_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bug_seq` */

insert  into `bug_seq`(`next_val`) values 
(1);

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart` */

insert  into `cart`(`id`,`user_id`) values 
(1,1);

/*Table structure for table `cart_item` */

DROP TABLE IF EXISTS `cart_item`;

CREATE TABLE `cart_item` (
  `id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr5b99rvdnupujm2h7hh2bh6m7` (`product_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_item` */

insert  into `cart_item`(`id`,`quantity`,`cart_id`,`product_id`) values 
(56,3,1,2),
(58,1,1,1),
(59,1,1,3),
(60,1,1,4);

/*Table structure for table `cart_item_seq` */

DROP TABLE IF EXISTS `cart_item_seq`;

CREATE TABLE `cart_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_item_seq` */

insert  into `cart_item_seq`(`next_val`) values 
(151);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `comment_text` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `is_public` bit(1) NOT NULL,
  `is_archived` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment` */

insert  into `comment`(`id`,`comment_text`,`parent_id`,`post_id`,`user_id`,`is_public`,`is_archived`,`is_deleted`,`created_at`,`updated_at`) values 
(1,'hello',NULL,1,1,'','\0','\0','2025-02-12 23:22:10.469592','2025-02-12 23:22:10.469592'),
(2,'hi',NULL,1,1,'','\0','\0','2025-02-12 23:25:24.393732','2025-02-12 23:25:24.393732'),
(52,'how are you',NULL,1,1,'','\0','\0','2025-02-12 23:30:31.705833','2025-02-12 23:30:31.705833'),
(53,'i am fine',NULL,1,1,'','\0','\0','2025-02-12 23:31:35.080222','2025-02-12 23:31:35.080222'),
(54,'how about you?',NULL,1,1,'','\0','\0','2025-02-12 23:31:55.918400','2025-02-12 23:31:55.918400'),
(55,'6th comment',NULL,1,1,'','\0','\0','2025-02-12 23:50:50.212349','2025-02-12 23:50:50.212349'),
(56,'7th comment',NULL,1,1,'','\0','\0','2025-02-12 23:51:42.246916','2025-02-12 23:51:42.246916'),
(57,'23',NULL,102,1,'','\0','\0','2025-02-12 23:53:06.473660','2025-02-12 23:53:06.473660'),
(58,'1',NULL,102,1,'','\0','\0','2025-02-12 23:55:08.715413','2025-02-12 23:55:08.715413'),
(59,'3',NULL,252,1,'','\0','\0','2025-02-12 23:55:35.270937','2025-02-12 23:55:35.270937'),
(60,'no video',NULL,302,1,'','\0','\0','2025-02-12 23:55:49.735785','2025-02-12 23:55:49.735785'),
(61,'noice post',NULL,303,1,'','\0','\0','2025-02-12 23:56:14.480071','2025-02-12 23:56:14.480071'),
(62,'meh',NULL,352,1,'','\0','\0','2025-02-12 23:56:23.296181','2025-02-12 23:56:23.296181'),
(102,'hi',1,NULL,1,'','\0','\0','2025-02-13 00:10:39.652804','2025-02-13 00:10:39.652804'),
(152,'hi',1,NULL,1,'','\0','\0','2025-02-13 00:11:52.779893','2025-02-13 00:11:52.779893'),
(202,'hi',1,NULL,1,'','\0','\0','2025-02-13 00:12:10.906777','2025-02-13 00:12:10.907746'),
(203,'final comment',NULL,1,1,'','\0','\0','2025-02-13 00:15:37.184180','2025-02-13 00:15:37.184180'),
(204,'fisrt replay',203,NULL,1,'','\0','\0','2025-02-13 00:15:53.137380','2025-02-13 00:15:53.137380'),
(205,'final replay',203,NULL,1,'','\0','\0','2025-02-13 00:19:24.674725','2025-02-13 00:19:24.674725'),
(206,'fixing bug',203,NULL,1,'','\0','\0','2025-02-13 00:21:32.876182','2025-02-13 00:21:32.876182'),
(207,'fixing bug 2',203,NULL,1,'','\0','\0','2025-02-13 00:26:40.601061','2025-02-13 00:26:40.601061'),
(208,'chup',62,NULL,1,'','\0','\0','2025-02-13 00:29:30.233721','2025-02-13 00:29:30.233721');

/*Table structure for table `comment_image` */

DROP TABLE IF EXISTS `comment_image`;

CREATE TABLE `comment_image` (
  `comment_id` bigint NOT NULL,
  `image_id` bigint NOT NULL,
  UNIQUE KEY `UK91bn55repkkjc31qw3rr1kb7g` (`image_id`),
  KEY `FKs1et2hjm1b13mvs63acb8wgi8` (`comment_id`),
  CONSTRAINT `FKqph8huxsx7plt6oxwywxarsy0` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKs1et2hjm1b13mvs63acb8wgi8` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment_image` */

/*Table structure for table `comment_replies` */

DROP TABLE IF EXISTS `comment_replies`;

CREATE TABLE `comment_replies` (
  `comment_id` bigint NOT NULL,
  `replies_id` bigint NOT NULL,
  UNIQUE KEY `UK107vee447mhtsxghw5bigtv9l` (`replies_id`),
  KEY `FK9eka2pjxubgmpdx3qqfsvfack` (`comment_id`),
  CONSTRAINT `FK9eka2pjxubgmpdx3qqfsvfack` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKr1rln36eafw2t55r8et2pb8yo` FOREIGN KEY (`replies_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment_replies` */

/*Table structure for table `comment_seq` */

DROP TABLE IF EXISTS `comment_seq`;

CREATE TABLE `comment_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment_seq` */

insert  into `comment_seq`(`next_val`) values 
(301);

/*Table structure for table `comment_video` */

DROP TABLE IF EXISTS `comment_video`;

CREATE TABLE `comment_video` (
  `comment_id` bigint NOT NULL,
  `video_id` bigint NOT NULL,
  UNIQUE KEY `UK9h869crshv6vrq4xkxhihrn5a` (`video_id`),
  KEY `FK7tanu2c22sdb9nxvavk5hefow` (`comment_id`),
  CONSTRAINT `FK7tanu2c22sdb9nxvavk5hefow` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKsx1006j6yf8s43s6ln6qgrvqh` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment_video` */

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `id` bigint NOT NULL,
  `date` date DEFAULT NULL,
  `day_of_week` enum('FRIDAY','MONDAY','SATURDAY','SUNDAY','THURSDAY','TUESDAY','WEDNESDAY') DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `distance_inkm` int DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `from_location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `to_location` varchar(255) DEFAULT NULL,
  `organiser_id` bigint DEFAULT NULL,
  `cover_image_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKahqf3d9mhepq2g6n37onde4cv` (`organiser_id`),
  KEY `FK8hmqkx9o7v6rpejkr9xtst3uj` (`cover_image_id`),
  CONSTRAINT `FK8hmqkx9o7v6rpejkr9xtst3uj` FOREIGN KEY (`cover_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKahqf3d9mhepq2g6n37onde4cv` FOREIGN KEY (`organiser_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event` */

insert  into `event`(`id`,`date`,`day_of_week`,`description`,`distance_inkm`,`duration`,`from_location`,`name`,`start_time`,`to_location`,`organiser_id`,`cover_image_id`,`created_at`,`updated_at`) values 
(1,'2025-02-14','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-13 16:09:06.000000','Sharighat',1,NULL,NULL,NULL),
(2,'2025-02-15','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 07:44:52.000000','Sharighat',1,NULL,NULL,NULL),
(3,'2025-02-15','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 07:45:17.000000','Sharighat',1,NULL,NULL,NULL),
(52,'2025-02-14','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 12:59:00.000000','Sharighat',1,NULL,NULL,NULL),
(102,'2025-02-15','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Current Event','2025-02-14 22:00:04.000000','Sharighat',1,NULL,NULL,NULL),
(103,'2025-02-15','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-15 10:00:00.000000','Sharighat',1,NULL,NULL,NULL),
(104,'2025-02-15','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-15 12:07:33.000000','Sharighat',1,NULL,NULL,NULL),
(152,'2025-02-15',NULL,'lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 18:35:00.000000','Sharighat',1,NULL,NULL,NULL),
(202,'2025-02-15',NULL,'lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 18:52:19.000000','Sharighat',1,NULL,'2025-02-15 00:53:20.580385','2025-02-15 00:53:20.580385'),
(203,'2025-02-15',NULL,'lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 18:57:46.000000','Sharighat',1,504,'2025-02-15 00:58:04.767608','2025-02-15 00:58:04.767608');

/*Table structure for table `event_seq` */

DROP TABLE IF EXISTS `event_seq`;

CREATE TABLE `event_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event_seq` */

insert  into `event_seq`(`next_val`) values 
(301);

/*Table structure for table `event_sponsor` */

DROP TABLE IF EXISTS `event_sponsor`;

CREATE TABLE `event_sponsor` (
  `event_id` bigint NOT NULL,
  `sponsor_id` bigint NOT NULL,
  KEY `FKo7wfoamq5qy3235bwbbkh1x61` (`sponsor_id`),
  KEY `FKs39eibx0g3fa2d3rj9pw75poj` (`event_id`),
  CONSTRAINT `FKo7wfoamq5qy3235bwbbkh1x61` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`),
  CONSTRAINT `FKs39eibx0g3fa2d3rj9pw75poj` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event_sponsor` */

/*Table structure for table `event_sponsor_ship_requests` */

DROP TABLE IF EXISTS `event_sponsor_ship_requests`;

CREATE TABLE `event_sponsor_ship_requests` (
  `event_id` bigint NOT NULL,
  `sponsor_ship_requests_id` bigint NOT NULL,
  KEY `FKghsov3ep2fl0o3qaff7eli9r0` (`sponsor_ship_requests_id`),
  KEY `FK1i1y69cyaqi6ih7p52c6mxawx` (`event_id`),
  CONSTRAINT `FK1i1y69cyaqi6ih7p52c6mxawx` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FKghsov3ep2fl0o3qaff7eli9r0` FOREIGN KEY (`sponsor_ship_requests_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event_sponsor_ship_requests` */

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcvpnctgluno47ac6avana5sqf` (`user_id`),
  CONSTRAINT `FKcvpnctgluno47ac6avana5sqf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image` */

insert  into `image`(`id`,`description`,`name`,`path`,`url`,`user_id`) values 
(1,NULL,'demoUser_1739026030698_0055070.png','src/main/resources/static/images/demoUser/demoUser_1739026030698_0055070.png','/images/demoUser/demoUser_1739026030698_0055070.png',1),
(2,NULL,'demoUser_1739026036676_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739026036676_th.jpg','/images/demoUser/demoUser_1739026036676_th.jpg',1),
(52,NULL,'demoUser_1739031760714_download.jpg','src/main/resources/static/images/demoUser/demoUser_1739031760714_download.jpg','/images/demoUser/demoUser_1739031760714_download.jpg',1),
(53,NULL,'demoUser_1739031770376_download (2).jpg','src/main/resources/static/images/demoUser/demoUser_1739031770376_download (2).jpg','/images/demoUser/demoUser_1739031770376_download (2).jpg',1),
(54,NULL,'demoUser_1739031770373_download (3).jpg','src/main/resources/static/images/demoUser/demoUser_1739031770373_download (3).jpg','/images/demoUser/demoUser_1739031770373_download (3).jpg',1),
(55,NULL,'demoUser_1739031770384_download (1).jpg','src/main/resources/static/images/demoUser/demoUser_1739031770384_download (1).jpg','/images/demoUser/demoUser_1739031770384_download (1).jpg',1),
(56,NULL,'demoUser_1739031800507_download (1).jpg','src/main/resources/static/images/demoUser/demoUser_1739031800507_download (1).jpg','/images/demoUser/demoUser_1739031800507_download (1).jpg',1),
(57,NULL,'demoUser_1739031808454_download (3).jpg','src/main/resources/static/images/demoUser/demoUser_1739031808454_download (3).jpg','/images/demoUser/demoUser_1739031808454_download (3).jpg',1),
(58,NULL,'demoUser_1739031808542_download (2).jpg','src/main/resources/static/images/demoUser/demoUser_1739031808542_download (2).jpg','/images/demoUser/demoUser_1739031808542_download (2).jpg',1),
(59,NULL,'demoUser_1739031808580_download.jpg','src/main/resources/static/images/demoUser/demoUser_1739031808580_download.jpg','/images/demoUser/demoUser_1739031808580_download.jpg',1),
(60,NULL,'demoUser_1739031829725_download (2).jpg','src/main/resources/static/images/demoUser/demoUser_1739031829725_download (2).jpg','/images/demoUser/demoUser_1739031829725_download (2).jpg',1),
(61,NULL,'demoUser_1739031840076_download (3).jpg','src/main/resources/static/images/demoUser/demoUser_1739031840076_download (3).jpg','/images/demoUser/demoUser_1739031840076_download (3).jpg',1),
(62,NULL,'demoUser_1739031840091_download (1).jpg','src/main/resources/static/images/demoUser/demoUser_1739031840091_download (1).jpg','/images/demoUser/demoUser_1739031840091_download (1).jpg',1),
(63,NULL,'demoUser_1739031840106_download.jpg','src/main/resources/static/images/demoUser/demoUser_1739031840106_download.jpg','/images/demoUser/demoUser_1739031840106_download.jpg',1),
(64,NULL,'demoUser_1739031854324_download (3).jpg','src/main/resources/static/images/demoUser/demoUser_1739031854324_download (3).jpg','/images/demoUser/demoUser_1739031854324_download (3).jpg',1),
(65,NULL,'demoUser_1739031860598_download (2).jpg','src/main/resources/static/images/demoUser/demoUser_1739031860598_download (2).jpg','/images/demoUser/demoUser_1739031860598_download (2).jpg',1),
(66,NULL,'demoUser_1739031860682_download (1).jpg','src/main/resources/static/images/demoUser/demoUser_1739031860682_download (1).jpg','/images/demoUser/demoUser_1739031860682_download (1).jpg',1),
(67,NULL,'demoUser_1739031860685_download.jpg','src/main/resources/static/images/demoUser/demoUser_1739031860685_download.jpg','/images/demoUser/demoUser_1739031860685_download.jpg',1),
(102,NULL,'demoUser_1739037146201_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739037146201_th2.jpg','/images/demoUser/demoUser_1739037146201_th2.jpg',1),
(103,NULL,'demoUser_1739037338581_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739037338581_th.jpg','/images/demoUser/demoUser_1739037338581_th.jpg',1),
(104,NULL,'demoUser_1739037474518_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739037474518_th.jpg','/images/demoUser/demoUser_1739037474518_th.jpg',1),
(105,NULL,'demoUser_1739037474532_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739037474532_th2.jpg','/images/demoUser/demoUser_1739037474532_th2.jpg',1),
(152,NULL,'demoUser_1739175727712_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739175727712_th.jpg','/images/demoUser/demoUser_1739175727712_th.jpg',1),
(153,NULL,'demoUser_1739177505469_0055070.png','src/main/resources/static/images/demoUser/demoUser_1739177505469_0055070.png','/images/demoUser/demoUser_1739177505469_0055070.png',1),
(202,NULL,'demoUser_1739191848785_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739191848785_th.jpg','/images/demoUser/demoUser_1739191848785_th.jpg',1),
(252,NULL,'demoUser_1739193110853_s-l400.jpg','src/main/resources/static/images/demoUser/demoUser_1739193110853_s-l400.jpg','/images/demoUser/demoUser_1739193110853_s-l400.jpg',1),
(302,NULL,'demoUser_1739193224548_s-l400.jpg','src/main/resources/static/images/demoUser/demoUser_1739193224548_s-l400.jpg','/images/demoUser/demoUser_1739193224548_s-l400.jpg',1),
(352,NULL,'demoUser_1739268168230_images.jpg','src/main/resources/static/images/demoUser/demoUser_1739268168230_images.jpg','/images/demoUser/demoUser_1739268168230_images.jpg',1),
(353,NULL,'demoUser_1739268168230_MainBefore.jpg','src/main/resources/static/images/demoUser/demoUser_1739268168230_MainBefore.jpg','/images/demoUser/demoUser_1739268168230_MainBefore.jpg',1),
(354,NULL,'demoUser_1739268672983_s-l400.jpg','src/main/resources/static/images/demoUser/demoUser_1739268672983_s-l400.jpg','/images/demoUser/demoUser_1739268672983_s-l400.jpg',1),
(355,NULL,'demoUser_1739268673002_71HijM2R7dL._AC_SL1500_.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673002_71HijM2R7dL._AC_SL1500_.jpg','/images/demoUser/demoUser_1739268673002_71HijM2R7dL._AC_SL1500_.jpg',1),
(356,NULL,'demoUser_1739268672966_MainBefore.jpg','src/main/resources/static/images/demoUser/demoUser_1739268672966_MainBefore.jpg','/images/demoUser/demoUser_1739268672966_MainBefore.jpg',1),
(357,NULL,'demoUser_1739268673108_470186131_892781573032685_3766674264100066369_n.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673108_470186131_892781573032685_3766674264100066369_n.jpg','/images/demoUser/demoUser_1739268673108_470186131_892781573032685_3766674264100066369_n.jpg',1),
(358,NULL,'demoUser_1739268673105_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673105_th.jpg','/images/demoUser/demoUser_1739268673105_th.jpg',1),
(359,NULL,'demoUser_1739268672966_images.jpg','src/main/resources/static/images/demoUser/demoUser_1739268672966_images.jpg','/images/demoUser/demoUser_1739268672966_images.jpg',1),
(360,NULL,'demoUser_1739268673169_470189050_892781526366023_1233281047254958663_n.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673169_470189050_892781526366023_1233281047254958663_n.jpg','/images/demoUser/demoUser_1739268673169_470189050_892781526366023_1233281047254958663_n.jpg',1),
(361,NULL,'demoUser_1739268673189_161611769_max.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673189_161611769_max.jpg','/images/demoUser/demoUser_1739268673189_161611769_max.jpg',1),
(362,NULL,'demoUser_1739268673205_0055070.png','src/main/resources/static/images/demoUser/demoUser_1739268673205_0055070.png','/images/demoUser/demoUser_1739268673205_0055070.png',1),
(363,NULL,'demoUser_1739268673208_470214942_892781466366029_1570399543332804060_n.jpg','src/main/resources/static/images/demoUser/demoUser_1739268673208_470214942_892781466366029_1570399543332804060_n.jpg','/images/demoUser/demoUser_1739268673208_470214942_892781466366029_1570399543332804060_n.jpg',1),
(402,NULL,'demoUser_1739557896558_th3.jpg','src/main/resources/static/images/demoUser/demoUser_1739557896558_th3.jpg','/images/demoUser/demoUser_1739557896558_th3.jpg',1),
(403,NULL,'demoUser_1739558009441_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739558009441_th2.jpg','/images/demoUser/demoUser_1739558009441_th2.jpg',1),
(404,NULL,'demoUser_1739558022747_th3.jpg','src/main/resources/static/images/demoUser/demoUser_1739558022747_th3.jpg','/images/demoUser/demoUser_1739558022747_th3.jpg',1),
(452,NULL,'demoUser_1739558693289_th3.jpg','src/main/resources/static/images/demoUser/demoUser_1739558693289_th3.jpg','/images/demoUser/demoUser_1739558693289_th3.jpg',1),
(502,NULL,'demoUser_1739559145286_th3.jpg','src/main/resources/static/images/demoUser/demoUser_1739559145286_th3.jpg','/images/demoUser/demoUser_1739559145286_th3.jpg',1),
(503,NULL,'demoUser_1739559468843_th3.jpg','src/main/resources/static/images/demoUser/demoUser_1739559468843_th3.jpg','/images/demoUser/demoUser_1739559468843_th3.jpg',1),
(504,NULL,'demoUser_1739559480930_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739559480930_th2.jpg','/images/demoUser/demoUser_1739559480930_th2.jpg',1);

/*Table structure for table `image_seq` */

DROP TABLE IF EXISTS `image_seq`;

CREATE TABLE `image_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image_seq` */

insert  into `image_seq`(`next_val`) values 
(601);

/*Table structure for table `messege` */

DROP TABLE IF EXISTS `messege`;

CREATE TABLE `messege` (
  `id` bigint NOT NULL,
  `is_archived` bit(1) NOT NULL,
  `is_blocked` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_deleted_for_all` bit(1) NOT NULL,
  `is_deleted_for_both` bit(1) NOT NULL,
  `is_deleted_for_receiver` bit(1) NOT NULL,
  `is_deleted_for_sender` bit(1) NOT NULL,
  `is_draft` bit(1) NOT NULL,
  `is_important` bit(1) NOT NULL,
  `is_muted` bit(1) NOT NULL,
  `is_pinned` bit(1) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `is_sent` bit(1) NOT NULL,
  `is_spam` bit(1) NOT NULL,
  `is_starred` bit(1) NOT NULL,
  `is_trash` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK61aa6yc88cjlpi7ykr66knk5x` (`receiver_id`),
  UNIQUE KEY `UK8f2ldfa5t3t50ynfp8jq78kkq` (`sender_id`),
  CONSTRAINT `FK1p85unqtqwupp899tq8mkut8j` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK9tt2ik57o7bmatdu6m8sct02g` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege` */

/*Table structure for table `messege_audios` */

DROP TABLE IF EXISTS `messege_audios`;

CREATE TABLE `messege_audios` (
  `messege_id` bigint NOT NULL,
  `audios_id` bigint NOT NULL,
  UNIQUE KEY `UKaxg3qu662geymtka4cxhgwpso` (`audios_id`),
  KEY `FKob4w1haipr9tj9tte4xn863gb` (`messege_id`),
  CONSTRAINT `FKco8gdqw426um4ot45abkurwu5` FOREIGN KEY (`audios_id`) REFERENCES `audio` (`id`),
  CONSTRAINT `FKob4w1haipr9tj9tte4xn863gb` FOREIGN KEY (`messege_id`) REFERENCES `messege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege_audios` */

/*Table structure for table `messege_images` */

DROP TABLE IF EXISTS `messege_images`;

CREATE TABLE `messege_images` (
  `messege_id` bigint NOT NULL,
  `images_id` bigint NOT NULL,
  UNIQUE KEY `UK7c526yog4eg9yxlpsqaahwsfc` (`images_id`),
  KEY `FK3sswgayut0x0k2e8jx846i1oi` (`messege_id`),
  CONSTRAINT `FK3sswgayut0x0k2e8jx846i1oi` FOREIGN KEY (`messege_id`) REFERENCES `messege` (`id`),
  CONSTRAINT `FKlu6apnkll9q997ouxunyx6ovo` FOREIGN KEY (`images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege_images` */

/*Table structure for table `messege_seq` */

DROP TABLE IF EXISTS `messege_seq`;

CREATE TABLE `messege_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege_seq` */

insert  into `messege_seq`(`next_val`) values 
(1);

/*Table structure for table `messege_videos` */

DROP TABLE IF EXISTS `messege_videos`;

CREATE TABLE `messege_videos` (
  `messege_id` bigint NOT NULL,
  `videos_id` bigint NOT NULL,
  UNIQUE KEY `UKtsbmc6qrf2kpvfhsc7guqfa` (`videos_id`),
  KEY `FKr1co1vwidly4ygyrrdvyx8koy` (`messege_id`),
  CONSTRAINT `FKatoah91vd9vg0a8iibmif8e3n` FOREIGN KEY (`videos_id`) REFERENCES `video` (`id`),
  CONSTRAINT `FKr1co1vwidly4ygyrrdvyx8koy` FOREIGN KEY (`messege_id`) REFERENCES `messege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege_videos` */

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` bigint NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `status` enum('PENDING','PROCESSING','OUT_FOR_DELIVERY','COMPLETED','REJECTED','CANCELLED','REFUSED') NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  KEY `FKp0engm9uydulvij7syqjyvyo` (`customer_id`),
  KEY `FKpnbjra8qegoraqc46mwscg9d4` (`seller_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKp0engm9uydulvij7syqjyvyo` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpnbjra8qegoraqc46mwscg9d4` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item` */

insert  into `order_item`(`id`,`price`,`quantity`,`status`,`order_id`,`product_id`,`customer_id`,`seller_id`,`created_at`,`updated_at`) values 
(202,1000,3,'REJECTED',152,2,1,1,NULL,'2025-02-15 13:23:24.579155'),
(203,1000,1,'REJECTED',152,1,1,1,NULL,'2025-02-15 13:32:01.553282'),
(204,1000,1,'COMPLETED',152,3,1,1,NULL,'2025-02-15 13:47:34.789916'),
(205,1000,1,'REFUSED',152,4,1,1,NULL,'2025-02-15 14:01:39.043215'),
(206,1000,3,'COMPLETED',153,2,1,1,NULL,'2025-02-15 23:09:15.154325'),
(207,1000,1,'OUT_FOR_DELIVERY',153,1,1,1,NULL,'2025-02-15 13:35:52.641500'),
(208,1000,1,'REJECTED',153,3,1,1,NULL,'2025-02-15 13:35:59.503511'),
(209,1000,1,'OUT_FOR_DELIVERY',153,4,1,1,NULL,'2025-02-15 13:36:02.908452'),
(210,1000,3,'OUT_FOR_DELIVERY',154,2,1,1,NULL,'2025-02-15 13:36:53.661189'),
(211,1000,1,'REJECTED',154,1,1,1,NULL,'2025-02-15 13:37:03.936853'),
(212,1000,1,'OUT_FOR_DELIVERY',154,3,1,1,NULL,'2025-02-15 13:37:50.626652'),
(213,1000,1,'REJECTED',154,4,1,1,NULL,'2025-02-15 13:39:23.993609'),
(214,1000,3,'OUT_FOR_DELIVERY',155,2,1,1,NULL,'2025-02-15 13:39:35.193816'),
(215,1000,1,'REJECTED',155,1,1,1,NULL,'2025-02-15 13:18:16.223301'),
(216,1000,1,'REJECTED',155,3,1,1,NULL,'2025-02-15 13:18:16.979008'),
(217,1000,1,'REJECTED',155,4,1,1,NULL,'2025-02-15 13:18:17.554378'),
(218,1000,3,'REJECTED',156,2,1,1,NULL,'2025-02-15 13:18:17.899358'),
(219,1000,1,'REJECTED',156,1,1,1,NULL,'2025-02-15 13:18:18.207666'),
(220,1000,1,'REJECTED',156,3,1,1,NULL,'2025-02-15 13:18:18.523201'),
(221,1000,1,'REJECTED',156,4,1,1,NULL,'2025-02-15 13:18:18.900157'),
(222,1000,3,'REJECTED',157,2,1,1,NULL,'2025-02-15 13:45:15.198779'),
(223,1000,1,'OUT_FOR_DELIVERY',157,1,1,1,NULL,'2025-02-15 13:45:16.979925'),
(224,1000,1,'REJECTED',157,3,1,1,NULL,'2025-02-15 13:18:21.486546'),
(225,1000,1,'REJECTED',157,4,1,1,NULL,'2025-02-15 13:18:21.798150'),
(226,1000,3,'REJECTED',158,2,1,1,NULL,'2025-02-15 13:18:22.109639'),
(227,1000,1,'OUT_FOR_DELIVERY',158,1,1,1,NULL,'2025-02-15 13:45:17.591467'),
(228,1000,1,'OUT_FOR_DELIVERY',158,3,1,1,NULL,'2025-02-15 13:45:18.202533'),
(229,1000,1,'OUT_FOR_DELIVERY',158,4,1,1,NULL,'2025-02-15 13:47:17.737915'),
(230,1000,3,'REJECTED',159,2,1,1,NULL,'2025-02-15 13:18:24.768391'),
(231,1000,1,'REJECTED',159,1,1,1,NULL,'2025-02-15 13:18:25.080303'),
(232,1000,1,'REJECTED',159,3,1,1,NULL,'2025-02-15 13:18:25.498284'),
(233,1000,1,'REJECTED',159,4,1,1,NULL,'2025-02-15 13:47:20.911728'),
(234,1000,3,'OUT_FOR_DELIVERY',160,2,1,1,NULL,'2025-02-15 22:42:59.618348'),
(235,1000,1,'OUT_FOR_DELIVERY',160,1,1,1,NULL,'2025-02-15 22:42:59.618348'),
(236,1000,1,'REJECTED',160,3,1,1,NULL,'2025-02-15 13:18:27.724954'),
(237,1000,1,'REJECTED',160,4,1,1,NULL,'2025-02-15 13:18:28.062237'),
(238,1000,3,'REJECTED',161,2,1,1,NULL,'2025-02-15 13:18:28.452671'),
(239,1000,1,'OUT_FOR_DELIVERY',161,1,1,1,NULL,'2025-02-15 23:01:29.666966'),
(240,1000,1,'OUT_FOR_DELIVERY',161,3,1,1,NULL,'2025-02-15 13:45:06.352551'),
(241,1000,1,'OUT_FOR_DELIVERY',161,4,1,1,NULL,'2025-02-15 23:01:29.676702'),
(242,1000,3,'OUT_FOR_DELIVERY',162,2,1,1,NULL,'2025-02-15 23:05:22.940696'),
(243,1000,1,'OUT_FOR_DELIVERY',162,1,1,1,NULL,'2025-02-15 23:05:22.940696'),
(244,1000,1,'OUT_FOR_DELIVERY',162,3,1,1,NULL,'2025-02-15 23:05:22.940696'),
(245,1000,1,'OUT_FOR_DELIVERY',162,4,1,1,NULL,'2025-02-15 23:05:22.940696'),
(246,1000,3,'OUT_FOR_DELIVERY',163,2,1,1,NULL,'2025-02-15 23:06:26.644507'),
(247,1000,1,'OUT_FOR_DELIVERY',163,1,1,1,NULL,'2025-02-15 23:06:26.644507'),
(248,1000,1,'OUT_FOR_DELIVERY',163,3,1,1,NULL,'2025-02-15 23:06:26.644507'),
(249,1000,1,'OUT_FOR_DELIVERY',163,4,1,1,NULL,'2025-02-15 23:06:26.644507'),
(250,1000,3,'OUT_FOR_DELIVERY',164,2,1,1,NULL,'2025-02-15 23:10:01.995433'),
(251,1000,1,'OUT_FOR_DELIVERY',164,1,1,1,NULL,'2025-02-15 23:13:23.258303'),
(252,1000,1,'OUT_FOR_DELIVERY',164,3,1,1,NULL,'2025-02-15 23:15:23.087076'),
(253,1000,1,'OUT_FOR_DELIVERY',164,4,1,1,NULL,'2025-02-15 23:21:26.475527'),
(254,1000,3,'OUT_FOR_DELIVERY',165,2,1,1,NULL,'2025-02-15 23:24:47.797338'),
(255,1000,1,'OUT_FOR_DELIVERY',165,1,1,1,NULL,'2025-02-15 23:25:55.473140'),
(256,1000,1,'OUT_FOR_DELIVERY',165,3,1,1,NULL,'2025-02-15 23:27:23.810468'),
(257,1000,1,'PENDING',165,4,1,1,NULL,NULL),
(258,1000,3,'OUT_FOR_DELIVERY',166,2,1,1,NULL,'2025-02-15 23:09:31.152611'),
(259,1000,3,'OUT_FOR_DELIVERY',167,2,1,1,NULL,'2025-02-15 23:08:42.616253');

/*Table structure for table `order_item_seq` */

DROP TABLE IF EXISTS `order_item_seq`;

CREATE TABLE `order_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item_seq` */

insert  into `order_item_seq`(`next_val`) values 
(351);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_number` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`id`,`address`,`created_at`,`email`,`name`,`payment`,`payment_method`,`payment_number`,`phone`,`phone_number`,`transaction_id`,`user_name`,`user_id`) values 
(152,'123 Demo Street, Demo City','2025-02-13 02:20:22.250814','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(153,'123 Demo Street, Demo City','2025-02-13 02:20:24.533226','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(154,'123 Demo Street, Demo City','2025-02-13 02:20:26.186460','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(155,'123 Demo Street, Demo City','2025-02-13 02:20:27.013152','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(156,'123 Demo Street, Demo City','2025-02-13 02:20:27.745485','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(157,'123 Demo Street, Demo City','2025-02-13 02:20:28.795168','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(158,'123 Demo Street, Demo City','2025-02-13 02:20:30.747826','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(159,'123 Demo Street, Demo City','2025-02-13 02:20:31.220429','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(160,'123 Demo Street, Demo City','2025-02-13 02:20:31.581908','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(161,'123 Demo Street, Demo City','2025-02-13 02:20:31.752625','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(162,'123 Demo Street, Demo City','2025-02-13 02:20:31.911584','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(163,'123 Demo Street, Demo City','2025-02-13 02:20:32.077915','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(164,'123 Demo Street, Demo City','2025-02-13 02:20:32.261760','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(165,'123 Demo Street, Demo City','2025-02-13 02:20:32.479115','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(166,'123 Demo Street, Demo City','2025-02-13 02:21:29.759398','demo@example.com','Demo User',0,'cashOnDelivery','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(167,'123 Demo Street, Demo City','2025-02-13 02:23:03.942611','demo@example.com','Demo User',0,'rocket','1234567890',NULL,'1234567890','abcyzx','demoUser',1);

/*Table structure for table `orders_seq` */

DROP TABLE IF EXISTS `orders_seq`;

CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders_seq` */

insert  into `orders_seq`(`next_val`) values 
(251);

/*Table structure for table `percel` */

DROP TABLE IF EXISTS `percel`;

CREATE TABLE `percel` (
  `id` bigint NOT NULL,
  `cod` double NOT NULL,
  `dc` double NOT NULL,
  `delivery_instructions` varchar(255) DEFAULT NULL,
  `delivery_time` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `distance` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pickup_instructions` varchar(255) DEFAULT NULL,
  `pickup_time` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `reciever_address` varchar(255) DEFAULT NULL,
  `reciever_phone` varchar(255) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `weight` double NOT NULL,
  `rider_id` bigint DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3rps5ni3igqk6nq9irs3wfcu6` (`rider_id`),
  KEY `FKrqd2lyi2su5pwcq00jf86plm0` (`sender_id`),
  CONSTRAINT `FK7enat861w82wxlwwf577afy1h` FOREIGN KEY (`rider_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrqd2lyi2su5pwcq00jf86plm0` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `percel_chk_1` CHECK ((`status` between 0 and 7))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `percel` */

/*Table structure for table `percel_images` */

DROP TABLE IF EXISTS `percel_images`;

CREATE TABLE `percel_images` (
  `percel_id` bigint NOT NULL,
  `images_id` bigint NOT NULL,
  UNIQUE KEY `UKsbxbkrl5whcb9hgcu5ebe2q10` (`images_id`),
  KEY `FK3itp2lunl49uwcthslr2j8jby` (`percel_id`),
  CONSTRAINT `FK3itp2lunl49uwcthslr2j8jby` FOREIGN KEY (`percel_id`) REFERENCES `percel` (`id`),
  CONSTRAINT `FK4yhg0v4kbapvj7habdtoc1xqo` FOREIGN KEY (`images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `percel_images` */

/*Table structure for table `percel_seq` */

DROP TABLE IF EXISTS `percel_seq`;

CREATE TABLE `percel_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `percel_seq` */

insert  into `percel_seq`(`next_val`) values 
(1);

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` bigint NOT NULL,
  `is_archived` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_public` bit(1) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `post_text` varchar(255) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `shared_post_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4b5ha5bhihxywskppdc06dyuo` (`creator_id`),
  KEY `FKnlb9ck8vt0xwqb561hssno6l3` (`shared_post_id`),
  CONSTRAINT `FK4b5ha5bhihxywskppdc06dyuo` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnlb9ck8vt0xwqb561hssno6l3` FOREIGN KEY (`shared_post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post` */

insert  into `post`(`id`,`is_archived`,`is_deleted`,`is_public`,`location`,`post_text`,`creator_id`,`shared_post_id`,`created_at`,`updated_at`) values 
(1,'\0','\0','','Dhaka','demo text',1,NULL,'2025-02-11 16:20:11.000000','2025-02-11 16:20:11.404629'),
(2,'\0','\0','','Dhaka','demo text',1,NULL,'2025-02-11 16:20:11.404629','2025-02-11 16:20:11.404629'),
(52,'\0','\0','','Dhaka','demo text',1,NULL,'2025-02-11 16:20:11.000000','2025-02-11 16:20:11.404629'),
(102,'\0','\0','','Dhaka','demo text',1,NULL,'2025-02-07 16:20:11.404629','2025-02-11 16:20:11.404629'),
(252,'\0','\0','','faka','sdf',1,NULL,'2025-02-07 16:20:11.000000','2025-02-11 16:20:11.404629'),
(302,'\0','\0','','chaka','Some thing big is comming',1,NULL,'2025-02-08 06:00:01.000000','2025-02-11 16:20:11.404629'),
(303,'\0','\0','','moha','Something Bigger is comming',1,NULL,'2025-02-10 06:00:01.000000','2025-02-11 16:20:11.404629'),
(352,'\0','\0','','','123',1,NULL,'2025-02-10 16:20:11.404629','2025-02-11 16:20:11.404629');

/*Table structure for table `post_comments` */

DROP TABLE IF EXISTS `post_comments`;

CREATE TABLE `post_comments` (
  `post_id` bigint NOT NULL,
  `comments_id` bigint NOT NULL,
  UNIQUE KEY `UKgq9be62nx9c9hc0uyhakey771` (`comments_id`),
  KEY `FKmws3vvhl5o4t7r7sajiqpfe0b` (`post_id`),
  CONSTRAINT `FKmws3vvhl5o4t7r7sajiqpfe0b` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKrvgf8o4dg5kamt01me5gjqodf` FOREIGN KEY (`comments_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_comments` */

/*Table structure for table `post_likers` */

DROP TABLE IF EXISTS `post_likers`;

CREATE TABLE `post_likers` (
  `post_id` bigint NOT NULL,
  `likers_id` bigint NOT NULL,
  KEY `FKjgk318axdmfm9s1vhxtsju0fb` (`likers_id`),
  KEY `FKtcouira1civlc02pt2xhv0taj` (`post_id`),
  CONSTRAINT `FKjgk318axdmfm9s1vhxtsju0fb` FOREIGN KEY (`likers_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtcouira1civlc02pt2xhv0taj` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_likers` */

/*Table structure for table `post_post_image` */

DROP TABLE IF EXISTS `post_post_image`;

CREATE TABLE `post_post_image` (
  `post_id` bigint NOT NULL,
  `post_image_id` bigint NOT NULL,
  UNIQUE KEY `UKpl2v0o2mw4328g0cxo9d7e5ar` (`post_image_id`),
  KEY `FK8854xad6uhnva69mku8a3wsbn` (`post_id`),
  CONSTRAINT `FK8854xad6uhnva69mku8a3wsbn` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKgwum2grqtncil80o7b0ykub67` FOREIGN KEY (`post_image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_post_image` */

insert  into `post_post_image`(`post_id`,`post_image_id`) values 
(102,202),
(252,302),
(302,352),
(302,353),
(303,354),
(303,355),
(303,356),
(303,357),
(303,358),
(303,359),
(303,360),
(303,361),
(303,362),
(303,363);

/*Table structure for table `post_post_video` */

DROP TABLE IF EXISTS `post_post_video`;

CREATE TABLE `post_post_video` (
  `post_id` bigint NOT NULL,
  `post_video_id` bigint NOT NULL,
  UNIQUE KEY `UK9lht6m2gaykw1rgxlvrdckym2` (`post_video_id`),
  KEY `FKth8okgg2fo25qd93h9qyty2xu` (`post_id`),
  CONSTRAINT `FKkox95ikrv1t1mrqw6bd9uflbx` FOREIGN KEY (`post_video_id`) REFERENCES `video` (`id`),
  CONSTRAINT `FKth8okgg2fo25qd93h9qyty2xu` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_post_video` */

insert  into `post_post_video`(`post_id`,`post_video_id`) values 
(302,1),
(303,2),
(303,3),
(303,4);

/*Table structure for table `post_products` */

DROP TABLE IF EXISTS `post_products`;

CREATE TABLE `post_products` (
  `post_id` bigint NOT NULL,
  `products_id` bigint NOT NULL,
  KEY `FKp4uty1gfcoyhwkfihcue400fp` (`products_id`),
  KEY `FKl1y7fj0gfy509cpv2gvdsak7h` (`post_id`),
  CONSTRAINT `FKl1y7fj0gfy509cpv2gvdsak7h` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKp4uty1gfcoyhwkfihcue400fp` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_products` */

insert  into `post_products`(`post_id`,`products_id`) values 
(252,4),
(302,2),
(302,3),
(303,1),
(303,2),
(303,3),
(303,4);

/*Table structure for table `post_seq` */

DROP TABLE IF EXISTS `post_seq`;

CREATE TABLE `post_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_seq` */

insert  into `post_seq`(`next_val`) values 
(451);

/*Table structure for table `post_shares` */

DROP TABLE IF EXISTS `post_shares`;

CREATE TABLE `post_shares` (
  `post_id` bigint NOT NULL,
  `shares_id` bigint NOT NULL,
  KEY `FKq7ddimcgld76ei6ecmj1kbk51` (`shares_id`),
  KEY `FKp40t6oai6dykb9hux2mp2ti5r` (`post_id`),
  CONSTRAINT `FKp40t6oai6dykb9hux2mp2ti5r` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKq7ddimcgld76ei6ecmj1kbk51` FOREIGN KEY (`shares_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_shares` */

/*Table structure for table `post_views` */

DROP TABLE IF EXISTS `post_views`;

CREATE TABLE `post_views` (
  `post_id` bigint NOT NULL,
  `views_id` bigint NOT NULL,
  KEY `FKgmw16usq7nsw08lsgtmyna1ov` (`views_id`),
  KEY `FK8a1o0yvf8ee1udcj1vp8k5d22` (`post_id`),
  CONSTRAINT `FK8a1o0yvf8ee1udcj1vp8k5d22` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKgmw16usq7nsw08lsgtmyna1ov` FOREIGN KEY (`views_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_views` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `added_date` datetime(6) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dimensions` varchar(255) DEFAULT NULL,
  `discount_price` double NOT NULL,
  `features` varbinary(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `ratings` float DEFAULT NULL,
  `reviews` int NOT NULL,
  `stock_status` varchar(255) DEFAULT NULL,
  `tags` varbinary(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `main_image_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKia727jx9hh6kxtao8hrjuwfj3` (`main_image_id`),
  KEY `FKnuvtfgcf3ohskgoyi6v1eh1jr` (`seller_id`),
  CONSTRAINT `FKia727jx9hh6kxtao8hrjuwfj3` FOREIGN KEY (`main_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKnuvtfgcf3ohskgoyi6v1eh1jr` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`added_date`,`brand`,`category`,`description`,`dimensions`,`discount_price`,`features`,`name`,`origin`,`price`,`quantity`,`ratings`,`reviews`,`stock_status`,`tags`,`weight`,`main_image_id`,`seller_id`,`updated_at`) values 
(1,'2025-02-08 22:23:01.119828','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demo ft\0	product dx','Demo Product','Jinjira',1000,997,0,0,'In Stock','¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demot\0productx','10',52,1,NULL),
(2,'2025-02-08 22:23:32.199779','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demo ft\0	product dx','Demo Product','Jinjira',1000,991,0,0,'In Stock','¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demot\0productx','10',56,1,NULL),
(3,'2025-02-08 22:24:02.826377','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demo ft\0	product dx','Demo Product','Jinjira',1000,996,0,0,'In Stock','¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demot\0productx','10',60,1,NULL),
(4,'2025-02-08 22:24:29.399471','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demo ft\0	product dx','Demo Product','Jinjira',1000,1001,0,0,'In Stock','¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0t\0demot\0productx','10',64,1,NULL);

/*Table structure for table `product_gallery_images` */

DROP TABLE IF EXISTS `product_gallery_images`;

CREATE TABLE `product_gallery_images` (
  `product_id` bigint NOT NULL,
  `gallery_images_id` bigint NOT NULL,
  KEY `FKgdkxcv56uojha4c0w9ptve8gh` (`gallery_images_id`),
  KEY `FK7mu9ltwaca8g53jhy065rvbsr` (`product_id`),
  CONSTRAINT `FK7mu9ltwaca8g53jhy065rvbsr` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKgdkxcv56uojha4c0w9ptve8gh` FOREIGN KEY (`gallery_images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_gallery_images` */

insert  into `product_gallery_images`(`product_id`,`gallery_images_id`) values 
(1,53),
(1,54),
(1,55),
(2,57),
(2,58),
(2,59),
(3,61),
(3,62),
(3,63),
(4,65),
(4,66),
(4,67);

/*Table structure for table `product_seq` */

DROP TABLE IF EXISTS `product_seq`;

CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_seq` */

insert  into `product_seq`(`next_val`) values 
(101);

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `id` bigint NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` tinyint NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlkuwie0au2dru36asng9nywmh` (`product_id`),
  KEY `FKf68lgbsbxl310n0jifwpfqgfh` (`user_id`),
  CONSTRAINT `FKf68lgbsbxl310n0jifwpfqgfh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKlkuwie0au2dru36asng9nywmh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rating` */

/*Table structure for table `rating_seq` */

DROP TABLE IF EXISTS `rating_seq`;

CREATE TABLE `rating_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rating_seq` */

insert  into `rating_seq`(`next_val`) values 
(1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role` */

insert  into `role`(`name`) values 
('ROLE_ADMIN'),
('ROLE_DEV'),
('ROLE_SELLER'),
('ROLE_USER');

/*Table structure for table `role_users` */

DROP TABLE IF EXISTS `role_users`;

CREATE TABLE `role_users` (
  `users_id` bigint NOT NULL,
  `roles_name` varchar(255) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_name`),
  KEY `FKqa80dnmfc2c882xxrrvpy0ek2` (`roles_name`),
  CONSTRAINT `FK9bybxrnjl6xpb73hruvlvaw71` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqa80dnmfc2c882xxrrvpy0ek2` FOREIGN KEY (`roles_name`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role_users` */

insert  into `role_users`(`users_id`,`roles_name`) values 
(1,'ROLE_ADMIN'),
(1,'ROLE_DEV'),
(1,'ROLE_SELLER'),
(1,'ROLE_USER');

/*Table structure for table `sponsor` */

DROP TABLE IF EXISTS `sponsor`;

CREATE TABLE `sponsor` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK44py0gdjhwe3x78oj5dqmkmsb` (`image_id`),
  CONSTRAINT `FK991n1lqu77tf3gs0g2fbnj7e4` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sponsor` */

/*Table structure for table `sponsor_seq` */

DROP TABLE IF EXISTS `sponsor_seq`;

CREATE TABLE `sponsor_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sponsor_seq` */

insert  into `sponsor_seq`(`next_val`) values 
(1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `about` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `discord` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` enum('FEMALE','MALE','NOT_SPECIFIED') DEFAULT NULL,
  `github` varchar(255) DEFAULT NULL,
  `instagram` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `pinterest` varchar(255) DEFAULT NULL,
  `reddit` varchar(255) DEFAULT NULL,
  `snapchat` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','DELETED','INACTIVE','SUSPENDED') DEFAULT NULL,
  `telegram` varchar(255) DEFAULT NULL,
  `tiktok` varchar(255) DEFAULT NULL,
  `twitter` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `whatsapp` varchar(255) DEFAULT NULL,
  `youtube` varchar(255) DEFAULT NULL,
  `cover_picture_id` bigint DEFAULT NULL,
  `profile_picture_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK8hwcv4fherq1eroemm1dx95qa` (`cover_picture_id`),
  UNIQUE KEY `UKk0y369dffniq4d2myb2kbkw2h` (`profile_picture_id`),
  CONSTRAINT `FK1en5bnx3aiap7khlu3noms5oo` FOREIGN KEY (`cover_picture_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FK6x3ltepmdh8f784iq0r13n4u5` FOREIGN KEY (`profile_picture_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`about`,`address`,`discord`,`dob`,`email`,`facebook`,`full_name`,`gender`,`github`,`instagram`,`linkedin`,`password`,`phone_number`,`pinterest`,`reddit`,`snapchat`,`status`,`telegram`,`tiktok`,`twitter`,`username`,`website`,`whatsapp`,`youtube`,`cover_picture_id`,`profile_picture_id`) values 
(1,NULL,'123 Demo Street, Demo City',NULL,'2000-01-01','demo@example.com',NULL,'Demo User','MALE',NULL,NULL,NULL,'$2a$10$9dMDtZMdtFzRrEIhXFydZe1yA1D/LemHdXj6fcxtyLpE0r.Lkwo2e','1234567890',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'demoUser',NULL,NULL,NULL,152,153);

/*Table structure for table `users_seq` */

DROP TABLE IF EXISTS `users_seq`;

CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users_seq` */

insert  into `users_seq`(`next_val`) values 
(51);

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2k5fkq9vwjoxc3ne82hh280t0` (`user_id`),
  CONSTRAINT `FK2k5fkq9vwjoxc3ne82hh280t0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `video` */

insert  into `video`(`id`,`description`,`name`,`path`,`url`,`user_id`) values 
(1,NULL,'demoUser_1739268193618_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4','src/main/resources/static/videos/demoUser/demoUser_1739268193618_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4','/videos/demoUser/demoUser_1739268193618_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4',1),
(2,NULL,'demoUser_1739268691694_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-25.mp4','src/main/resources/static/videos/demoUser/demoUser_1739268691694_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-25.mp4','/videos/demoUser/demoUser_1739268691694_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-25.mp4',1),
(3,NULL,'demoUser_1739268691649_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4','src/main/resources/static/videos/demoUser/demoUser_1739268691649_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4','/videos/demoUser/demoUser_1739268691649_PostService.java - sdcianSpring - Visual Studio Code 2025-02-11 16-01-12.mp4',1),
(4,NULL,'demoUser_1739268691694_SDC South Dhaka Cyclists - Google Chrome 2025-02-03 16-16-39.mp4','src/main/resources/static/videos/demoUser/demoUser_1739268691694_SDC South Dhaka Cyclists - Google Chrome 2025-02-03 16-16-39.mp4','/videos/demoUser/demoUser_1739268691694_SDC South Dhaka Cyclists - Google Chrome 2025-02-03 16-16-39.mp4',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
