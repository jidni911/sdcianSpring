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

/*Table structure for table `cart_item_seq` */

DROP TABLE IF EXISTS `cart_item_seq`;

CREATE TABLE `cart_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_item_seq` */

insert  into `cart_item_seq`(`next_val`) values 
(1);

/*Table structure for table `cart_items` */

DROP TABLE IF EXISTS `cart_items`;

CREATE TABLE `cart_items` (
  `cart_id` bigint NOT NULL,
  `items_id` bigint NOT NULL,
  UNIQUE KEY `UK383kkp3af9dpn91t406oqe9n1` (`items_id`),
  KEY `FK99e0am9jpriwxcm6is7xfedy3` (`cart_id`),
  CONSTRAINT `FK99e0am9jpriwxcm6is7xfedy3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKnqjva2t0na43f4qxm3xprl2qu` FOREIGN KEY (`items_id`) REFERENCES `cart_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_items` */

/*Table structure for table `cart_seq` */

DROP TABLE IF EXISTS `cart_seq`;

CREATE TABLE `cart_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cart_seq` */

insert  into `cart_seq`(`next_val`) values 
(1);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint NOT NULL,
  `comment_text` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `comment` */

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
(1);

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
  PRIMARY KEY (`id`),
  KEY `FKahqf3d9mhepq2g6n37onde4cv` (`organiser_id`),
  CONSTRAINT `FKahqf3d9mhepq2g6n37onde4cv` FOREIGN KEY (`organiser_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event` */

/*Table structure for table `event_seq` */

DROP TABLE IF EXISTS `event_seq`;

CREATE TABLE `event_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `event_seq` */

insert  into `event_seq`(`next_val`) values 
(1);

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

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe2l07hc93u2bbjnl80meu3rn4` (`post_id`),
  KEY `FKcvpnctgluno47ac6avana5sqf` (`user_id`),
  CONSTRAINT `FKcvpnctgluno47ac6avana5sqf` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKe2l07hc93u2bbjnl80meu3rn4` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image` */

/*Table structure for table `image_seq` */

DROP TABLE IF EXISTS `image_seq`;

CREATE TABLE `image_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `image_seq` */

insert  into `image_seq`(`next_val`) values 
(1);

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
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqd4o375qvtt45ierlsgsouhlc` (`product_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item` */

/*Table structure for table `order_item_seq` */

DROP TABLE IF EXISTS `order_item_seq`;

CREATE TABLE `order_item_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item_seq` */

insert  into `order_item_seq`(`next_val`) values 
(1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_date` varchar(255) DEFAULT NULL,
  `delivery_instructions` varchar(255) DEFAULT NULL,
  `delivery_method` tinyint DEFAULT NULL,
  `delivery_time` varchar(255) DEFAULT NULL,
  `is_payment_completed` bit(1) NOT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `payment_method` tinyint DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','OUT_FOR_DELIVERY','OUT_FOR_PICKUP','PENDING','PENDING_PAYMENT','PROCESSING','REFUNDED','REJECTED','RETURNED') DEFAULT NULL,
  `total_amount` double NOT NULL,
  `seller_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsb9w6305d2be0rwbtifi7wymp` (`seller_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsb9w6305d2be0rwbtifi7wymp` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`),
  CONSTRAINT `orders_chk_1` CHECK ((`delivery_method` between 0 and 1)),
  CONSTRAINT `orders_chk_2` CHECK ((`payment_method` between 0 and 4))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

/*Table structure for table `orders_order_items` */

DROP TABLE IF EXISTS `orders_order_items`;

CREATE TABLE `orders_order_items` (
  `order_id` bigint NOT NULL,
  `order_items_id` bigint NOT NULL,
  UNIQUE KEY `UK9d47gapmi35omtannusv6btu3` (`order_items_id`),
  KEY `FK3l8rktw0f4w5t6tift31e2d7c` (`order_id`),
  CONSTRAINT `FK3l8rktw0f4w5t6tift31e2d7c` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK7nw03p9mxq154wvbsonaq0qrw` FOREIGN KEY (`order_items_id`) REFERENCES `order_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders_order_items` */

/*Table structure for table `orders_seq` */

DROP TABLE IF EXISTS `orders_seq`;

CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders_seq` */

insert  into `orders_seq`(`next_val`) values 
(1);

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
  `post_text` varchar(255) DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  `shared_post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4b5ha5bhihxywskppdc06dyuo` (`creator_id`),
  KEY `FKnlb9ck8vt0xwqb561hssno6l3` (`shared_post_id`),
  CONSTRAINT `FK4b5ha5bhihxywskppdc06dyuo` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKnlb9ck8vt0xwqb561hssno6l3` FOREIGN KEY (`shared_post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post` */

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

/*Table structure for table `post_seq` */

DROP TABLE IF EXISTS `post_seq`;

CREATE TABLE `post_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `post_seq` */

insert  into `post_seq`(`next_val`) values 
(1);

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
  `added_date` varchar(255) DEFAULT NULL,
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
  `ratings` double NOT NULL,
  `reviews` int NOT NULL,
  `stock_status` varchar(255) DEFAULT NULL,
  `tags` varbinary(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `main_image_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKyw5egv10d825aje1aj3tnh9y` (`main_image_id`),
  KEY `FKnuvtfgcf3ohskgoyi6v1eh1jr` (`seller_id`),
  CONSTRAINT `FKia727jx9hh6kxtao8hrjuwfj3` FOREIGN KEY (`main_image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKnuvtfgcf3ohskgoyi6v1eh1jr` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

/*Table structure for table `product_gallery_images` */

DROP TABLE IF EXISTS `product_gallery_images`;

CREATE TABLE `product_gallery_images` (
  `product_id` bigint NOT NULL,
  `gallery_images_id` bigint NOT NULL,
  UNIQUE KEY `UKskvtcgoj9rvxn6nhm8vltf6lt` (`gallery_images_id`),
  KEY `FK7mu9ltwaca8g53jhy065rvbsr` (`product_id`),
  CONSTRAINT `FK7mu9ltwaca8g53jhy065rvbsr` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKgdkxcv56uojha4c0w9ptve8gh` FOREIGN KEY (`gallery_images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_gallery_images` */

/*Table structure for table `product_seq` */

DROP TABLE IF EXISTS `product_seq`;

CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product_seq` */

insert  into `product_seq`(`next_val`) values 
(1);

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
  `cover_picture` varchar(255) DEFAULT NULL,
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
  `profile_picture` varchar(255) DEFAULT NULL,
  `reddit` varchar(255) DEFAULT NULL,
  `snapchat` varchar(255) DEFAULT NULL,
  `telegram` varchar(255) DEFAULT NULL,
  `tiktok` varchar(255) DEFAULT NULL,
  `twitter` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `whatsapp` varchar(255) DEFAULT NULL,
  `youtube` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`about`,`address`,`cover_picture`,`discord`,`dob`,`email`,`facebook`,`full_name`,`gender`,`github`,`instagram`,`linkedin`,`password`,`phone_number`,`pinterest`,`profile_picture`,`reddit`,`snapchat`,`telegram`,`tiktok`,`twitter`,`username`,`website`,`whatsapp`,`youtube`) values 
(1,NULL,'123 Demo Street, Demo City',NULL,NULL,'2000-01-01','demo@example.com',NULL,'Demo User','MALE',NULL,NULL,NULL,'$2a$10$.1EpVuS6qFog05DV2wJaPep6msJ.1By3PCpotLVVOpMsl.2J1/qTG','1234567890',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'demoUser',NULL,NULL,NULL);

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
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1rcfqyngqcam9rcq4bx21sbl` (`post_id`),
  KEY `FK2k5fkq9vwjoxc3ne82hh280t0` (`user_id`),
  CONSTRAINT `FK2k5fkq9vwjoxc3ne82hh280t0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKm1rcfqyngqcam9rcq4bx21sbl` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `video` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
