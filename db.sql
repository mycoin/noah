SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `TEMPLATE` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `guid` varchar(36) NOT NULL,
  `name` varchar(512) DEFAULT NULL,
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
