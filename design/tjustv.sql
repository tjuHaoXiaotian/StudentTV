-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2016 �?03 �?29 �?05:52
-- 服务器版本: 5.6.11
-- PHP 版本: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `tjustv`
--
CREATE DATABASE IF NOT EXISTS `tjustv` DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;
USE `tjustv`;

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`adminId`, `adminName`, `password`, `createtime`, `updatetime`) VALUES
(1, 'shen', '123', '2016-03-29 03:49:16', '2016-03-29 03:49:18'),
(2, 'ShenYueLong', '123', '2016-03-29 03:49:44', '2016-03-29 03:49:44');

-- --------------------------------------------------------

--
-- 表的结构 `collection`
--

CREATE TABLE IF NOT EXISTS `collection` (
  `collectionId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  PRIMARY KEY (`collectionId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ref_video_Id` int(11) NOT NULL,
  `ref_user_Id` int(11) NOT NULL,
  PRIMARY KEY (`commentId`),
  UNIQUE KEY `UK_fhf1rlmtyvffhwwag72oitsdf` (`ref_user_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `localauth`
--

CREATE TABLE IF NOT EXISTS `localauth` (
  `localId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` int(11) NOT NULL,
  `user_Id` int(11) NOT NULL,
  PRIMARY KEY (`localId`),
  UNIQUE KEY `UK_i4agdpvup0dr98937t0rr9ybj` (`user_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `localauth`
--

INSERT INTO `localauth` (`localId`, `account`, `password`, `state`, `user_Id`) VALUES
(1, 'wutong', '123', 0, 1),
(2, '汤宏垚', '123', 1, 2),
(3, '15620947182', '123', 1, 3);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `aboutMe` varchar(255) DEFAULT NULL,
  `avatarPath` varchar(255) DEFAULT NULL,
  `lastIp` varchar(255) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userId`, `account`, `aboutMe`, `avatarPath`, `lastIp`, `createtime`, `updatetime`) VALUES
(1, 'wutong', '夕阳无限好只是，近黄昏', 'http://localhost:8080/upload/video/2016_03_28/1923614186601135/2016_03_28_11_59_01_何以爱情－《何以笙箫默》主题曲钢琴版_高清.jpg', NULL, '2016-03-29 03:51:23', '2016-03-29 03:51:24'),
(2, '汤宏垚', '往事随风', NULL, NULL, '2016-03-29 03:51:23', '2016-03-29 03:51:24'),
(3, '15620947182', '往事随风', NULL, NULL, '2016-03-29 03:51:23', '2016-03-29 03:51:24'),
(4, '123', '你好', NULL, NULL, '2016-03-29 03:41:01', '2016-03-29 03:41:01');

-- --------------------------------------------------------

--
-- 表的结构 `video`
--

CREATE TABLE IF NOT EXISTS `video` (
  `videoId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `performer` varchar(255) DEFAULT NULL,
  `album` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `praise` int(11) DEFAULT NULL,
  `targetDir` varchar(255) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `size` bigint(20) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `visitedTimes` int(11) DEFAULT NULL,
  `state` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`videoId`)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `video`
--

INSERT INTO `video` (`videoId`, `name`, `description`, `performer`, `album`, `path`, `imagePath`, `praise`, `targetDir`, `createtime`, `updatetime`, `size`, `duration`, `visitedTimes`, `state`, `type`) VALUES
(3, '何以爱情－《何以笙箫默》主题曲钢琴版_高清.mp4', '视频文件：何以爱情－《何以笙箫默》主题曲钢琴版_高清.mp4', 'xx 明星', 'xx 电视剧', 'http://localhost:8080/upload/video/2016_03_28/1923614186601135/2016_03_28_11_59_01_何以爱情－《何以笙箫默》主题曲钢琴版_高清.mp4', 'http://localhost:8080/upload/video/2016_03_28/1923614186601135/2016_03_28_11_59_01_何以爱情－《何以笙箫默》主题曲钢琴版_高清.jpg', 3, 'D:\\upload\\video\\2016_03_28\\1923614186601135\\', '2016-03-29 03:51:25', '2016-03-29 03:51:25', 23912117, '00:05:47.00', 0, 0, 4);

--
-- 限制导出的表
--

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FKcbxhti46t3h0lhohltfje2ysm` FOREIGN KEY (`ref_user_Id`) REFERENCES `user` (`userId`);

--
-- 限制表 `localauth`
--
ALTER TABLE `localauth`
  ADD CONSTRAINT `FK172abl1ppdvkhyxrqngcx7bxb` FOREIGN KEY (`user_Id`) REFERENCES `user` (`userId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
