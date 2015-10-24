-- phpMyAdmin SQL Dump
-- version 4.1.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-10-24 15:01:59
-- 服务器版本： 5.6.26
-- PHP Version: 5.5.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `log`
--

-- --------------------------------------------------------

--
-- 表的结构 `common_log`
--

CREATE TABLE IF NOT EXISTS `common_log` (
  `type` int(2) DEFAULT NULL,
  `os` varchar(16) DEFAULT NULL,
  `app` varchar(16) DEFAULT NULL,
  `cl` varchar(16) DEFAULT NULL,
  `charset` varchar(256) DEFAULT NULL,
  `scr` varchar(256) DEFAULT NULL,
  `lang` varchar(256) DEFAULT NULL,
  `tz` varchar(256) DEFAULT NULL,
  `domain` varchar(256) DEFAULT NULL,
  `je` varchar(256) DEFAULT NULL,
  `ce` varchar(256) DEFAULT NULL,
  `se` varchar(256) DEFAULT NULL,
  `sword` varchar(256) DEFAULT NULL,
  `refer` varchar(32) DEFAULT NULL,
  `dt` varchar(32) DEFAULT NULL,
  `rt` varchar(32) DEFAULT NULL,
  `lt` varchar(32) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `sid` varchar(32) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  `u` varchar(32) DEFAULT NULL,
  `v` varchar(32) DEFAULT NULL,
  `xpath` varchar(512) DEFAULT NULL,
  `url` varchar(512) DEFAULT NULL,
  `fm` varchar(16) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `pos` varchar(16) DEFAULT NULL,
  `et` varchar(16) DEFAULT NULL,
  `F` varchar(32) DEFAULT NULL,
  `F1` varchar(32) DEFAULT NULL,
  `F2` varchar(32) DEFAULT NULL,
  `F3` varchar(32) DEFAULT NULL,
  `rsv_map` varchar(512) DEFAULT NULL,
  `g` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
