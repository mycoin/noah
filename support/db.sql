CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `TABLE_MONITOR` (
  `ID` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `TYPE` int(2) DEFAULT NULL COMMENT '日志类型',
  `SITEID` int(11) NOT NULL COMMENT '网站主唯一标示',
  `CLIENTID` varchar(64) NOT NULL DEFAULT '' COMMENT '跟踪用户唯一标记',
  `LOADINGTIME` varchar(16) NOT NULL DEFAULT '' COMMENT '进入页面的时间',
  `URL` varchar(64) NOT NULL COMMENT '当前页面的URL',
  `TITLE` varchar(64) DEFAULT NULL COMMENT '页面标题',
  `VERSION` varchar(16) NOT NULL COMMENT '版本号码',
  `BROWSERNAME` varchar(32) DEFAULT NULL COMMENT '浏览器类型',
  `COLORDEPTH` varchar(16) DEFAULT NULL COMMENT '颜色位',
  `SCREEN` varchar(16) DEFAULT NULL COMMENT '屏幕尺寸相关信息',
  `CHARSET` varchar(16) DEFAULT NULL COMMENT '编码类型',
  `OS` varchar(32) DEFAULT NULL COMMENT '操作系统类型',
  `LANGUAGE` varchar(32) DEFAULT NULL COMMENT '操作系统语言',
  `TIMEZONE` int(4) DEFAULT NULL COMMENT '时区',
  `DOMAIN` varchar(256) DEFAULT NULL COMMENT '域名名称',
  `JAVAENABLED` int(1) DEFAULT NULL COMMENT 'Java 是否安装',
  `COOKIEENABLED` int(1) DEFAULT NULL COMMENT 'Cookie 是否安装',
  `SEARCHENGINE` varchar(64) DEFAULT NULL COMMENT '搜索引擎类型',
  `SEARCHWORD` varchar(512) DEFAULT NULL COMMENT '搜索关键词',
  `REFER` varchar(512) DEFAULT NULL COMMENT '流量来源',
  `READYTIME` varchar(16) DEFAULT NULL COMMENT '所有资源ready的时间戳',
  `XPATH` varchar(1024) DEFAULT NULL COMMENT '节点路径',
  `LINKURL` varchar(512) DEFAULT NULL COMMENT '超链接地址、图片地址',
  `TARGET` varchar(32) DEFAULT NULL COMMENT '节点特征',
  `TEXT` varchar(128) DEFAULT NULL COMMENT '文本信息',
  `SCREENPOSITION` varchar(16) DEFAULT NULL COMMENT '坐标位置',
  `EVENTTIME` varchar(16) DEFAULT NULL COMMENT '事件发生的时间',
  `IS_DELETE` tinyint(1) DEFAULT '0',
  `GMT_CREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `GMT_MODIFY` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TABLE_USER` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_NAME` varchar(32) DEFAULT NULL COMMENT '登录账户',
  `PASSWORD` varchar(512) DEFAULT NULL COMMENT '密码',
  `STATUS` varchar(32) DEFAULT NULL COMMENT '账户状态',
  `TAGS` text COMMENT '标签列表',
  `GMT_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`),
  KEY `TABLE_USER_IDIDX` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
