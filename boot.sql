-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- Server version:               8.0.16 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for tutor_test
CREATE DATABASE IF NOT EXISTS `tutor_test` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tutor_test`;

-- Dumping structure for table tutor_test.sys_admin_user
CREATE TABLE IF NOT EXISTS `sys_admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `is_lock` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`admin_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_admin_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `sys_admin_user` DISABLE KEYS */;
INSERT INTO `sys_admin_user` (`admin_user_id`, `nickname`, `username`, `password`, `is_lock`) VALUES
	(1, '1', 'admin', '1', 0);
/*!40000 ALTER TABLE `sys_admin_user` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_comments
CREATE TABLE IF NOT EXISTS `sys_comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `sub_id` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `like_num` int(11) DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_comments: ~4 rows (approximately)
/*!40000 ALTER TABLE `sys_comments` DISABLE KEYS */;
INSERT INTO `sys_comments` (`comment_id`, `user_id`, `sub_id`, `content`, `like_num`, `is_deleted`, `create_time`, `update_time`) VALUES
	(7, 1, 6, '天王6666666', 0, 0, '2024-04-17 17:49:28', '2024-04-17 17:49:28'),
	(8, 1, 22, '歌神预览图好好笑', 0, 0, '2024-04-17 17:49:53', '2024-04-17 17:49:53'),
	(9, 1, 30, '帅啊流星', 0, 0, '2024-04-17 17:52:01', '2024-04-17 17:52:01'),
	(10, 1, 30, '不对刘星', 0, 0, '2024-04-17 17:52:08', '2024-04-17 17:52:08');
/*!40000 ALTER TABLE `sys_comments` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_index_config
CREATE TABLE IF NOT EXISTS `sys_index_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键ID',
  `config_name` varchar(50) DEFAULT NULL COMMENT '配置项名称',
  `config_type` tinyint(4) DEFAULT NULL COMMENT '配置类型 最新 最热 推荐',
  `config_tutor_id` int(11) DEFAULT NULL COMMENT '对应的教师ID',
  `config_sub_id` int(11) DEFAULT NULL COMMENT '关联教师和课程映射表',
  `config_rank` int(11) DEFAULT NULL COMMENT '排序值',
  `redirect_url` varchar(50) DEFAULT NULL COMMENT '点击后跳转的地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_admin_id` int(11) DEFAULT '0' COMMENT '创建的管理员ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_admin_id` int(11) DEFAULT '0' COMMENT '修改的管理员ID',
  `is_expire` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_index_config: ~8 rows (approximately)
/*!40000 ALTER TABLE `sys_index_config` DISABLE KEYS */;
INSERT INTO `sys_index_config` (`config_id`, `config_name`, `config_type`, `config_tutor_id`, `config_sub_id`, `config_rank`, `redirect_url`, `create_time`, `create_admin_id`, `update_time`, `update_admin_id`, `is_expire`) VALUES
	(5, '张一山带你学化学', 1, 14, 30, 0, NULL, '2024-04-17 17:42:49', NULL, '2024-04-17 17:42:49', NULL, 0),
	(6, '歌神吉他', 1, 10, 22, 0, NULL, '2024-04-17 17:44:14', NULL, '2024-04-17 17:44:14', NULL, 0),
	(7, '刘德华贝斯课', 1, 3, 6, 0, NULL, '2024-04-17 17:44:37', NULL, '2024-04-17 17:44:37', NULL, 0),
	(8, '成龙高中英语', 2, 4, 8, 0, NULL, '2024-04-17 17:45:09', NULL, '2024-04-17 17:45:09', NULL, 0),
	(9, '韩红初中英语', 2, 8, 17, 0, NULL, '2024-04-17 17:45:23', NULL, '2024-04-17 17:45:23', NULL, 0),
	(10, '杨幂语文课堂', 2, 6, 13, 0, NULL, '2024-04-17 17:45:52', NULL, '2024-04-17 17:45:52', NULL, 0),
	(11, '赵本山吉他课', 3, 2, 4, 0, NULL, '2024-04-17 17:46:21', NULL, '2024-04-17 17:46:21', NULL, 0),
	(12, '仙女姐姐小学数学课堂', 3, 7, 15, 0, NULL, '2024-04-17 17:46:31', NULL, '2024-04-17 17:46:31', NULL, 0);
/*!40000 ALTER TABLE `sys_index_config` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_sub_category
CREATE TABLE IF NOT EXISTS `sys_sub_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类等级',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级分类id',
  `sub_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '科目名称',
  `category_rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序值',
  `is_expire` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_admin_id` int(11) NOT NULL DEFAULT '0',
  `update_admin_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_sub_category: ~27 rows (approximately)
/*!40000 ALTER TABLE `sys_sub_category` DISABLE KEYS */;
INSERT INTO `sys_sub_category` (`category_id`, `category_level`, `parent_id`, `sub_name`, `category_rank`, `is_expire`, `create_time`, `update_time`, `create_admin_id`, `update_admin_id`) VALUES
	(1, 1, 0, '高中', 0, 0, '2024-04-03 19:35:37', '2024-04-03 19:35:37', 0, 0),
	(2, 1, 0, '初中', 0, 0, '2024-04-03 19:36:31', '2024-04-03 19:36:31', 0, 0),
	(3, 1, 0, '小学', 0, 0, '2024-04-03 19:36:38', '2024-04-03 19:36:38', 0, 0),
	(4, 1, 0, '其他', 0, 0, '2024-04-03 19:36:54', '2024-04-03 19:36:54', 0, 0),
	(5, 2, 1, '语文', 0, 0, '2024-04-17 16:13:50', '2024-04-17 16:13:50', 0, 0),
	(6, 2, 1, '数学', 0, 0, '2024-04-17 16:13:54', '2024-04-17 16:13:54', 0, 0),
	(7, 2, 1, '英语', 0, 0, '2024-04-17 16:13:58', '2024-04-17 16:13:58', 0, 0),
	(8, 2, 1, '物理', 0, 0, '2024-04-17 16:14:00', '2024-04-17 16:14:00', 0, 0),
	(9, 2, 1, '化学', 0, 0, '2024-04-17 16:14:54', '2024-04-17 16:14:55', 0, 0),
	(10, 2, 1, '生物', 0, 0, '2024-04-17 16:15:11', '2024-04-17 16:15:12', 0, 0),
	(11, 2, 1, '政治', 0, 0, '2024-04-17 16:15:28', '2024-04-17 16:15:28', 0, 0),
	(12, 2, 1, '历史', 0, 0, '2024-04-17 16:15:36', '2024-04-17 16:15:36', 0, 0),
	(13, 2, 1, '地理', 0, 0, '2024-04-17 16:15:44', '2024-04-17 16:15:44', 0, 0),
	(14, 2, 2, '数学', 0, 0, '2024-04-17 16:19:38', '2024-04-17 16:19:38', 0, 0),
	(15, 2, 2, '语文', 0, 0, '2024-04-17 16:20:34', '2024-04-17 16:20:34', 0, 0),
	(16, 2, 2, '英语', 0, 0, '2024-04-17 16:20:42', '2024-04-17 16:20:42', 0, 0),
	(17, 2, 2, '物理', 33, 0, '2024-04-17 16:20:47', '2024-04-17 16:20:47', 0, 0),
	(18, 2, 2, '化学', 0, 0, '2024-04-17 16:20:50', '2024-04-17 16:20:50', 0, 0),
	(19, 2, 2, '政治', 0, 0, '2024-04-17 16:20:54', '2024-04-17 16:20:54', 0, 0),
	(20, 2, 2, '历史', 6, 0, '2024-04-17 16:20:58', '2024-04-17 16:20:58', 0, 0),
	(21, 2, 2, '地理', 0, 0, '2024-04-17 16:21:01', '2024-04-17 16:21:01', 0, 0),
	(22, 2, 2, '生物', 22, 0, '2024-04-17 16:21:06', '2024-04-17 16:21:06', 0, 0),
	(23, 2, 3, '数学', 11, 0, '2024-04-17 16:21:39', '2024-04-17 16:21:39', 0, 0),
	(24, 2, 3, '语文', 0, 0, '2024-04-17 16:21:42', '2024-04-17 16:21:42', 0, 0),
	(25, 2, 3, '英语', 0, 0, '2024-04-17 16:21:46', '2024-04-17 16:21:46', 0, 0),
	(26, 2, 4, '计算机', 10, 0, '2024-04-17 16:22:00', '2024-04-17 16:22:00', 0, 0),
	(27, 2, 4, '篮球', 0, 0, '2024-04-17 16:22:10', '2024-04-17 16:22:10', 0, 0),
	(28, 2, 4, '乒乓球', 88, 0, '2024-04-17 16:22:14', '2024-04-17 16:22:14', 0, 0),
	(29, 2, 4, '架子鼓', 0, 0, '2024-04-17 16:22:17', '2024-04-17 16:22:17', 0, 0),
	(30, 2, 4, '贝斯', 9, 0, '2024-04-17 16:22:20', '2024-04-17 16:22:20', 0, 0),
	(31, 2, 4, '吉他', 9, 0, '2024-04-17 16:22:23', '2024-04-17 16:22:23', 0, 0);
/*!40000 ALTER TABLE `sys_sub_category` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_tutor
CREATE TABLE IF NOT EXISTS `sys_tutor` (
  `tutor_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '家教主键id',
  `tutor_name` varchar(40) DEFAULT NULL COMMENT '家教姓名',
  `tutor_gender` varchar(40) DEFAULT NULL COMMENT '家教性别',
  `tutor_photo` varchar(600) DEFAULT NULL COMMENT '照片',
  `tutor_age` int(11) DEFAULT NULL COMMENT '家教年龄',
  `tutor_phone` varchar(20) DEFAULT NULL COMMENT '家教联系电话',
  `area` varchar(100) DEFAULT NULL COMMENT '地区',
  `effective` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已生效',
  `brief_introduction` varchar(300) DEFAULT NULL COMMENT '个人简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_expire` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  `lock` tinyint(4) DEFAULT '0' COMMENT '锁定',
  `create_admin_id` int(11) DEFAULT '0',
  `update_admin_id` int(11) DEFAULT '0',
  `user_id` bigint(20) DEFAULT '0',
  PRIMARY KEY (`tutor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_tutor: ~14 rows (approximately)
/*!40000 ALTER TABLE `sys_tutor` DISABLE KEYS */;
INSERT INTO `sys_tutor` (`tutor_id`, `tutor_name`, `tutor_gender`, `tutor_photo`, `tutor_age`, `tutor_phone`, `area`, `effective`, `brief_introduction`, `create_time`, `update_time`, `is_expire`, `lock`, `create_admin_id`, `update_admin_id`, `user_id`) VALUES
	(1, '张三丰', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zsf.png', 81, '12666666666', '湖北省十堰市丹江口市武当山', 1, '武当派祖师爷，擅长各种球类运动', '2024-04-17 16:30:40', '2024-04-17 16:30:40', 0, 0, 0, 0, 0),
	(2, '赵本山', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zbs.png', 66, '13111111111', '辽宁省铁岭市开原市', 1, '小品，喜剧明星，擅长高中数学，吉他', '2024-04-17 16:23:12', '2024-04-17 16:23:12', 0, 0, 0, 0, 0),
	(3, '刘德华', '男', 'http://sbly3w188.hn-bkt.clouddn.com/ldh.jpeg', 62, '13222222222', '中国香港', 1, '四大天王之一，擅长各种乐器', '2024-04-17 16:35:03', '2024-04-17 16:35:03', 0, 0, 0, 0, 0),
	(4, '成龙', '男', 'http://sbly3w188.hn-bkt.clouddn.com/cl.jpg', 70, '13333333333', '中国香港', 1, '著名武打巨星，擅长全年级英语', '2024-04-17 16:36:14', '2024-04-17 16:36:14', 0, 0, 0, 0, 0),
	(5, '周润发', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zrf.jpeg', 68, '13555555555', '中国香港', 1, '赌神，擅长全年级数学', '2024-04-17 16:38:52', '2024-04-17 16:38:52', 0, 0, 0, 0, 0),
	(6, '杨幂', '女', 'http://sbly3w188.hn-bkt.clouddn.com/ym.png', 37, '13666666666', '北京市', 1, '著名女演员，擅长中小学语文以及架子鼓', '2024-04-17 16:39:19', '2024-04-17 16:39:19', 0, 0, 0, 0, 0),
	(7, '刘亦菲', '女', 'http://sbly3w188.hn-bkt.clouddn.com/lyf.png', 36, '13777777777', '湖北省武汉市', 1, '著名女演员，擅长小学数学', '2024-04-17 16:41:26', '2024-04-17 16:41:26', 0, 0, 0, 0, 0),
	(8, '韩红', '女', 'http://sbly3w188.hn-bkt.clouddn.com/hh.jpg', 52, '13888888888', '西藏省昌都市', 1, '著名女歌手，慈善家，擅长小学英语，初中英语', '2024-04-17 16:42:30', '2024-04-17 16:42:30', 0, 0, 0, 0, 0),
	(9, '周星驰', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zxc.jpg', 61, '13999999999', '中国香港', 1, '著名喜剧演员，擅长初中化学，物理', '2024-04-17 16:43:45', '2024-04-17 16:43:45', 0, 0, 0, 0, 0),
	(10, '张学友', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zxy.png', 62, '15111111111', '中国香港', 1, '有歌神之称，擅长各种乐器', '2024-04-17 16:45:15', '2024-04-17 16:45:15', 0, 0, 0, 0, 0),
	(11, '林志炫', '男', 'http://sbly3w188.hn-bkt.clouddn.com/lzx.png', 57, '15222222222', '中国台湾', 1, '著名男歌手，擅长高中政治和贝斯', '2024-04-17 16:46:22', '2024-04-17 16:46:22', 0, 0, 0, 0, 0),
	(12, '谭晶', '女', 'http://sbly3w188.hn-bkt.clouddn.com/tj.jpg', 46, '15333333333', '陕西省侯马市', 1, '女高音歌唱家，擅长各种乐器', '2024-04-17 16:48:06', '2024-04-17 16:48:06', 0, 0, 0, 0, 0),
	(13, '杨紫', '女', 'http://sbly3w188.hn-bkt.clouddn.com/yz.jpg', 31, '15555555555', '北京市', 0, '著名女演员，擅长高中生物地理', '2024-04-17 16:49:05', '2024-04-17 16:49:05', 1, 0, 0, 0, 0),
	(14, '张一山', '男', 'http://sbly3w188.hn-bkt.clouddn.com/zys.png', 31, '15666666666', '北京市西城区', 0, '著名男演员，擅长高中物理化学', '2024-04-17 16:49:57', '2024-04-17 16:49:57', 1, 0, 0, 0, 0);
/*!40000 ALTER TABLE `sys_tutor` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_tutor_sub
CREATE TABLE IF NOT EXISTS `sys_tutor_sub` (
  `tutor_sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_category_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联课程分类id',
  `sub_name` varchar(50) NOT NULL DEFAULT '0',
  `price_lesson` int(11) NOT NULL DEFAULT '0' COMMENT '元/课时',
  `tutor_id` int(11) DEFAULT NULL COMMENT '关联教师id',
  `tutor_name` varchar(50) DEFAULT NULL,
  `tutor_photo` varchar(300) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_admin_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_admin_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0' COMMENT '是否失效',
  `is_expire` int(11) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`tutor_sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_tutor_sub: ~26 rows (approximately)
/*!40000 ALTER TABLE `sys_tutor_sub` DISABLE KEYS */;
INSERT INTO `sys_tutor_sub` (`tutor_sub_id`, `sub_category_id`, `sub_name`, `price_lesson`, `tutor_id`, `tutor_name`, `tutor_photo`, `create_time`, `create_admin_id`, `update_time`, `update_admin_id`, `status`, `is_expire`) VALUES
	(1, 27, '篮球', 150, 1, '张三丰教篮球', 'http://sbly3w188.hn-bkt.clouddn.com/zsf.png', '2024-04-17 17:11:04', 0, '2024-04-17 17:11:04', 0, 0, 0),
	(2, 28, '乒乓球', 200, 1, '武当张三丰', 'http://sbly3w188.hn-bkt.clouddn.com/zsf.png', '2024-04-17 17:11:25', 0, '2024-04-17 17:11:25', 0, 0, 0),
	(3, 6, '数学', 80, 2, '赵本山数学讲堂', 'http://sbly3w188.hn-bkt.clouddn.com/zbs.png', '2024-04-17 17:13:26', 0, '2024-04-17 17:13:26', 0, 0, 0),
	(4, 31, '吉他', 150, 2, '赵本山吉他课', 'http://sbly3w188.hn-bkt.clouddn.com/zbs.png', '2024-04-17 17:13:28', 0, '2024-04-17 17:13:28', 0, 0, 0),
	(5, 30, '吉他', 200, 3, '天王吉他课', 'http://sbly3w188.hn-bkt.clouddn.com/ldh.jpeg', '2024-04-17 17:14:48', 0, '2024-04-17 17:14:48', 0, 0, 0),
	(6, 31, '贝斯', 200, 3, '刘德华贝斯课', 'http://sbly3w188.hn-bkt.clouddn.com/ldh.jpeg', '2024-04-17 17:14:53', 0, '2024-04-17 17:14:53', 0, 0, 0),
	(7, 25, '英语', 180, 4, '成龙小学英语速成班', 'http://sbly3w188.hn-bkt.clouddn.com/cl.jpg', '2024-04-17 17:19:43', 0, '2024-04-17 17:19:43', 0, 0, 0),
	(8, 7, '英语', 200, 4, '成龙高中英语速成班', 'http://sbly3w188.hn-bkt.clouddn.com/cl.jpg', '2024-04-17 17:19:14', 0, '2024-04-17 17:19:14', 0, 0, 0),
	(9, 16, '英语', 190, 4, '成龙初中英语速成班', 'http://sbly3w188.hn-bkt.clouddn.com/cl.jpg', '2024-04-17 17:19:29', 0, '2024-04-17 17:19:29', 0, 0, 0),
	(10, 23, '数学', 180, 5, '周润发小学数学速成班', 'http://sbly3w188.hn-bkt.clouddn.com/zrf.jpeg', '2024-04-17 17:22:03', 0, '2024-04-17 17:22:03', 0, 0, 0),
	(11, 14, '数学', 190, 5, '周润发初中数学速成班', 'http://sbly3w188.hn-bkt.clouddn.com/zrf.jpeg', '2024-04-17 17:22:49', 0, '2024-04-17 17:22:49', 0, 0, 0),
	(12, 6, '数学', 200, 5, '周润发高中数学速成班', 'http://sbly3w188.hn-bkt.clouddn.com/zrf.jpeg', '2024-04-17 17:23:26', 0, '2024-04-17 17:23:26', 0, 0, 0),
	(13, 24, '语文', 100, 6, '杨幂语文课堂', 'http://sbly3w188.hn-bkt.clouddn.com/ym.png', '2024-04-17 17:23:55', 0, '2024-04-17 17:23:55', 0, 0, 0),
	(14, 29, '架子鼓', 150, 6, '杨幂架子鼓速成课', 'http://sbly3w188.hn-bkt.clouddn.com/ym.png', '2024-04-17 17:25:02', 0, '2024-04-17 17:25:02', 0, 0, 0),
	(15, 23, '数学', 500, 7, '仙女姐姐小学数学课堂', 'http://sbly3w188.hn-bkt.clouddn.com/lyf.png', '2024-04-17 17:28:35', 0, '2024-04-17 17:28:35', 0, 0, 0),
	(16, 25, '英语', 100, 8, '韩红小学英语', 'http://sbly3w188.hn-bkt.clouddn.com/hh.jpg', '2024-04-17 17:29:10', 0, '2024-04-17 17:29:10', 0, 0, 0),
	(17, 16, '英语', 120, 8, '韩红初中英语', 'http://sbly3w188.hn-bkt.clouddn.com/hh.jpg', '2024-04-17 17:29:32', 0, '2024-04-17 17:29:32', 0, 0, 0),
	(18, 17, '物理', 120, 9, '周星驰物理学堂', 'http://sbly3w188.hn-bkt.clouddn.com/zxc.jpg', '2024-04-17 17:31:47', 0, '2024-04-17 17:31:47', 0, 0, 0),
	(19, 18, '化学', 120, 9, '周星驰化学学堂', 'http://sbly3w188.hn-bkt.clouddn.com/zxc.jpg', '2024-04-17 17:32:01', 0, '2024-04-17 17:32:01', 0, 0, 0),
	(20, 29, '架子鼓', 200, 10, '歌神架子鼓', 'http://sbly3w188.hn-bkt.clouddn.com/zxy.png', '2024-04-17 17:33:50', 0, '2024-04-17 17:33:50', 0, 0, 0),
	(21, 30, '贝斯', 200, 10, '歌神贝斯', 'http://sbly3w188.hn-bkt.clouddn.com/zxy.png', '2024-04-17 17:34:02', 0, '2024-04-17 17:34:02', 0, 0, 0),
	(22, 31, '吉他', 200, 10, '歌神吉他', 'http://sbly3w188.hn-bkt.clouddn.com/zxy.png', '2024-04-17 17:34:05', 0, '2024-04-17 17:34:05', 0, 0, 0),
	(23, 11, '政治', 130, 11, '林志炫高考政治', 'http://sbly3w188.hn-bkt.clouddn.com/lzx.png', '2024-04-17 17:36:38', 0, '2024-04-17 17:36:38', 0, 0, 0),
	(24, 30, '贝斯', 100, 11, '林志炫四弦贝斯', 'http://sbly3w188.hn-bkt.clouddn.com/lzx.png', '2024-04-17 17:36:38', 0, '2024-04-17 17:36:38', 0, 0, 0),
	(25, 29, '架子鼓', 150, 12, '谭晶架子鼓', 'http://sbly3w188.hn-bkt.clouddn.com/tj.jpg', '2024-04-17 17:37:49', 0, '2024-04-17 17:37:49', 0, 0, 0),
	(26, 30, '贝斯', 150, 12, '谭晶贝斯', 'http://sbly3w188.hn-bkt.clouddn.com/tj.jpg', '2024-04-17 17:37:49', 0, '2024-04-17 17:37:49', 0, 0, 0),
	(27, 10, '生物', 110, 13, '杨紫高中生物', 'http://sbly3w188.hn-bkt.clouddn.com/yz.jpg', '2024-04-17 17:41:01', 0, '2024-04-17 17:41:01', 0, 0, 0),
	(28, 13, '地理', 120, 13, '杨紫高中地理', 'http://sbly3w188.hn-bkt.clouddn.com/yz.jpg', '2024-04-17 17:41:01', 0, '2024-04-17 17:41:01', 0, 0, 0),
	(29, 8, '物理', 80, 14, '张一山带你学物理', 'http://sbly3w188.hn-bkt.clouddn.com/zys.png', '2024-04-17 17:41:01', 0, '2024-04-17 17:41:01', 0, 0, 0),
	(30, 9, '化学', 80, 14, '张一山带你学化学', 'http://sbly3w188.hn-bkt.clouddn.com/zys.png', '2024-04-17 17:41:01', 0, '2024-04-17 17:41:01', 0, 0, 0);
/*!40000 ALTER TABLE `sys_tutor_sub` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_user
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_phone` varchar(15) DEFAULT NULL COMMENT '登陆用户名/手机号',
  `password` varchar(25) DEFAULT NULL COMMENT '登陆密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `user_email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `brief_introduction` varchar(300) DEFAULT NULL COMMENT '简介',
  `avatar_url` varchar(200) DEFAULT '/images/personal.png' COMMENT '头像',
  `tutor_flag` tinyint(4) DEFAULT '0',
  `score` int(11) DEFAULT '0' COMMENT '积分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_expire` tinyint(4) DEFAULT '0' COMMENT '是否逻辑删除',
  `lock` tinyint(4) DEFAULT '0' COMMENT '是否已锁定',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `login_phone`, `password`, `nickname`, `phone`, `user_email`, `brief_introduction`, `avatar_url`, `tutor_flag`, `score`, `create_time`, `update_time`, `is_expire`, `lock`) VALUES
	(0, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', '/images/personal.png', 0, 0, '2024-04-16 14:56:18', '2024-04-16 14:56:18', 0, 0),
	(1, '13936717370', '1', '小帕', '123456789', '123456789@qq.com', '帅哥', 'http://sbly3w188.hn-bkt.clouddn.com/Ft5YpIQa4koqTzaKhKzLE-uiIiVd', 1, 0, '2024-04-08 14:26:57', '2024-04-08 14:27:11', 0, 0),
	(2, '11111111111', '1', NULL, NULL, NULL, NULL, '/images/personal.png', 0, 0, '2024-04-08 14:26:57', '2024-04-08 14:27:11', 0, 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_user_coll
CREATE TABLE IF NOT EXISTS `sys_user_coll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `tutor_sub_id` int(11) DEFAULT NULL,
  `id_deleted` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_user_coll: ~4 rows (approximately)
/*!40000 ALTER TABLE `sys_user_coll` DISABLE KEYS */;
INSERT INTO `sys_user_coll` (`id`, `user_id`, `tutor_sub_id`, `id_deleted`, `create_time`, `update_time`) VALUES
	(16, 1, 30, 0, '2024-04-17 17:51:02', '2024-04-17 17:51:02'),
	(17, 1, 22, 0, '2024-04-17 17:57:18', '2024-04-17 17:57:18'),
	(18, 1, 6, 0, '2024-04-17 17:58:55', '2024-04-17 17:58:55'),
	(19, 1, 4, 0, '2024-04-17 18:01:23', '2024-04-17 18:01:23');
/*!40000 ALTER TABLE `sys_user_coll` ENABLE KEYS */;

-- Dumping structure for table tutor_test.sys_user_wish
CREATE TABLE IF NOT EXISTS `sys_user_wish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `tutor_sub_id` int(11) DEFAULT NULL,
  `id_deleted` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table tutor_test.sys_user_wish: ~4 rows (approximately)
/*!40000 ALTER TABLE `sys_user_wish` DISABLE KEYS */;
INSERT INTO `sys_user_wish` (`id`, `user_id`, `tutor_sub_id`, `id_deleted`, `create_time`, `update_time`) VALUES
	(10, 1, 30, 0, '2024-04-17 17:51:01', '2024-04-17 17:51:01'),
	(11, 1, 22, 0, '2024-04-17 17:57:18', '2024-04-17 17:57:18'),
	(12, 1, 6, 0, '2024-04-17 17:58:56', '2024-04-17 17:58:56'),
	(13, 1, 4, 0, '2024-04-17 18:01:23', '2024-04-17 18:01:23'),
	(14, 1, 13, 0, '2024-04-17 18:01:31', '2024-04-17 18:01:31');
/*!40000 ALTER TABLE `sys_user_wish` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
