-- phpMyAdmin SQL Dump
-- version 4.1.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-01-20 01:54:21
-- 服务器版本： 5.6.27
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `logger`
--

-- --------------------------------------------------------

--
-- 表的结构 `org`
--

CREATE TABLE IF NOT EXISTS `org` (
  `type` tinyint(2) NOT NULL COMMENT '日志类型',
  `os` varchar(16) DEFAULT NULL COMMENT '操作系统',
  `app` varchar(16) DEFAULT NULL COMMENT '浏览器类型',
  `cl` varchar(16) DEFAULT NULL COMMENT '颜色位',
  `charset` varchar(16) DEFAULT NULL COMMENT '编码类型',
  `scr` varchar(256) DEFAULT NULL COMMENT '屏幕尺寸',
  `lang` varchar(256) DEFAULT NULL COMMENT '浏览器语言',
  `tz` tinyint(2) DEFAULT NULL COMMENT '时区',
  `domain` varchar(64) DEFAULT NULL COMMENT '域名名称',
  `je` tinyint(1) DEFAULT NULL COMMENT 'Java',
  `ce` tinyint(1) DEFAULT NULL COMMENT 'cookie',
  `se` varchar(16) DEFAULT NULL COMMENT '搜索引擎',
  `sword` varchar(64) DEFAULT NULL COMMENT '关键词',
  `refer` varchar(256) DEFAULT NULL COMMENT '流量来源',
  `dt` timestamp NULL DEFAULT NULL COMMENT 'DOMready',
  `lt` timestamp NULL DEFAULT NULL COMMENT '进入页面的时间',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `sid` varchar(32) NOT NULL COMMENT '网站主唯一标示',
  `cid` varchar(32) NOT NULL COMMENT '用户唯一标记',
  `u` varchar(512) DEFAULT NULL COMMENT '统计维度',
  `v` varchar(16) NOT NULL COMMENT '版本号码',
  `xpath` varchar(1024) DEFAULT NULL COMMENT 'XPath',
  `url` varchar(512) DEFAULT NULL COMMENT '当前地址',
  `fm` enum('ppim','beha','alop','sk') DEFAULT NULL COMMENT '日志特征码',
  `text` varchar(64) DEFAULT NULL COMMENT '文本信息',
  `pos` varchar(16) DEFAULT NULL COMMENT '坐标位置',
  `et` timestamp NULL DEFAULT NULL COMMENT '事件时间',
  `F` varchar(32) DEFAULT NULL COMMENT '预留字段（广告）',
  `F1` varchar(32) DEFAULT NULL COMMENT '预留字段',
  `F2` varchar(32) DEFAULT NULL COMMENT '预留字段',
  `F3` varchar(32) DEFAULT NULL COMMENT '预留字段',
  `rsv_map` tinytext COMMENT '用户参数',
  `g` varchar(16) DEFAULT NULL COMMENT '节点特征'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
