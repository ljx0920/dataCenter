/*
Navicat MySQL Data Transfer

Source Server         : 10.10.1.74_3307
Source Server Version : 50630
Source Host           : 10.10.1.74:3307
Source Database       : fusion_stage

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2019-04-09 18:11:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for CS_MENU
-- ----------------------------
DROP TABLE IF EXISTS `CS_MENU`;
CREATE TABLE `CS_MENU` (
  `id` char(16) NOT NULL COMMENT '唯一标识',
  `parent_id` char(16) NOT NULL COMMENT '上级目录id',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `name` varchar(60) NOT NULL COMMENT '菜单名称',
  `level` int(10) NOT NULL COMMENT '级别',
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CS_MENU
-- ----------------------------
INSERT INTO `CS_MENU` VALUES ('0EA2ABAA6D054A00', '33', '16', '/systemMonitor/logCheck', '日志查看', '1', '2019-04-08 10:46:35');
INSERT INTO `CS_MENU` VALUES ('17', '0', 'international', '/servicePublish', '服务发布', '1', '2019-03-13 18:44:52');
INSERT INTO `CS_MENU` VALUES ('18', '0', 'link', '/serviceInsert', '服务接入', '2', '2019-03-22 15:56:03');
INSERT INTO `CS_MENU` VALUES ('19', '0', 'table', '/dataBase', '基础数据', '3', '2019-03-13 18:52:50');
INSERT INTO `CS_MENU` VALUES ('20', '0', 'user', '/systemManage', '系统管理', '4', '2019-03-13 18:52:50');
INSERT INTO `CS_MENU` VALUES ('21', '17', '05', '/servicePublish/sysRegister', '服务注册', '1', '2019-03-18 14:41:21');
INSERT INTO `CS_MENU` VALUES ('22', '17', '06', '/servicePublish/serviceAuth', '服务授权', '2', '2019-03-18 14:41:21');
INSERT INTO `CS_MENU` VALUES ('23', '19', '07', '/dataBase/dataDic', '数据字典', '1', '2019-03-13 18:40:47');
INSERT INTO `CS_MENU` VALUES ('24', '19', '08', '/dataBase/serviceTable', '业务表', '2', '2019-03-18 15:22:01');
INSERT INTO `CS_MENU` VALUES ('25', '20', '09', '/systemManage/userManage', '用户管理', '1', '2019-03-18 15:22:10');
INSERT INTO `CS_MENU` VALUES ('26', '20', '10', '/systemManage/customerManage', '客户管理', '2', '2019-03-18 15:22:16');
INSERT INTO `CS_MENU` VALUES ('27', '20', '11', '/systemManage/paramSystem', '系统参数', '3', '2019-03-18 15:22:21');
INSERT INTO `CS_MENU` VALUES ('28', '20', '12', '/systemManage/serviceManage', '服务器管理', '4', '2019-03-18 15:22:26');
INSERT INTO `CS_MENU` VALUES ('30', '20', '13', '/systemManage/engine', '引擎部署', '5', '2019-03-18 15:22:31');
INSERT INTO `CS_MENU` VALUES ('31', '20', '14', '/systemManage/menuManage', '菜单管理', '6', '2019-03-18 15:22:37');
INSERT INTO `CS_MENU` VALUES ('32', '20', '15', '/systemManage/gemManage', '执行组管理', '7', '2019-03-18 15:22:42');
INSERT INTO `CS_MENU` VALUES ('33', '0', 'form', '/systemMonitor', '系统监控', '5', '2019-03-29 18:08:15');
INSERT INTO `CS_MENU` VALUES ('E9B39BEEF10F4D63', '33', '17', '/systemMonitor/interfaceLog', '接口日志', '2', '2019-03-29 18:08:07');

-- ----------------------------
-- Table structure for CS_SYSTEM_PARAM
-- ----------------------------
DROP TABLE IF EXISTS `CS_SYSTEM_PARAM`;
CREATE TABLE `CS_SYSTEM_PARAM` (
  `id` char(16) NOT NULL COMMENT '唯一标识',
  `name` varchar(60) DEFAULT NULL COMMENT '系统参数名称',
  `system_param_catalog_id` char(16) DEFAULT NULL COMMENT '系统参数类别id',
  `system_key` varchar(50) DEFAULT NULL COMMENT '系统参数键',
  `system_value` varchar(200) DEFAULT NULL COMMENT '系统参数值',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CS_SYSTEM_PARAM
-- ----------------------------
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('193', '引擎部署类型', '1', 'engine.deploy.type', '01:restful,02:server', '2019-03-08 11:01:04');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('196', '系统名称', '22', 'system.name', '服务中台', '2019-03-22 14:00:57');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('198', '业务数据库地址、及账号密码', '20', 'business.db.info', 'ip:10.10.1.74,port:3307,db:buissness_table,user:root,pwd:123456', '2019-04-09 10:05:11');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('200', '系统日志地址', '22', 'system.log.address', '\\home\\clouddata\\iovCloud\\log', '2019-03-21 18:59:42');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('72FB19CC12ED4841', '请求方式类型', '20', 'service.request.method', '01:Get有参,02:Get无参,03:Post,04:Delete单个,05:Delete批量,06:Put', '2019-03-22 13:55:12');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('A018A2598B70411A', '系统英文名称', '22', 'system.nameEn', 'fusionStage', '2019-03-22 14:03:29');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('A95443BD2218424D', '服务参数类型', '20', 'service.param.type', '01:path,02:queryParam,03:header,04:jsonBody', '2019-03-22 13:33:55');
INSERT INTO `CS_SYSTEM_PARAM` VALUES ('E813E0F80AD540E0', 'restful接口认证方式', '670900CEDC604401', 'restful.auth.type', '01:免认证,02:用户名/密码认证,03:OAuth2.0', '2019-04-03 20:16:28');

-- ----------------------------
-- Table structure for CS_SYSTEM_PARAM_CATALOG
-- ----------------------------
DROP TABLE IF EXISTS `CS_SYSTEM_PARAM_CATALOG`;
CREATE TABLE `CS_SYSTEM_PARAM_CATALOG` (
  `id` char(16) NOT NULL COMMENT '唯一标识',
  `parent_id` char(16) DEFAULT NULL COMMENT '上级目录id',
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(60) DEFAULT NULL COMMENT '系统参数类别名',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CS_SYSTEM_PARAM_CATALOG
-- ----------------------------
INSERT INTO `CS_SYSTEM_PARAM_CATALOG` VALUES ('20', '18', null, '服务发布', '2019-03-13 15:21:10');
INSERT INTO `CS_SYSTEM_PARAM_CATALOG` VALUES ('22', null, null, '系统管理', '2019-03-18 10:53:30');
INSERT INTO `CS_SYSTEM_PARAM_CATALOG` VALUES ('670900CEDC604401', null, null, '服务接入', '2019-04-02 15:55:34');
INSERT INTO `CS_SYSTEM_PARAM_CATALOG` VALUES ('7D3FC2A04D824D2F', null, null, 'aa', '2019-04-08 11:30:50');

-- ----------------------------
-- Table structure for CS_USER
-- ----------------------------
DROP TABLE IF EXISTS `CS_USER`;
CREATE TABLE `CS_USER` (
  `id` char(16) NOT NULL COMMENT '唯一标识',
  `department_id` char(16) DEFAULT NULL COMMENT '所属部门id',
  `name` varchar(60) NOT NULL COMMENT '用户姓名',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `username` varchar(60) NOT NULL COMMENT '用户名',
  `pwd` varchar(60) NOT NULL COMMENT '用户密码',
  `birthday` date DEFAULT NULL COMMENT '用户生日',
  `gender` tinyint(1) DEFAULT NULL COMMENT '用户性别',
  `mobile_phone` varchar(11) NOT NULL COMMENT '用户电话',
  `unit` varchar(32) DEFAULT NULL COMMENT '用户部门',
  `start_date` datetime NOT NULL COMMENT '起始日期',
  `end_date` datetime NOT NULL COMMENT '结束日期',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识',
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`,`mobile_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CS_USER
-- ----------------------------
INSERT INTO `CS_USER` VALUES ('156587C97386423B', '3', '赵瑞祥', '461c7dfe7ff72d0abce75a002202c62c', 'zrx', '1412548828c77cac5e7015bb5bb1841c', null, null, '1231525855', '恒润', '2019-03-09 08:00:00', '2019-04-14 08:00:00', '0', '2019-04-02 14:49:07');
INSERT INTO `CS_USER` VALUES ('2EF20C3483D34E79', '3', 'xuzhou', '997719ab610db83925cf5b3fb5d626e4', 'xz', '62a0505eaf7d847d5afe0b386a98ff96', null, null, '1234567890', 'hirain', '2019-04-01 00:00:00', '2020-05-02 00:00:00', '0', '2019-04-02 17:07:30');
INSERT INTO `CS_USER` VALUES ('7A2FFA83139F4915', '1', 'test', 'fe7019d9440276517d0f2402b3874c6d', 'test', '9c665b2c637bb105a4a928e687f8cfce', null, null, '13152693689', '恒润', '2019-03-27 08:00:00', '2019-04-27 08:00:00', '0', '2019-03-27 10:12:42');
INSERT INTO `CS_USER` VALUES ('A18DC14071504A96', '3', '白亚勇', '6ec7bc46551ccb6e4c9844512f98b912', 'byy', 'd2e3c90d396394bfdddcd8a90dfab454', null, null, '123456789', '恒润', '2019-01-29 08:00:00', '2019-04-18 08:00:00', '0', '2019-04-02 14:52:34');
