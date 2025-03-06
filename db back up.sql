/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.32 : Database - sdcian
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
(1,1),
(32,32);

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
(58,1,1,1),
(59,3,1,3),
(60,1,1,4),
(102,1,1,2),
(152,1,1,6);

/*Table structure for table `cart_item_seq` */

DROP TABLE IF EXISTS `cart_item_seq`;

CREATE TABLE `cart_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_item_seq` */

insert  into `cart_item_seq`(`next_val`) values 
(251);

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_group` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `group_image_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `last_message_id` bigint DEFAULT NULL,
  `last_message` varchar(255) DEFAULT NULL,
  `last_message_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb1ejj8ly97668wqohrj3n2r6g` (`creator_id`),
  KEY `FK4g8l141s4k4oywomjhhp385ub` (`group_image_id`),
  KEY `FKfis0e31kifmviy0qyyub5jqm2` (`last_message_id`),
  CONSTRAINT `FK4g8l141s4k4oywomjhhp385ub` FOREIGN KEY (`group_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKb1ejj8ly97668wqohrj3n2r6g` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKfis0e31kifmviy0qyyub5jqm2` FOREIGN KEY (`last_message_id`) REFERENCES `messege` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat` */

insert  into `chat`(`id`,`is_group`,`name`,`creator_id`,`group_image_id`,`created_at`,`updated_at`,`last_message_id`,`last_message`,`last_message_time`) values 
(1,'\0','Demo User',52,NULL,'2025-03-06 12:30:20.582304','2025-03-06 16:12:56.285577',NULL,'qwe','2025-03-06 16:12:56.285577'),
(2,'\0','Jidni Khan',52,NULL,'2025-03-06 12:38:01.449189','2025-03-06 16:02:47.855773',NULL,'kiree','2025-03-06 16:02:47.855773'),
(3,'\0','Demo User',52,NULL,'2025-03-06 12:44:24.057337','2025-03-06 16:27:44.897467',NULL,'heyy','2025-03-06 16:27:44.897467'),
(4,'\0','Mahid Ahmed Tonmoy2',52,NULL,'2025-03-06 16:14:10.646453','2025-03-06 16:14:13.455927',NULL,'hi','2025-03-06 16:14:13.455927');

/*Table structure for table `chat_members` */

DROP TABLE IF EXISTS `chat_members`;

CREATE TABLE `chat_members` (
  `chat_id` bigint NOT NULL,
  `members_id` bigint NOT NULL,
  KEY `FKejh3xk8tktifvk858ypgtqft5` (`members_id`),
  KEY `FKcjnigrgwbin0pdph6mdphhp6i` (`chat_id`),
  CONSTRAINT `FKcjnigrgwbin0pdph6mdphhp6i` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKejh3xk8tktifvk858ypgtqft5` FOREIGN KEY (`members_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_members` */

insert  into `chat_members`(`chat_id`,`members_id`) values 
(1,1),
(2,52),
(3,1),
(3,52),
(1,52),
(4,2),
(4,52);

/*Table structure for table `chat_messages` */

DROP TABLE IF EXISTS `chat_messages`;

CREATE TABLE `chat_messages` (
  `chat_id` bigint NOT NULL,
  `messages_id` bigint NOT NULL,
  UNIQUE KEY `UKmrq0rmc439okhdws2rxsjjhdn` (`messages_id`),
  KEY `FKb27mi3082eolv7k6tavhgq3wc` (`chat_id`),
  CONSTRAINT `FKb27mi3082eolv7k6tavhgq3wc` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKs0fdc1u0qkde13bus4lsujhuw` FOREIGN KEY (`messages_id`) REFERENCES `messege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_messages` */

/*Table structure for table `chat_requested_members` */

DROP TABLE IF EXISTS `chat_requested_members`;

CREATE TABLE `chat_requested_members` (
  `chat_id` bigint NOT NULL,
  `requested_members_id` bigint NOT NULL,
  KEY `FKqooaqm85fv6a0o6vrmg0id7p3` (`requested_members_id`),
  KEY `FKq7fw2nr00u6q6gcmlirf46yvk` (`chat_id`),
  CONSTRAINT `FKq7fw2nr00u6q6gcmlirf46yvk` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  CONSTRAINT `FKqooaqm85fv6a0o6vrmg0id7p3` FOREIGN KEY (`requested_members_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_requested_members` */

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
(208,'chup',62,NULL,1,'','\0','\0','2025-02-13 00:29:30.233721','2025-02-13 00:29:30.233721'),
(252,'ok',61,NULL,1,'','\0','\0','2025-02-16 04:24:18.178098','2025-02-16 04:24:18.178098'),
(302,'kire',NULL,102,12,'','\0','\0','2025-02-18 18:09:11.938238','2025-02-18 18:09:11.938238'),
(303,'ki',302,NULL,32,'','\0','\0','2025-02-18 18:09:39.005737','2025-02-18 18:09:39.005737'),
(304,'kier',NULL,102,32,'','\0','\0','2025-02-18 18:09:55.674661','2025-02-18 18:09:55.674661');

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
(401);

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
(203,'2025-02-15',NULL,'lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-14 18:57:46.000000','Sharighat',1,504,'2025-02-15 00:58:04.767608','2025-02-15 00:58:04.767608'),
(252,'2025-02-20','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-19 06:03:13.000000','Sharighat',1,702,'2025-02-19 12:03:24.246700','2025-02-19 12:03:24.246700'),
(253,'2025-02-21','FRIDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-21 07:00:00.000000','Sharighat',1,703,'2025-02-19 12:03:50.148404','2025-02-19 12:03:50.148404'),
(254,'2025-02-22','SATURDAY','lorem ipsum dolor sit amet consectetur adipiscing elit',20,2,'Kamalapur','Chill Ride','2025-02-22 07:00:00.000000','Sharighat',1,704,'2025-02-19 12:04:12.576597','2025-02-19 12:04:12.576597');

/*Table structure for table `event_seq` */

DROP TABLE IF EXISTS `event_seq`;

CREATE TABLE `event_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event_seq` */

insert  into `event_seq`(`next_val`) values 
(351);

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
(504,NULL,'demoUser_1739559480930_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739559480930_th2.jpg','/images/demoUser/demoUser_1739559480930_th2.jpg',1),
(552,NULL,'mahidtonmoy_1739875258460_mahid ahmed tonmoy.jpg','src/main/resources/static/images/mahidtonmoy/mahidtonmoy_1739875258460_mahid ahmed tonmoy.jpg','/images/mahidtonmoy/mahidtonmoy_1739875258460_mahid ahmed tonmoy.jpg',2),
(553,NULL,'mahidtonmoy_1739875299525_th.jpg','src/main/resources/static/images/mahidtonmoy/mahidtonmoy_1739875299525_th.jpg','/images/mahidtonmoy/mahidtonmoy_1739875299525_th.jpg',2),
(602,NULL,'shakibalhasan_1739879158928_sakib al hasan.webp','src/main/resources/static/images/shakibalhasan/shakibalhasan_1739879158928_sakib al hasan.webp','/images/shakibalhasan/shakibalhasan_1739879158928_sakib al hasan.webp',4),
(603,NULL,'shakibalhasan_1739879227691_sakib al hasan cover.jpg','src/main/resources/static/images/shakibalhasan/shakibalhasan_1739879227691_sakib al hasan cover.jpg','/images/shakibalhasan/shakibalhasan_1739879227691_sakib al hasan cover.jpg',4),
(604,NULL,'aisharahman_1739879293841_aisharahman.jpg','src/main/resources/static/images/aisharahman/aisharahman_1739879293841_aisharahman.jpg','/images/aisharahman/aisharahman_1739879293841_aisharahman.jpg',5),
(605,NULL,'aisharahman_1739879353974_images.png','src/main/resources/static/images/aisharahman/aisharahman_1739879353974_images.png','/images/aisharahman/aisharahman_1739879353974_images.png',5),
(652,NULL,'imranali_1739880467310_download.jpg','src/main/resources/static/images/imranali/imranali_1739880467310_download.jpg','/images/imranali/imranali_1739880467310_download.jpg',12),
(702,NULL,'demoUser_1739944998967_th.jpg','src/main/resources/static/images/demoUser/demoUser_1739944998967_th.jpg','/images/demoUser/demoUser_1739944998967_th.jpg',1),
(703,NULL,'demoUser_1739945013738_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739945013738_th2.jpg','/images/demoUser/demoUser_1739945013738_th2.jpg',1),
(704,NULL,'demoUser_1739945050243_th2.jpg','src/main/resources/static/images/demoUser/demoUser_1739945050243_th2.jpg','/images/demoUser/demoUser_1739945050243_th2.jpg',1);

/*Table structure for table `image_seq` */

DROP TABLE IF EXISTS `image_seq`;

CREATE TABLE `image_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image_seq` */

insert  into `image_seq`(`next_val`) values 
(801);

/*Table structure for table `memo` */

DROP TABLE IF EXISTS `memo`;

CREATE TABLE `memo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `buyer_address` varchar(255) DEFAULT NULL,
  `buyer_email` varchar(255) DEFAULT NULL,
  `buyer_name` varchar(255) DEFAULT NULL,
  `buyer_phone_number` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `delivery_charge` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `memo_number` int DEFAULT NULL,
  `service_charge` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `buyer_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkcpub2eyaafvs8hdwfl72lav2` (`buyer_id`),
  KEY `FKe3iwths0lng7f5q3witlk1wdm` (`seller_id`),
  CONSTRAINT `FKe3iwths0lng7f5q3witlk1wdm` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkcpub2eyaafvs8hdwfl72lav2` FOREIGN KEY (`buyer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `memo` */

insert  into `memo`(`id`,`buyer_address`,`buyer_email`,`buyer_name`,`buyer_phone_number`,`date`,`delivery_charge`,`discount`,`memo_number`,`service_charge`,`subtotal`,`total`,`buyer_id`,`seller_id`) values 
(1,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-27',50,10,1,100,0,140,NULL,1),
(3,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-27',50,10,1,100,900,1040,NULL,1),
(4,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-27',50,10,1,100,900,1040,NULL,1),
(5,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-27',50,10,2,100,900,1040,NULL,1),
(6,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-28',50,10,3,100,0,140,NULL,1),
(7,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-28',50,10,4,100,891000,891140,NULL,1),
(8,'123 Main St, Anytown USA','9i9ZU@example.com','John Doe','1234567890','2025-02-28',50,10,5,100,2674800,2674940,NULL,1);

/*Table structure for table `memo_item` */

DROP TABLE IF EXISTS `memo_item`;

CREATE TABLE `memo_item` (
  `id` bigint NOT NULL,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8wr4eci9x7jqb864aouwrsmub` (`product_id`),
  CONSTRAINT `FK8wr4eci9x7jqb864aouwrsmub` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `memo_item` */

insert  into `memo_item`(`id`,`price`,`quantity`,`product_id`) values 
(1,900,1,1),
(2,900,1,1),
(3,900,1,2),
(52,900,990,1),
(53,900,991,2),
(54,900,991,3),
(55,900,990,4);

/*Table structure for table `memo_item_seq` */

DROP TABLE IF EXISTS `memo_item_seq`;

CREATE TABLE `memo_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `memo_item_seq` */

insert  into `memo_item_seq`(`next_val`) values 
(151);

/*Table structure for table `memo_memo_items` */

DROP TABLE IF EXISTS `memo_memo_items`;

CREATE TABLE `memo_memo_items` (
  `memo_id` bigint NOT NULL,
  `memo_items_id` bigint NOT NULL,
  UNIQUE KEY `UK1c4oinuispnrpgi42806r5auv` (`memo_items_id`),
  KEY `FKr77g7toraykv19as8fiu4frqe` (`memo_id`),
  CONSTRAINT `FK6w35pyops42qvap7gikuwwhs3` FOREIGN KEY (`memo_items_id`) REFERENCES `memo_item` (`id`),
  CONSTRAINT `FKr77g7toraykv19as8fiu4frqe` FOREIGN KEY (`memo_id`) REFERENCES `memo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `memo_memo_items` */

insert  into `memo_memo_items`(`memo_id`,`memo_items_id`) values 
(3,1),
(4,2),
(5,3),
(7,52),
(8,53),
(8,54),
(8,55);

/*Table structure for table `messege` */

DROP TABLE IF EXISTS `messege`;

CREATE TABLE `messege` (
  `id` bigint NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `is_sent` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `chat_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKthey05rcl96h50moqkvlpoc2n` (`chat_id`),
  KEY `UK8f2ldfa5t3t50ynfp8jq78kkq` (`sender_id`),
  CONSTRAINT `FK9tt2ik57o7bmatdu6m8sct02g` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKthey05rcl96h50moqkvlpoc2n` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `messege` */

insert  into `messege`(`id`,`is_deleted`,`is_read`,`is_sent`,`message`,`sender_id`,`created_at`,`updated_at`,`chat_id`) values 
(30,'\0','\0','\0',' ',52,'2025-03-06 14:39:02.606901','2025-03-06 14:39:02.606901',3),
(53,'\0','\0','\0',' ',52,'2025-03-06 15:00:41.731329','2025-03-06 15:00:41.731329',3),
(54,'\0','\0','\0',' ',52,'2025-03-06 15:03:16.491241','2025-03-06 15:03:16.491241',3),
(55,'\0','\0','\0','hi',52,'2025-03-06 15:08:11.470252','2025-03-06 15:08:11.470252',3),
(56,'\0','\0','\0',' ',52,'2025-03-06 15:08:17.349742','2025-03-06 15:08:17.349742',3),
(57,'\0','\0','\0',' ',52,'2025-03-06 15:08:21.518358','2025-03-06 15:08:21.518358',3),
(58,'\0','\0','\0','hell',52,'2025-03-06 15:08:24.433707','2025-03-06 15:08:24.433707',3),
(59,'\0','\0','\0','hell',52,'2025-03-06 15:08:27.788217','2025-03-06 15:08:27.788217',3),
(60,'\0','\0','\0','hi',52,'2025-03-06 15:08:29.691809','2025-03-06 15:08:29.691809',3),
(61,'\0','\0','\0','hell',52,'2025-03-06 15:09:03.398109','2025-03-06 15:09:03.398109',3),
(62,'\0','\0','\0','hi',52,'2025-03-06 15:09:25.898910','2025-03-06 15:09:25.898910',3),
(63,'\0','\0','\0','hi',52,'2025-03-06 15:09:58.393484','2025-03-06 15:09:58.393484',3),
(64,'\0','\0','\0','hello',52,'2025-03-06 15:10:36.189531','2025-03-06 15:10:36.189531',3),
(102,'\0','\0','\0','hiiii',52,'2025-03-06 15:15:56.330824','2025-03-06 15:15:56.330824',3),
(103,'\0','\0','\0','hiiii',52,'2025-03-06 15:16:45.033023','2025-03-06 15:16:45.033023',3),
(104,'\0','\0','\0','hiiii',52,'2025-03-06 15:16:45.987490','2025-03-06 15:16:45.987490',3),
(105,'\0','\0','\0','hiiii',52,'2025-03-06 15:16:46.572497','2025-03-06 15:16:46.572497',3),
(152,'\0','\0','\0','message 233',52,'2025-03-06 15:24:50.812991','2025-03-06 15:24:50.812991',2),
(153,'\0','\0','\0','messege 666',52,'2025-03-06 15:24:59.685369','2025-03-06 15:24:59.685369',1),
(154,'\0','\0','\0','hummm',52,'2025-03-06 15:25:21.082807','2025-03-06 15:25:21.082807',1),
(155,'\0','\0','\0','accha',52,'2025-03-06 15:25:26.261486','2025-03-06 15:25:26.261486',2),
(202,'\0','\0','\0','hi',1,'2025-03-06 15:58:11.310556','2025-03-06 15:58:11.310556',3),
(203,'\0','\0','\0','hi',1,'2025-03-06 15:58:13.009556','2025-03-06 15:58:13.009556',3),
(204,'\0','\0','\0','hi',1,'2025-03-06 15:58:14.927995','2025-03-06 15:58:14.927995',3),
(205,'\0','\0','\0','hi',1,'2025-03-06 15:58:15.096926','2025-03-06 15:58:15.096926',3),
(206,'\0','\0','\0','hi',1,'2025-03-06 15:58:15.321370','2025-03-06 15:58:15.321370',3),
(207,'\0','\0','\0','hi',1,'2025-03-06 15:58:17.675918','2025-03-06 15:58:17.675918',3),
(208,'\0','\0','\0','hi',1,'2025-03-06 15:58:17.822593','2025-03-06 15:58:17.822593',3),
(209,'\0','\0','\0','hi',1,'2025-03-06 15:58:27.674371','2025-03-06 15:58:27.674371',3),
(210,'\0','\0','\0','hi',1,'2025-03-06 15:58:28.238435','2025-03-06 15:58:28.238435',3),
(211,'\0','\0','\0','hi',1,'2025-03-06 15:58:28.393401','2025-03-06 15:58:28.393401',3),
(212,'\0','\0','\0','ok',52,'2025-03-06 16:01:40.650195','2025-03-06 16:01:40.650195',3),
(213,'\0','\0','\0','kire',52,'2025-03-06 16:02:39.066095','2025-03-06 16:02:39.066095',3),
(214,'\0','\0','\0','kiree',52,'2025-03-06 16:02:47.855773','2025-03-06 16:02:47.855773',2),
(215,'\0','\0','\0','ki khobor',52,'2025-03-06 16:02:53.265807','2025-03-06 16:02:53.265807',1),
(216,'\0','\0','\0','valoi',1,'2025-03-06 16:03:19.859955','2025-03-06 16:03:19.859955',1),
(217,'\0','\0','\0','hae',1,'2025-03-06 16:03:27.366142','2025-03-06 16:03:27.366142',3),
(252,'\0','\0','\0','1234',1,'2025-03-06 16:11:44.012035','2025-03-06 16:11:44.012035',1),
(253,'\0','\0','\0','gu',52,'2025-03-06 16:12:42.628957','2025-03-06 16:12:42.628957',1),
(254,'\0','\0','\0','qwe',52,'2025-03-06 16:12:56.285577','2025-03-06 16:12:56.285577',1),
(255,'\0','\0','\0','hi',52,'2025-03-06 16:14:13.455927','2025-03-06 16:14:13.455927',4),
(256,'\0','\0','\0','hakj',52,'2025-03-06 16:16:45.050047','2025-03-06 16:16:45.050047',3),
(257,'\0','\0','\0','heyy',52,'2025-03-06 16:27:44.897467','2025-03-06 16:27:44.897467',3);

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
(351);

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
(257,1000,1,'PROCESSING',165,4,1,1,NULL,'2025-02-16 03:59:54.050252'),
(258,1000,3,'OUT_FOR_DELIVERY',166,2,1,1,NULL,'2025-02-15 23:09:31.152611'),
(259,1000,3,'OUT_FOR_DELIVERY',167,2,1,1,NULL,'2025-02-15 23:08:42.616253'),
(302,900,3,'PROCESSING',202,2,1,1,'2025-02-16 03:53:17.714398','2025-02-16 03:59:54.465664'),
(303,900,1,'PROCESSING',202,1,1,1,'2025-02-16 03:53:17.714398','2025-02-16 03:59:54.986543'),
(304,900,1,'PROCESSING',202,3,1,1,'2025-02-16 03:53:17.714398','2025-02-16 03:59:55.570972'),
(305,900,1,'REJECTED',202,4,1,1,'2025-02-16 03:53:17.714398','2025-02-16 04:28:35.577292'),
(306,900,3,'PROCESSING',203,2,1,1,'2025-02-16 03:57:10.814645','2025-02-19 12:07:07.389114'),
(307,900,1,'PENDING',203,1,1,1,'2025-02-16 03:57:10.814645','2025-02-16 03:57:10.814645'),
(308,900,1,'PENDING',203,3,1,1,'2025-02-16 03:57:10.814645','2025-02-16 03:57:10.814645'),
(309,900,1,'PENDING',203,4,1,1,'2025-02-16 03:57:10.814645','2025-02-16 03:57:10.814645'),
(310,900,3,'PENDING',204,2,1,1,'2025-02-16 03:59:00.954046','2025-02-16 03:59:00.954046'),
(311,900,1,'PENDING',204,1,1,1,'2025-02-16 03:59:00.955043','2025-02-16 03:59:00.955043'),
(312,900,1,'PENDING',204,3,1,1,'2025-02-16 03:59:00.955043','2025-02-16 03:59:00.955043'),
(313,900,1,'PENDING',204,4,1,1,'2025-02-16 03:59:00.955043','2025-02-16 03:59:00.955043'),
(314,900,3,'PROCESSING',205,2,1,1,'2025-02-16 03:59:07.602032','2025-02-16 04:00:05.384607'),
(315,900,1,'PROCESSING',205,1,1,1,'2025-02-16 03:59:07.602032','2025-02-16 04:00:05.906415'),
(316,900,1,'PROCESSING',205,3,1,1,'2025-02-16 03:59:07.602032','2025-02-16 04:00:06.242282'),
(317,900,1,'PROCESSING',205,4,1,1,'2025-02-16 03:59:07.603058','2025-02-16 04:00:06.563003'),
(318,900,3,'PROCESSING',206,2,1,1,'2025-02-16 03:59:14.069169','2025-02-16 04:00:06.922445'),
(319,900,1,'PROCESSING',206,1,1,1,'2025-02-16 03:59:14.069169','2025-02-16 04:00:07.096255'),
(320,900,1,'PROCESSING',206,3,1,1,'2025-02-16 03:59:14.069169','2025-02-16 04:00:07.448874'),
(321,900,1,'PROCESSING',206,4,1,1,'2025-02-16 03:59:14.069169','2025-02-16 03:59:53.393766'),
(322,900,3,'PROCESSING',207,2,1,1,'2025-02-16 03:59:17.547543','2025-02-16 04:00:07.821223'),
(323,900,1,'PROCESSING',207,1,1,1,'2025-02-16 03:59:17.547543','2025-02-16 04:00:08.178284'),
(324,900,1,'PENDING',207,3,1,1,'2025-02-16 03:59:17.547543','2025-02-16 03:59:17.547543'),
(325,900,1,'PENDING',207,4,1,1,'2025-02-16 03:59:17.547543','2025-02-16 03:59:17.547543'),
(326,900,3,'PENDING',208,2,1,1,'2025-02-16 03:59:21.205430','2025-02-16 03:59:21.205430'),
(327,900,1,'PENDING',208,1,1,1,'2025-02-16 03:59:21.205430','2025-02-16 03:59:21.205430'),
(328,900,1,'PENDING',208,3,1,1,'2025-02-16 03:59:21.205430','2025-02-16 03:59:21.205430'),
(329,900,1,'PENDING',208,4,1,1,'2025-02-16 03:59:21.205430','2025-02-16 03:59:21.205430'),
(330,900,3,'PENDING',209,2,1,1,'2025-02-16 03:59:23.632390','2025-02-16 03:59:23.632390'),
(331,900,1,'PENDING',209,1,1,1,'2025-02-16 03:59:23.632390','2025-02-16 03:59:23.632390'),
(332,900,1,'PENDING',209,3,1,1,'2025-02-16 03:59:23.633386','2025-02-16 03:59:23.633386'),
(333,900,1,'PENDING',209,4,1,1,'2025-02-16 03:59:23.633386','2025-02-16 03:59:23.633386'),
(352,900,1,'PENDING',252,1,1,1,'2025-02-16 04:27:18.423521','2025-02-16 04:27:18.423521'),
(353,900,3,'PENDING',252,3,1,1,'2025-02-16 04:27:18.423521','2025-02-16 04:27:18.423521'),
(402,900,3,'PENDING',302,3,1,1,'2025-02-17 15:43:47.716660','2025-02-17 15:43:47.716660'),
(403,900,1,'PENDING',302,4,1,1,'2025-02-17 15:43:47.717165','2025-02-17 15:43:47.717165'),
(452,900,1,'PENDING',352,1,1,1,'2025-02-17 18:10:54.712072','2025-02-17 18:10:54.712072'),
(453,900,3,'PENDING',352,3,1,1,'2025-02-17 18:10:54.713107','2025-02-17 18:10:54.713107'),
(454,900,1,'PENDING',352,4,1,1,'2025-02-17 18:10:54.713107','2025-02-17 18:10:54.713107'),
(455,900,1,'PENDING',353,1,1,1,'2025-02-17 18:11:12.790556','2025-02-17 18:11:12.790556'),
(456,900,3,'PENDING',353,3,1,1,'2025-02-17 18:11:12.790556','2025-02-17 18:11:12.790556'),
(457,900,1,'PENDING',353,4,1,1,'2025-02-17 18:11:12.790556','2025-02-17 18:11:12.790556'),
(458,900,1,'PENDING',354,1,1,1,'2025-02-17 18:13:03.196396','2025-02-17 18:13:03.196396'),
(459,900,3,'PENDING',354,3,1,1,'2025-02-17 18:13:03.196396','2025-02-17 18:13:03.196396'),
(460,900,1,'PENDING',354,4,1,1,'2025-02-17 18:13:03.196396','2025-02-17 18:13:03.196396'),
(502,900,1,'PENDING',402,1,1,1,'2025-02-19 12:06:31.048330','2025-02-19 12:06:31.048330'),
(503,900,3,'PENDING',402,3,1,1,'2025-02-19 12:06:31.048330','2025-02-19 12:06:31.048330'),
(504,900,1,'PENDING',402,4,1,1,'2025-02-19 12:06:31.048330','2025-02-19 12:06:31.048330'),
(505,900,1,'PENDING',402,2,1,1,'2025-02-19 12:06:31.048330','2025-02-19 12:06:31.048330'),
(506,43.35,1,'PENDING',402,6,1,7,'2025-02-19 12:06:31.048330','2025-02-19 12:06:31.048330');

/*Table structure for table `order_item_seq` */

DROP TABLE IF EXISTS `order_item_seq`;

CREATE TABLE `order_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item_seq` */

insert  into `order_item_seq`(`next_val`) values 
(601);

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
(167,'123 Demo Street, Demo City','2025-02-13 02:23:03.942611','demo@example.com','Demo User',0,'rocket','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(202,'123 Demo Street, Demo City','2025-02-16 03:53:17.714398','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(203,'123 Demo Street, Demo City','2025-02-16 03:57:10.814645','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(204,'123 Demo Street, Demo City','2025-02-16 03:59:00.954046','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(205,'123 Demo Street, Demo City','2025-02-16 03:59:07.602032','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(206,'123 Demo Street, Demo City','2025-02-16 03:59:14.069169','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(207,'123 Demo Street, Demo City','2025-02-16 03:59:17.547543','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(208,'123 Demo Street, Demo City','2025-02-16 03:59:21.205430','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(209,'123 Demo Street, Demo City','2025-02-16 03:59:23.632390','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(252,'123 Demo Street, Demo City','2025-02-16 04:27:18.419218','demo@example.com','Demo User2',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(302,'123 Demo Street, Demo City','2025-02-17 15:43:47.687288','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(352,'123 Demo Street, Demo City','2025-02-17 18:10:54.690586','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(353,'123 Demo Street, Demo City','2025-02-17 18:11:12.790556','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1),
(354,NULL,'2025-02-17 18:13:03.196396',NULL,NULL,0,'bkash',NULL,NULL,NULL,'abcyzx',NULL,1),
(402,'123 Demo Street, Demo City','2025-02-19 12:06:31.044417','demo@example.com','Demo User',0,'bkash','1234567890',NULL,'1234567890','abcyzx','demoUser',1);

/*Table structure for table `orders_seq` */

DROP TABLE IF EXISTS `orders_seq`;

CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders_seq` */

insert  into `orders_seq`(`next_val`) values 
(501);

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
(1,'\0','','','Dhaka','demo text',1,NULL,'2025-02-11 16:20:11.000000','2025-02-18 00:24:12.190740'),
(2,'\0','','','Dhaka','demo text',2,NULL,'2025-02-11 16:20:11.404629','2025-02-11 16:20:11.404629'),
(52,'\0','','','Dhaka','demo text',3,NULL,'2025-02-11 16:20:11.000000','2025-02-11 16:20:11.404629'),
(102,'\0','\0','','Dhaka','demo text',4,NULL,'2025-02-07 16:20:11.404629','2025-02-11 16:20:11.404629'),
(252,'\0','\0','','faka','sdf',5,NULL,'2025-02-07 16:20:11.000000','2025-02-11 16:20:11.404629'),
(302,'\0','\0','','chaka','Some thing big is comming',6,NULL,'2025-02-08 06:00:01.000000','2025-02-11 16:20:11.404629'),
(303,'\0','\0','','moha','Something Bigger is comming',7,NULL,'2025-02-10 06:00:01.000000','2025-02-11 16:20:11.404629'),
(352,'\0','\0','','','123',8,NULL,'2025-02-10 16:20:11.404629','2025-02-11 16:20:11.404629'),
(400,'\0','\0','','Dhaka','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',1,NULL,'2025-02-12 10:00:00.000000','2025-02-12 10:05:00.000000'),
(401,'\0','\0','','Chittagong','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',2,1,'2025-02-12 10:01:00.000000','2025-02-12 10:06:00.000000'),
(402,'\0','\0','','Khulna','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',3,NULL,'2025-02-12 10:02:00.000000','2025-02-12 10:07:00.000000'),
(403,'\0','\0','','Rajshahi','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',4,2,'2025-02-12 10:03:00.000000','2025-02-12 10:08:00.000000'),
(404,'\0','\0','','Barisal','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',5,NULL,'2025-02-12 10:04:00.000000','2025-02-12 10:09:00.000000'),
(405,'\0','\0','','Sylhet','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',6,52,'2025-02-12 10:05:00.000000','2025-02-12 10:10:00.000000'),
(406,'\0','\0','','Rangpur','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',7,NULL,'2025-02-12 10:06:00.000000','2025-02-12 10:11:00.000000'),
(407,'\0','\0','','Cox\'s Bazar','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',8,102,'2025-02-12 10:07:00.000000','2025-02-12 10:12:00.000000'),
(408,'\0','\0','','Dhaka','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',9,NULL,'2025-02-12 10:08:00.000000','2025-02-12 10:13:00.000000'),
(409,'\0','\0','','Chittagong','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',10,252,'2025-02-12 10:09:00.000000','2025-02-12 10:14:00.000000'),
(410,'\0','\0','','Khulna','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',11,NULL,'2025-02-12 10:10:00.000000','2025-02-12 10:15:00.000000'),
(411,'\0','\0','','Rajshahi','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',12,1,'2025-02-12 10:11:00.000000','2025-02-12 10:16:00.000000'),
(412,'\0','\0','','Barisal','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',13,NULL,'2025-02-12 10:12:00.000000','2025-02-12 10:17:00.000000'),
(413,'\0','\0','','Sylhet','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',14,2,'2025-02-12 10:13:00.000000','2025-02-12 10:18:00.000000'),
(414,'\0','\0','','Rangpur','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',15,NULL,'2025-02-12 10:14:00.000000','2025-02-12 10:19:00.000000'),
(415,'\0','\0','','Cox\'s Bazar','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',16,52,'2025-02-12 10:15:00.000000','2025-02-12 10:20:00.000000'),
(416,'\0','\0','','Dhaka','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',17,NULL,'2025-02-12 10:16:00.000000','2025-02-12 10:21:00.000000'),
(417,'\0','\0','','Chittagong','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',18,102,'2025-02-12 10:17:00.000000','2025-02-12 10:22:00.000000'),
(418,'\0','\0','','Khulna','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',19,NULL,'2025-02-12 10:18:00.000000','2025-02-12 10:23:00.000000'),
(419,'\0','\0','','Rajshahi','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',20,252,'2025-02-12 10:19:00.000000','2025-02-12 10:24:00.000000'),
(420,'\0','\0','','Barisal','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',21,NULL,'2025-02-12 10:20:00.000000','2025-02-12 10:25:00.000000'),
(421,'\0','\0','','Sylhet','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',22,1,'2025-02-12 10:21:00.000000','2025-02-12 10:26:00.000000'),
(422,'\0','\0','','Rangpur','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',23,NULL,'2025-02-12 10:22:00.000000','2025-02-12 10:27:00.000000'),
(423,'\0','\0','','Cox\'s Bazar','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',24,2,'2025-02-12 10:23:00.000000','2025-02-12 10:28:00.000000'),
(424,'\0','\0','','Dhaka','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',25,NULL,'2025-02-12 10:24:00.000000','2025-02-12 10:29:00.000000'),
(425,'\0','\0','','Chittagong','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',26,52,'2025-02-12 10:25:00.000000','2025-02-12 10:30:00.000000'),
(426,'\0','\0','','Khulna','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',27,NULL,'2025-02-12 10:26:00.000000','2025-02-12 10:31:00.000000'),
(427,'\0','\0','','Rajshahi','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',28,102,'2025-02-12 10:27:00.000000','2025-02-12 10:32:00.000000'),
(428,'\0','\0','','Barisal','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',29,NULL,'2025-02-12 10:28:00.000000','2025-02-12 10:33:00.000000'),
(429,'\0','\0','','Sylhet','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',30,252,'2025-02-12 10:29:00.000000','2025-02-12 10:34:00.000000'),
(430,'\0','\0','','Rangpur','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',31,NULL,'2025-02-12 10:30:00.000000','2025-02-12 10:35:00.000000'),
(431,'\0','\0','','Cox\'s Bazar','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',32,1,'2025-02-12 10:31:00.000000','2025-02-12 10:36:00.000000'),
(432,'\0','\0','','Dhaka','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',33,NULL,'2025-02-12 10:32:00.000000','2025-02-12 10:37:00.000000'),
(433,'\0','\0','','Chittagong','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',34,2,'2025-02-12 10:33:00.000000','2025-02-12 10:38:00.000000'),
(434,'\0','\0','','Khulna','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',35,NULL,'2025-02-12 10:34:00.000000','2025-02-12 10:39:00.000000'),
(435,'\0','\0','','Rajshahi','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',36,52,'2025-02-12 10:35:00.000000','2025-02-12 10:40:00.000000'),
(436,'\0','\0','','Barisal','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',37,NULL,'2025-02-12 10:36:00.000000','2025-02-12 10:41:00.000000'),
(437,'\0','\0','','Sylhet','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',38,102,'2025-02-12 10:37:00.000000','2025-02-12 10:42:00.000000'),
(438,'\0','\0','','Rangpur','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',39,NULL,'2025-02-12 10:38:00.000000','2025-02-12 10:43:00.000000'),
(439,'\0','\0','','Cox\'s Bazar','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',40,252,'2025-02-12 10:39:00.000000','2025-02-12 10:44:00.000000'),
(440,'\0','\0','','Dhaka','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',41,NULL,'2025-02-12 10:40:00.000000','2025-02-12 10:45:00.000000'),
(441,'\0','\0','','Chittagong','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',42,1,'2025-02-12 10:41:00.000000','2025-02-12 10:46:00.000000'),
(442,'\0','\0','','Khulna','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',43,NULL,'2025-02-12 10:42:00.000000','2025-02-12 10:47:00.000000'),
(443,'\0','\0','','Rajshahi','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',44,2,'2025-02-12 10:43:00.000000','2025-02-12 10:48:00.000000'),
(444,'\0','\0','','Barisal','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',45,NULL,'2025-02-12 10:44:00.000000','2025-02-12 10:49:00.000000'),
(445,'\0','\0','','Sylhet','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',46,52,'2025-02-12 10:45:00.000000','2025-02-12 10:50:00.000000'),
(446,'\0','\0','','Rangpur','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',47,NULL,'2025-02-12 10:46:00.000000','2025-02-12 10:51:00.000000'),
(447,'\0','\0','','Cox\'s Bazar','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',48,102,'2025-02-12 10:47:00.000000','2025-02-12 10:52:00.000000'),
(448,'\0','\0','','Dhaka','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',49,NULL,'2025-02-12 10:48:00.000000','2025-02-12 10:53:00.000000'),
(449,'\0','\0','','Chittagong','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',50,252,'2025-02-12 10:49:00.000000','2025-02-12 10:54:00.000000'),
(450,'\0','\0','','Khulna','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',51,NULL,'2025-02-12 10:50:00.000000','2025-02-12 10:55:00.000000'),
(451,'\0','\0','','Rajshahi','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',1,1,'2025-02-12 10:51:00.000000','2025-02-12 10:56:00.000000'),
(452,'\0','\0','','Barisal','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',2,NULL,'2025-02-12 10:52:00.000000','2025-02-12 10:57:00.000000'),
(453,'\0','\0','','Sylhet','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',3,2,'2025-02-12 10:53:00.000000','2025-02-12 10:58:00.000000'),
(454,'\0','\0','','Rangpur','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',4,NULL,'2025-02-12 10:54:00.000000','2025-02-12 10:59:00.000000'),
(455,'\0','\0','','Cox\'s Bazar','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',5,52,'2025-02-12 10:55:00.000000','2025-02-12 11:00:00.000000'),
(456,'\0','\0','','Dhaka','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',6,NULL,'2025-02-12 10:56:00.000000','2025-02-12 11:01:00.000000'),
(457,'\0','\0','','Chittagong','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',7,102,'2025-02-12 10:57:00.000000','2025-02-12 11:02:00.000000'),
(458,'\0','\0','','Khulna','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',8,NULL,'2025-02-12 10:58:00.000000','2025-02-12 11:03:00.000000'),
(459,'\0','\0','','Rajshahi','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',9,252,'2025-02-12 10:59:00.000000','2025-02-12 11:04:00.000000'),
(460,'\0','\0','','Barisal','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',10,NULL,'2025-02-12 11:00:00.000000','2025-02-12 11:05:00.000000'),
(461,'\0','\0','','Sylhet','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',11,1,'2025-02-12 11:01:00.000000','2025-02-12 11:06:00.000000'),
(462,'\0','\0','','Rangpur','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',12,NULL,'2025-02-12 11:02:00.000000','2025-02-12 11:07:00.000000'),
(463,'\0','\0','','Cox\'s Bazar','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',13,2,'2025-02-12 11:03:00.000000','2025-02-12 11:08:00.000000'),
(464,'\0','\0','','Dhaka','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',14,NULL,'2025-02-12 11:04:00.000000','2025-02-12 11:09:00.000000'),
(465,'\0','\0','','Chittagong','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',15,52,'2025-02-12 11:05:00.000000','2025-02-12 11:10:00.000000'),
(466,'\0','\0','','Khulna','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',16,NULL,'2025-02-12 11:06:00.000000','2025-02-12 11:11:00.000000'),
(467,'\0','\0','','Rajshahi','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',17,102,'2025-02-12 11:07:00.000000','2025-02-12 11:12:00.000000'),
(468,'\0','\0','','Barisal','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',18,NULL,'2025-02-12 11:08:00.000000','2025-02-12 11:13:00.000000'),
(469,'\0','\0','','Sylhet','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',19,252,'2025-02-12 11:09:00.000000','2025-02-12 11:14:00.000000'),
(470,'\0','\0','','Rangpur','Early morning ride through the city streets – nothing beats the calm before the rush of traffic. The cool breeze and quiet roads made for a perfect start to my day.',20,NULL,'2025-02-12 11:10:00.000000','2025-02-12 11:15:00.000000'),
(471,'\0','\0','','Cox\'s Bazar','Just discovered a new trail on the outskirts of town. Challenging climbs, exhilarating descents, and breathtaking views at every turn. Can’t wait to go back!',21,1,'2025-02-12 11:11:00.000000','2025-02-12 11:16:00.000000'),
(472,'\0','\0','','Dhaka','Upgraded to a hybrid bike last month, and my daily commute has never been better. It’s fun, eco-friendly, and a great way to clear my mind before work.',22,NULL,'2025-02-12 11:12:00.000000','2025-02-12 11:17:00.000000'),
(473,'\0','\0','','Chittagong','Weekend adventure on two wheels: rode through a scenic forest path, stopped by a small lake, and enjoyed a picnic. Cycling really reconnects me with nature.',23,2,'2025-02-12 11:13:00.000000','2025-02-12 11:18:00.000000'),
(474,'\0','\0','','Khulna','Riding under a brilliant sunset is pure magic. The sky transformed into a canvas of oranges and purples while I pedaled along a quiet country road.',24,NULL,'2025-02-12 11:14:00.000000','2025-02-12 11:19:00.000000'),
(475,'\0','\0','','Rajshahi','Had an amazing group ride today with fellow cycling enthusiasts. We tackled steep hills, shared tips, and ended the day with a well-deserved coffee break.',25,52,'2025-02-12 11:15:00.000000','2025-02-12 11:20:00.000000'),
(476,'\0','\0','','Barisal','Exploring urban backroads has become my new hobby. It’s exciting to find hidden gems – quiet neighborhoods, street art, and the occasional local café.',26,NULL,'2025-02-12 11:16:00.000000','2025-02-12 11:21:00.000000'),
(477,'\0','\0','','Sylhet','Nothing beats the thrill of racing down a long, open road. The rush of wind, the sense of freedom, and the rhythmic sound of pedals keep me coming back for more.',27,102,'2025-02-12 11:17:00.000000','2025-02-12 11:22:00.000000'),
(478,'\0','\0','','Rangpur','After a stressful week, a long ride in the countryside was the perfect remedy. Fresh air, rolling hills, and the steady pace of my bike helped me unwind completely.',28,NULL,'2025-02-12 11:18:00.000000','2025-02-12 11:23:00.000000'),
(479,'\0','\0','','Cox\'s Bazar','Joined a charity cycling event today, and the energy was infectious. It’s inspiring to see so many people riding together for a great cause.',29,252,'2025-02-12 11:19:00.000000','2025-02-12 11:24:00.000000');

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

insert  into `post_likers`(`post_id`,`likers_id`) values 
(1,1),
(2,1),
(52,1),
(302,1),
(303,1),
(406,5),
(407,5),
(252,1),
(252,12),
(102,12);

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

/*Table structure for table `post_reporter` */

DROP TABLE IF EXISTS `post_reporter`;

CREATE TABLE `post_reporter` (
  `post_id` bigint NOT NULL,
  `reporter_id` bigint NOT NULL,
  KEY `FKroo97l91ty4fyhvlvw7kselw7` (`reporter_id`),
  KEY `FK3ukb0o7g4cb775jruma1vlsor` (`post_id`),
  CONSTRAINT `FK3ukb0o7g4cb775jruma1vlsor` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKroo97l91ty4fyhvlvw7kselw7` FOREIGN KEY (`reporter_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_reporter` */

insert  into `post_reporter`(`post_id`,`reporter_id`) values 
(52,1),
(2,1),
(102,1),
(252,1),
(407,5);

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
  `discount_price` float NOT NULL,
  `features` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `ratings` float DEFAULT NULL,
  `reviews` int NOT NULL,
  `stock_status` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `main_image_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `buying_price` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKia727jx9hh6kxtao8hrjuwfj3` (`main_image_id`),
  KEY `FKnuvtfgcf3ohskgoyi6v1eh1jr` (`seller_id`),
  CONSTRAINT `FKia727jx9hh6kxtao8hrjuwfj3` FOREIGN KEY (`main_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKnuvtfgcf3ohskgoyi6v1eh1jr` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`added_date`,`brand`,`category`,`description`,`dimensions`,`discount_price`,`features`,`name`,`origin`,`price`,`quantity`,`ratings`,`reviews`,`stock_status`,`tags`,`weight`,`main_image_id`,`seller_id`,`updated_at`,`buying_price`) values 
(1,'2025-02-08 22:23:01.119828','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'','Demo Product','Jinjira',1000,7,0,0,'In Stock','','10',52,1,'2025-02-28 23:33:42.891308',NULL),
(2,'2025-02-08 22:23:32.199779','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'','Demo Product','Jinjira',1000,0,0,0,'In Stock','','10',56,1,'2025-02-28 23:50:29.761701',NULL),
(3,'2025-02-08 22:24:02.826377','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'','Demo Product','Jinjira',1000,5,0,0,'In Stock','','10',60,1,'2025-02-28 23:50:29.767599',NULL),
(4,'2025-02-08 22:24:29.399471','Demo Brand','Electronics','Demo Product Description','90x90x90',900,'','Demo Product','Jinjira',1000,11,0,0,'In Stock','','10',64,1,'2025-02-28 23:50:29.773580',NULL),
(5,'2025-02-09 09:00:00.000000','Speedster','Bicycles','High performance Speedster Roadster designed for speed and durability.','180x70x110 cm',900,'','Speedster Roadster','USA',1000,100,3.5,20,'In Stock','','15',52,4,'2025-02-09 09:10:00.000000',NULL),
(6,'2025-02-09 09:01:00.000000','CyclePro','Cycling Accessories','Premium quality accessory from CyclePro that enhances your cycling experience.','15x10x5 cm',43.35,'','CyclePro Bike Lock','Germany',51,201,4.1,6,'In Stock','','0.5',56,7,'2025-02-09 09:11:00.000000',NULL),
(7,'2025-02-09 09:02:00.000000','UrbanRider','Bicycles','High performance UrbanRider Urban Glide designed for speed and durability.','180x70x110 cm',901.8,'','UrbanRider Urban Glide','Japan',1002,102,3.7,22,'In Stock','','15',60,10,'2025-02-09 09:12:00.000000',NULL),
(8,'2025-02-09 09:03:00.000000','MountainMaster','Bicycles','High performance MountainMaster Speed Racer designed for speed and durability.','180x70x110 cm',902.7,'','MountainMaster Speed Racer','Taiwan',1003,103,3.8,23,'In Stock','','15',64,13,'2025-02-09 09:13:00.000000',NULL),
(9,'2025-02-09 09:04:00.000000','EcoCycle','Cycling Accessories','Premium quality accessory from EcoCycle that enhances your cycling experience.','15x10x5 cm',45.9,'','EcoCycle Water Bottle','China',54,204,4.4,9,'In Stock','','0.5',52,16,'2025-02-09 09:14:00.000000',NULL),
(10,'2025-02-09 09:05:00.000000','TurboBike','Bicycles','High performance TurboBike Mountain X designed for speed and durability.','180x70x110 cm',905.4,'','TurboBike Mountain X','USA',1005,105,3.6,21,'In Stock','','15',56,19,'2025-02-09 09:15:00.000000',NULL),
(11,'2025-02-09 09:06:00.000000','RideOn','Bicycles','High performance RideOn Urban Glide designed for speed and durability.','180x70x110 cm',906.3,'','RideOn Urban Glide','Germany',1006,106,3.7,21,'In Stock','','15',60,22,'2025-02-09 09:16:00.000000',NULL),
(12,'2025-02-09 09:07:00.000000','VeloCity','Cycling Accessories','Premium quality accessory from VeloCity that enhances your cycling experience.','15x10x5 cm',56.95,'','VeloCity Cycling Gloves','Japan',57,207,4,7,'In Stock','','0.5',64,25,'2025-02-09 09:17:00.000000',NULL),
(13,'2025-02-09 09:08:00.000000','PaceMaker','Bicycles','High performance PaceMaker Speed Racer designed for speed and durability.','180x70x110 cm',908.7,'','PaceMaker Speed Racer','Taiwan',1008,108,3.8,23,'In Stock','','15',52,28,'2025-02-09 09:18:00.000000',NULL),
(14,'2025-02-09 09:09:00.000000','TrailBlazer','Bicycles','High performance TrailBlazer Roadster designed for speed and durability.','180x70x110 cm',909,'','TrailBlazer Roadster','China',1009,109,3.9,24,'In Stock','','15',56,31,'2025-02-09 09:19:00.000000',NULL),
(84,'2025-02-09 10:19:00.000000','TrailBlazer','Cycling Accessories','Premium quality accessory from TrailBlazer that enhances your cycling experience.','15x10x5 cm',109.65,'','TrailBlazer Bike Lock','China',129,219,4.4,9,'In Stock','','0.5',60,49,'2025-02-09 10:29:00.000000',NULL);

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
(2,'ROLE_ADMIN'),
(5,'ROLE_ADMIN'),
(8,'ROLE_ADMIN'),
(11,'ROLE_ADMIN'),
(14,'ROLE_ADMIN'),
(17,'ROLE_ADMIN'),
(20,'ROLE_ADMIN'),
(23,'ROLE_ADMIN'),
(26,'ROLE_ADMIN'),
(29,'ROLE_ADMIN'),
(32,'ROLE_ADMIN'),
(35,'ROLE_ADMIN'),
(38,'ROLE_ADMIN'),
(41,'ROLE_ADMIN'),
(44,'ROLE_ADMIN'),
(47,'ROLE_ADMIN'),
(50,'ROLE_ADMIN'),
(1,'ROLE_DEV'),
(3,'ROLE_DEV'),
(6,'ROLE_DEV'),
(9,'ROLE_DEV'),
(12,'ROLE_DEV'),
(15,'ROLE_DEV'),
(18,'ROLE_DEV'),
(21,'ROLE_DEV'),
(24,'ROLE_DEV'),
(27,'ROLE_DEV'),
(30,'ROLE_DEV'),
(33,'ROLE_DEV'),
(36,'ROLE_DEV'),
(39,'ROLE_DEV'),
(42,'ROLE_DEV'),
(45,'ROLE_DEV'),
(48,'ROLE_DEV'),
(51,'ROLE_DEV'),
(1,'ROLE_SELLER'),
(4,'ROLE_SELLER'),
(7,'ROLE_SELLER'),
(10,'ROLE_SELLER'),
(13,'ROLE_SELLER'),
(16,'ROLE_SELLER'),
(19,'ROLE_SELLER'),
(22,'ROLE_SELLER'),
(25,'ROLE_SELLER'),
(28,'ROLE_SELLER'),
(31,'ROLE_SELLER'),
(34,'ROLE_SELLER'),
(37,'ROLE_SELLER'),
(40,'ROLE_SELLER'),
(43,'ROLE_SELLER'),
(46,'ROLE_SELLER'),
(49,'ROLE_SELLER'),
(1,'ROLE_USER'),
(2,'ROLE_USER'),
(3,'ROLE_USER'),
(4,'ROLE_USER'),
(5,'ROLE_USER'),
(6,'ROLE_USER'),
(7,'ROLE_USER'),
(8,'ROLE_USER'),
(9,'ROLE_USER'),
(10,'ROLE_USER'),
(11,'ROLE_USER'),
(12,'ROLE_USER'),
(13,'ROLE_USER'),
(14,'ROLE_USER'),
(15,'ROLE_USER'),
(16,'ROLE_USER'),
(17,'ROLE_USER'),
(18,'ROLE_USER'),
(19,'ROLE_USER'),
(20,'ROLE_USER'),
(21,'ROLE_USER'),
(22,'ROLE_USER'),
(23,'ROLE_USER'),
(24,'ROLE_USER'),
(25,'ROLE_USER'),
(26,'ROLE_USER'),
(27,'ROLE_USER'),
(28,'ROLE_USER'),
(29,'ROLE_USER'),
(30,'ROLE_USER'),
(31,'ROLE_USER'),
(32,'ROLE_USER'),
(33,'ROLE_USER'),
(34,'ROLE_USER'),
(35,'ROLE_USER'),
(36,'ROLE_USER'),
(37,'ROLE_USER'),
(38,'ROLE_USER'),
(39,'ROLE_USER'),
(40,'ROLE_USER'),
(41,'ROLE_USER'),
(42,'ROLE_USER'),
(43,'ROLE_USER'),
(44,'ROLE_USER'),
(45,'ROLE_USER'),
(46,'ROLE_USER'),
(47,'ROLE_USER'),
(48,'ROLE_USER'),
(49,'ROLE_USER'),
(50,'ROLE_USER'),
(51,'ROLE_USER'),
(52,'ROLE_USER');

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
(1,NULL,'123 Demo Street, Demo City',NULL,'2000-01-01','demo@example.com',NULL,'Demo User','MALE',NULL,NULL,NULL,'$2a$10$9dMDtZMdtFzRrEIhXFydZe1yA1D/LemHdXj6fcxtyLpE0r.Lkwo2e','1234567890',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'demoUser',NULL,NULL,NULL,152,153),
(2,NULL,'Tikatoli, Dhaka, Bangladesh',NULL,'1998-11-06','mahid.tonmoy@example.com',NULL,'Mahid Ahmed Tonmoy','MALE',NULL,NULL,NULL,'$2a$10$/sUSzbGKQaTY8h35MnOEyeoC.XHbZJoG21o/LLTChnQvlqqFqMkWG','01914274834',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'mahidtonmoy',NULL,NULL,NULL,553,552),
(3,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1989-08-23','arif.khan@example.com',NULL,'Arif Khan','MALE',NULL,NULL,NULL,'$2a$10$5O5KPZwNPgy3Zwo04KXDYu.ot58gYMh3nUOzJ/5d/uJcMOU4ETuw2','01912345678',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'arifkhan',NULL,NULL,NULL,NULL,NULL),
(4,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1988-07-16','shakib.alhasan@example.com',NULL,'Shakib Al Hasan','MALE',NULL,NULL,NULL,'$2a$10$vaIoENSyMpqOIL1gu9Y5y.XVmryb1dJIhHnZjlhwFyHvfShznLwN6','01914567890',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'shakibalhasan',NULL,NULL,NULL,603,602),
(5,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1992-04-12','aisha.rahman@example.com',NULL,'Aisha Rahman','FEMALE',NULL,NULL,NULL,'$2a$10$KxwV6QesoJRpFWExdR5A4.UhWHEgJ.ogFCnUGb3m9muoFncKz1w3G','01911234567',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'aisharahman',NULL,NULL,NULL,605,604),
(6,NULL,'Banani, Dhaka, Bangladesh',NULL,'1993-05-17','laila.islam@example.com',NULL,'Laila Islam','FEMALE',NULL,NULL,NULL,'$2a$10$IMuWVJsOz8wDndLWlfdxFOhYxFVFIK2d4reoFJO0T12ig/F9rVrg2','01913456789',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'laila.islam',NULL,NULL,NULL,NULL,NULL),
(7,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1995-02-28','nusrat.jahan@example.com',NULL,'Nusrat Jahan','FEMALE',NULL,NULL,NULL,'$2a$10$QVSen/AzR2BnU4SuWg1ePObDPtUCV2KNnrB6YE1vzwp8IesLC0FWW','01915678901',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'nusratjahan',NULL,NULL,NULL,NULL,NULL),
(8,NULL,'Bashundhara, Dhaka, Bangladesh',NULL,'1994-09-03','rima.sultana@example.com',NULL,'Rima Sultana','FEMALE',NULL,NULL,NULL,'$2a$10$ySoVDRddh/ySTPUgOmwqD.scT0oExfxBNJlNiYwm4t52xw8knyGka','01917890123',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rimasultana',NULL,NULL,NULL,NULL,NULL),
(9,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1990-11-11','saifur.rahman@example.com',NULL,'Saifur Rahman','MALE',NULL,NULL,NULL,'$2a$10$BgjONHCIOS1FlYVZ74LGE.IsNxyXI/BP8C8uBLZylq0t.LVNF3dYS','01916789012',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'saifurrahman',NULL,NULL,NULL,NULL,NULL),
(10,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1991-03-21','tanvir.hossain@example.com',NULL,'Tanvir Hossain','MALE',NULL,NULL,NULL,'$2a$10$biBKlEo7FVRLK9N/SNvElu2JQg3IErI./Us5WUrR5fSr57nxsSVEO','01918901234',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'tanvirhossain',NULL,NULL,NULL,NULL,NULL),
(11,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1993-10-10','ritu.parveen@example.com',NULL,'Ritu Parveen','FEMALE',NULL,NULL,NULL,'$2a$10$HYPxJ1G8FzHn0.7bgCU5SuUP7KI1ZG5WtpVY71F9pB9XoEJSgNcIm','01910234567',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'ritaparveen',NULL,NULL,NULL,NULL,NULL),
(12,NULL,'Banani, Dhaka, Bangladesh',NULL,'1987-06-15','imran.ali@example.com',NULL,'Imran Ali','MALE',NULL,NULL,NULL,'$2a$10$tI54l9REZT9dKgKT1an...KUB/IvHJzDIX0r1mpjc.40cUEx7fcHO','01910123456',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'imranali',NULL,NULL,NULL,NULL,652),
(13,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1996-12-05','farhana.akter@example.com',NULL,'Farhana Akter','FEMALE',NULL,NULL,NULL,'$2a$10$SLUWov1FqwlzxH696GA6K.Xw7sBlOD8nztAenNNAgWEP0N1FO8b.y','01919012345',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'farhanaakter',NULL,NULL,NULL,NULL,NULL),
(14,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1985-01-20','kamal.uddin@example.com',NULL,'Kamal Uddin','MALE',NULL,NULL,NULL,'$2a$10$lswW1PRAAPvDbPREdo8po.RLpQcGJvabz3HQwDtMyxlJtzqiKHRQi','01910345678',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'kamaluddin',NULL,NULL,NULL,NULL,NULL),
(15,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1992-08-08','selina.akter@example.com',NULL,'Selina Akter','FEMALE',NULL,NULL,NULL,'$2a$10$FUtnn4sDhEcathmH2XAKZODkM1Ggb/4Vnv.DCttTzbelCzDs6Hgju','01910456789',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'selinaakter',NULL,NULL,NULL,NULL,NULL),
(16,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1990-02-02','omar.faruq@example.com',NULL,'Omar Faruq','MALE',NULL,NULL,NULL,'$2a$10$xtlcpessJwMM1EGGXRtImOmcprwdKYZJq24d9GgnUCGUMCakHqmI6','01910567890',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'omarfaruq',NULL,NULL,NULL,NULL,NULL),
(17,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1994-04-25','fatima.begum@example.com',NULL,'Fatima Begum','FEMALE',NULL,NULL,NULL,'$2a$10$HVrKDIGwlIC.nwD4ng35lewiivMTHKIUMad3krTPtSUxGJJKAMfGC','01910678901',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'fatimabegum',NULL,NULL,NULL,NULL,NULL),
(18,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1990-03-03','arif.chowdhury@example.com',NULL,'Arifuzzaman Chowdhury','MALE',NULL,NULL,NULL,'$2a$10$QQgcVnZz/g.6cOS7RFe9beFjsuqFE1zHZlHcksJndZQqJFl68UNhW','01910901234',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'arifchowdhury',NULL,NULL,NULL,NULL,NULL),
(19,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1995-11-11','nadia.islam@example.com',NULL,'Nadia Islam','FEMALE',NULL,NULL,NULL,'$2a$10$4iX.dNDvNeWR2bwPJ/xpAOrEGX.BaSG3h.GapvdUFPsSYpphdpmqi','01911012345',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'nadiaislam',NULL,NULL,NULL,NULL,NULL),
(20,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1986-05-05','jamil.uddin@example.com',NULL,'Jamil Uddin','MALE',NULL,NULL,NULL,'$2a$10$gnftxjPHtry9TkCDqAP.d.bHYiHkY4ni3jUDuljii9YkopaMXzGB2','01911123456',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'jamiluddin',NULL,NULL,NULL,NULL,NULL),
(21,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1991-07-19','parveen.sultana@example.com',NULL,'Parveen Sultana','FEMALE',NULL,NULL,NULL,'$2a$10$mpFxs12WkDQFegEiTB1GAeb5NSxKtXGCdffSNp0.QfiWK2cbuc/Wm','01910890123',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'parveensultana',NULL,NULL,NULL,NULL,NULL),
(22,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1993-09-09','shahana.akter@example.com',NULL,'Shahana Akter','FEMALE',NULL,NULL,NULL,'$2a$10$C9K5WY7Htk5wPaM0PILiDusS8U2Jwztgf4stJIGJ49a8FC07BEDFy','01911234567',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'shahanaakter',NULL,NULL,NULL,NULL,NULL),
(23,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1992-02-20','rokeya.begum@example.com',NULL,'Rokeya Begum','FEMALE',NULL,NULL,NULL,'$2a$10$VDpc5y6v3TGp8.S2xxe/aOUTJYTrDSSKZBAUFujHG9UlTtUfe50hm','01911456789',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rokeyabegum',NULL,NULL,NULL,NULL,NULL),
(24,NULL,'Banani, Dhaka, Bangladesh',NULL,'1988-12-30','rezaul.karim@example.com',NULL,'Rezaul Karim','MALE',NULL,NULL,NULL,'$2a$10$A06/ldxE8hlwj63MtT8dM.mf0RYjhW23kHcWqlvsUNFTyVpJgKHJ.','01910789012',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rezaulkarim',NULL,NULL,NULL,NULL,NULL),
(25,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1994-03-15','priya.sen@example.com',NULL,'Priya Sen','FEMALE',NULL,NULL,NULL,'$2a$10$66hC6haeCjWbdpXsdwcSqeKSSZe719APu2preiX7VPsPf58zFcbB2','01911678901',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'priyasen',NULL,NULL,NULL,NULL,NULL),
(26,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1987-08-08','asif.ali@example.com',NULL,'Asif Ali','MALE',NULL,NULL,NULL,'$2a$10$kXWVUxhDuzf1kwNWL7Jz/O46BFMAK6A6L/vKnYpxc.KPiYlIzVHg2','01911789012',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'asifali',NULL,NULL,NULL,NULL,NULL),
(27,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1991-12-12','rupa.sarkar@example.com',NULL,'Rupa Sarkar','FEMALE',NULL,NULL,NULL,'$2a$10$UEncV9WN0iZE9rv60Uek4uPntWMSmRmW0NgLQz/xhEvb3bf3eynvO','01911890123',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rupasarkar',NULL,NULL,NULL,NULL,NULL),
(28,NULL,'Banani, Dhaka, Bangladesh',NULL,'1990-09-09','zahid.hasan@example.com',NULL,'Zahid Hasan','MALE',NULL,NULL,NULL,'$2a$10$ugpksvQNX/Q6FoJqlv3aauJKWoag1Kgf.jsdGVlRLNfDE4sD3BgvG','01911901234',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'zahidhasan',NULL,NULL,NULL,NULL,NULL),
(29,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1993-11-11','malika.parvin@example.com',NULL,'Malika Parvin','FEMALE',NULL,NULL,NULL,'$2a$10$iwx8RW41KuwUKSr0zBnpJe80UMxbY2cZHj7pzNdXp/E7UPL3giL8u','01912012345',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'malikaparvin',NULL,NULL,NULL,NULL,NULL),
(30,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1988-04-04','sohel.rana@example.com',NULL,'Sohel Rana','MALE',NULL,NULL,NULL,'$2a$10$OVomQfrZw9T/hQJL01IqTevBDPE1JfLcKCY7qQBicvCCjnR2kqfYS','01912123456',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'sohelrana',NULL,NULL,NULL,NULL,NULL),
(31,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1992-07-07','shirin.akter@example.com',NULL,'Shirin Akter','FEMALE',NULL,NULL,NULL,'$2a$10$Srzv.Z0mggHHh.GVlbRo3.Fj.pPVCCijeTPHyH9E1CQztPbQZtx3G','01912234567',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'shirinakter',NULL,NULL,NULL,NULL,NULL),
(32,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1989-11-11','adnan.ahmed@example.com',NULL,'Adnan Ahmed','MALE',NULL,NULL,NULL,'$2a$10$ytTR.o0/yM5yg0eqBLMF6.BYS9napARAaj3tV6ftsHww7MyhsNQnq','01912345670',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'adnanaahmed',NULL,NULL,NULL,NULL,NULL),
(33,NULL,'Banani, Dhaka, Bangladesh',NULL,'1990-10-10','anik.rahman@example.com',NULL,'Anik Rahman','MALE',NULL,NULL,NULL,'$2a$10$ysI8u4KHtVOHxCWJOnOEM.CMr0OXcvUz5rWuANlY/fgHbt1H/7Klm','01911345678',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'anikrahman',NULL,NULL,NULL,NULL,NULL),
(34,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1994-05-05','moushumi.akhtar@example.com',NULL,'Moushumi Akhtar','FEMALE',NULL,NULL,NULL,'$2a$10$sWx/M9xlYmIvz8Jb5v619uK0u/LzXaQ0UdCP1sEGXb05MfhGN.r5O','01912456781',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'moushumiakhtar',NULL,NULL,NULL,NULL,NULL),
(35,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1993-03-03','sohana.sultana@example.com',NULL,'Sohana Sultana','FEMALE',NULL,NULL,NULL,'$2a$10$zm0nH/.YLkYd2zuYShgxfuYrapMxFD4eSE6VMRXA5xidWrtKycfqW','01912678903',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'sohanasultana',NULL,NULL,NULL,NULL,NULL),
(36,NULL,'Banani, Dhaka, Bangladesh',NULL,'1991-06-06','mahmudul.hasan@example.com',NULL,'Mahmudul Hasan','MALE',NULL,NULL,NULL,'$2a$10$YcfQxBLFZC86/yUa9M93W.Asw9dADEh.wzSvR/xn0meEQNMvdUCAO','01912567892',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'mahmudulhasan',NULL,NULL,NULL,NULL,NULL),
(37,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1987-12-12','imtiaz.rahman@example.com',NULL,'Imtiaz Rahman','MALE',NULL,NULL,NULL,'$2a$10$.I8RGZ4mO382FYn6SShcy.f6tUA.AfkE9M5CM2UfwaIbeaGrHlE9q','01912789014',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'imtiazrahman',NULL,NULL,NULL,NULL,NULL),
(38,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1989-06-06','kamrul.hassan@example.com',NULL,'Kamrul Hassan','MALE',NULL,NULL,NULL,'$2a$10$LlAFL8ku3aF40UqE69P3UOzzWlAkClDIZVltYf0Szfv7QHMKcKVMi','01911567890',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'kamrulhassan',NULL,NULL,NULL,NULL,NULL),
(39,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1990-02-02','nusrat.begum@example.com',NULL,'Nusrat Begum','FEMALE',NULL,NULL,NULL,'$2a$10$GKzyxtQVcnbzDE0e0vMFOO0awjW.XSmS6mX1egdv.lpKyst1.h6MS','01912890125',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'nusratbegum',NULL,NULL,NULL,NULL,NULL),
(40,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1992-10-10','sultana.begum@example.com',NULL,'Sultana Begum','FEMALE',NULL,NULL,NULL,'$2a$10$BXuoWthh1L2Q1IF6rafRauAwkc/4KxA50j3z86kmZ0K3IwHcJe9J6','01913012347',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'sultanabegum',NULL,NULL,NULL,NULL,NULL),
(41,NULL,'Banani, Dhaka, Bangladesh',NULL,'1989-09-09','nazmul.islam@example.com',NULL,'Nazmul Islam','MALE',NULL,NULL,NULL,'$2a$10$3oazW1mfHXYGDMqdFUz/f.nV4HH5Gsunx1oVvLWRyYnlM/ztLL0IG','01913123458',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'nazmulislam',NULL,NULL,NULL,NULL,NULL),
(42,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1995-01-01','jennifer.lee@example.com',NULL,'Jennifer Lee','FEMALE',NULL,NULL,NULL,'$2a$10$MBsIsu3dhwLHJspEfiohCOXFPFqpG/NpzB4klMJPdnB04LwylsqZm','01913234569',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'jenniferlee',NULL,NULL,NULL,NULL,NULL),
(43,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1990-08-08','rajib.das@example.com',NULL,'Rajib Das','MALE',NULL,NULL,NULL,'$2a$10$EQkNQGIIu.3rqP/BdKn9c.2lZiECX0R8SOqiDu.M1b6xALNcDrwTm','01913345670',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rajibdas',NULL,NULL,NULL,NULL,NULL),
(44,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1993-11-11','sumi.chowdhury@example.com',NULL,'Sumi Chowdhury','FEMALE',NULL,NULL,NULL,'$2a$10$0Z0.0St5Mx0.glxogdxtGOpvf/qEV97a4OJ6TMKfqGwodF/ElmMUe','01913456781',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'sumichowdhury',NULL,NULL,NULL,NULL,NULL),
(45,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1988-06-06','aslam.hossain@example.com',NULL,'Aslam Hossain','MALE',NULL,NULL,NULL,'$2a$10$jp24XkfQ2JHtqQ.p6//.z.1zD2k/wOFKWKueY/itcyUNhzImUeWm.','01913567892',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'aslamhossain',NULL,NULL,NULL,NULL,NULL),
(46,NULL,'Gulshan, Dhaka, Bangladesh',NULL,'1992-04-04','anika.begum@example.com',NULL,'Anika Begum','FEMALE',NULL,NULL,NULL,'$2a$10$y6mzDvKrwkn2TYmAOpkrmOUv7XYTavbaxtNMjse/SmG86cr3XFT16','01913678903',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'anikabegum',NULL,NULL,NULL,NULL,NULL),
(47,NULL,'Banani, Dhaka, Bangladesh',NULL,'1991-03-03','ehsan.hoque@example.com',NULL,'Ehsanul Hoque','MALE',NULL,NULL,NULL,'$2a$10$aOpftzj8D5TnMALgeVkvRuKnQW3dPpjfWh4Dtfcc8a0.rHgS6yeuW','01913789014',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'ehsanulhoque',NULL,NULL,NULL,NULL,NULL),
(48,NULL,'Uttara, Dhaka, Bangladesh',NULL,'1988-03-03','azad.hossain@example.com',NULL,'Azad Hossain','MALE',NULL,NULL,NULL,'$2a$10$hAEfLA93rhFdlweKz9CGvuyDFVsm8QYzH0bPlfN9QUbMufYhFRgbm','01912901236',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'azadhossain',NULL,NULL,NULL,NULL,NULL),
(49,NULL,'Mirpur, Dhaka, Bangladesh',NULL,'1987-10-10','ziaur.rahman@example.com',NULL,'Ziaur Rahman','MALE',NULL,NULL,NULL,'$2a$10$CxgH7pNcoc.9z03d44BAbuLNB9cU3KyuBRlg6S.yBvZWyiM3QRB5G','01913901236',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'ziaurrahman',NULL,NULL,NULL,NULL,NULL),
(50,NULL,'Dhanmondi, Dhaka, Bangladesh',NULL,'1993-07-07','farzana.begum@example.com',NULL,'Farzana Begum','FEMALE',NULL,NULL,NULL,'$2a$10$qxC7igLeAum3bd2RFNyS5eVAxSSkAvxBJVuSPEs6QMo86r9DUgg9q','01913890125',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'farzanabegum',NULL,NULL,NULL,NULL,NULL),
(51,NULL,'Mohammadpur, Dhaka, Bangladesh',NULL,'1995-05-05','rumana.akter@example.com',NULL,'Rumana Akter','FEMALE',NULL,NULL,NULL,'$2a$10$wnB/13ztbeZmvjsWzEIaS.BwmXcL78g0Soxny0LILOcI5vbXej1Nq','01914012347',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'rumanaakter',NULL,NULL,NULL,NULL,NULL),
(52,NULL,'Keraniganj Dhaka',NULL,'2000-12-27','gidni441@gmail.com',NULL,'Jidni Khan','MALE',NULL,NULL,NULL,'$2a$10$AErCLfN077Z/bggJXKidcev0L4EonxuI3mGd05Ow.RLolgO3Tp4Ay','01719987447',NULL,NULL,NULL,'INACTIVE',NULL,NULL,NULL,'jidni',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `users_seq` */

DROP TABLE IF EXISTS `users_seq`;

CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users_seq` */

insert  into `users_seq`(`next_val`) values 
(151);

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
