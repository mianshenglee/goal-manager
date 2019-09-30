
-- ----------------------------
-- 2019-09-17 mason  添加目标表、想法表、总结表、热点表
-- ----------------------------

-- 目标表
CREATE TABLE `gm_goal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `record_version` int(11) NOT NULL DEFAULT '1' COMMENT '记录版本',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sys_update_by` varchar(30) DEFAULT NULL COMMENT '更新人',
  `goal_phase` varchar(100) NOT NULL DEFAULT '' COMMENT '目标阶段（见字典表：年、月、日、时）',
  `goal_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '目标所属时间',
  `goal_type` varchar(100) NOT NULL DEFAULT '' COMMENT '目标类型（见字典表：技能、学习...）',
  `goal_content` varchar(300) NOT NULL DEFAULT '' COMMENT '目标内容',
  `process` int(11) NOT NULL DEFAULT '0' COMMENT '目标完成进度',
  `is_deleted` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除：1是，0否',
  `is_extra` char(1) NOT NULL DEFAULT '0' COMMENT '是否计划外的：1是，0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目标表';

-- 热点表
CREATE TABLE `gm_hotspot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `record_version` int(11) NOT NULL DEFAULT '1' COMMENT '记录版本',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sys_update_by` varchar(30) DEFAULT NULL COMMENT '更新人',
  `hot_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '热点时间',
  `title` varchar(300) NOT NULL DEFAULT '' COMMENT '热点标题',
  `link` varchar(200) NOT NULL DEFAULT '' COMMENT '热点链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热点表';

-- 想法表
CREATE TABLE `gm_idea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `record_version` int(11) NOT NULL DEFAULT '1' COMMENT '记录版本',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sys_update_by` varchar(30) DEFAULT NULL COMMENT '更新人',
  `content` varchar(300) NOT NULL DEFAULT '' COMMENT '想法内容',
  `type` varchar(100) NOT NULL DEFAULT '' COMMENT '想法类型',
  `idea_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '想法所属时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='想法表';

-- 总结表
CREATE TABLE `gm_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `record_version` int(11) NOT NULL DEFAULT '1' COMMENT '记录版本',
  `sys_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_create_by` varchar(30) DEFAULT NULL COMMENT '创建人',
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sys_update_by` varchar(30) DEFAULT NULL COMMENT '更新人',
  `summary_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '总结的时间',
  `summary` varchar(500) NOT NULL DEFAULT '' COMMENT '总结内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='总结表';


-- 目标
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1061','目标','0','1','#','menuItem','M','0',NULL,'fa fa-anchor','admin','2019-09-17 17:46:32','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1062','月目标','1061','1','/goal/month','menuItem','C','0','goal:month:view','fa fa-calendar-check-o','admin','2019-09-17 17:49:38','',NULL,'');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1063','目标查询','1062','1','#','','F','0','goal:month:list','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1064','目标新增','1062','2','#','','F','0','goal:month:add','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1065','目标修改','1062','3','#','','F','0','goal:month:edit','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','');
insert into `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('1066','目标删除','1062','4','#','','F','0','goal:month:remove','#','admin','2018-03-01 00:00:00','ry','2018-03-01 00:00:00','');


-- 总结
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('总结查询', '1062', '5',  '#',  'F', '0', 'goal:summary:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('总结新增', '1062', '6',  '#',  'F', '0', 'goal:summary:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('总结修改', '1062', '7',  '#',  'F', '0', 'goal:summary:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('总结删除', '1062', '8',  '#',  'F', '0', 'goal:summary:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- 想法
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('想法查询', '1062', '9',  '#',  'F', '0', 'goal:idea:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('想法新增', '1062', '10',  '#',  'F', '0', 'goal:idea:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('想法修改', '1062', '11',  '#',  'F', '0', 'goal:idea:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('想法删除', '1062', '12',  '#',  'F', '0', 'goal:idea:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- 热点
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('热点查询', '1062', '13',  '#',  'F', '0', 'goal:hotspot:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('热点新增', '1062', '14',  '#',  'F', '0', 'goal:hotspot:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('热点修改', '1062', '15',  '#',  'F', '0', 'goal:hotspot:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('热点删除', '1062', '16',  '#',  'F', '0', 'goal:hotspot:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

-- 权限
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1061');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1062');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1063');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1064');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1065');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1066');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1082');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1083');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1084');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1085');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1086');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1087');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1088');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1089');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1090');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1091');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1092');
insert into `sys_role_menu` (`role_id`, `menu_id`) values('2','1093');

-- 类型
insert into `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('11','目标阶段','goal_phase','0','admin','2019-09-18 11:43:18','',NULL,'标识目标所属时间范围');
insert into `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('12','目标类型','goal_type','0','admin','2019-09-18 11:46:11','admin','2019-09-18 12:33:52','目标类型，如技能、学习、生活等');
insert into `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('13','是否删除','is_deleted','0','admin','2019-09-18 12:34:23','',NULL,'删除状态');
insert into `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('14','是否计划外','is_extra','0','admin','2019-09-18 12:38:09','',NULL,'是否计划外的内容');
insert into `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('15','想法类型','idea_type','0','admin','2019-09-27 10:21:56','',NULL,'想法类型，如技术，学习，生活等');

-- 类型值
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('29','0','年目标','year','goal_phase',NULL,'primary','N','0','admin','2019-09-18 11:44:41','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('30','1','月目标','month','goal_phase',NULL,'primary','N','0','admin','2019-09-18 11:45:04','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('31','2','日目标','day','goal_phase',NULL,'primary','N','0','admin','2019-09-18 11:45:22','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('32','3','时目标','3','goal_phase',NULL,'primary','N','0','admin','2019-09-18 11:45:43','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('33','0','技能目标','skill','goal_type',NULL,'primary','N','0','admin','2019-09-18 11:48:11','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('34','1','学习目标','study','goal_type','','primary','N','0','admin','2019-09-18 11:48:34','admin','2019-09-18 11:48:49','');
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('35','2','生活目标','life','goal_type','','primary','N','0','admin','2019-09-18 11:56:20','admin','2019-09-18 11:57:50','');
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('36','3','理财目标','finance','goal_type','','primary','N','0','admin','2019-09-18 11:57:16','admin','2019-09-18 11:57:54','');
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('37','4','健康目标','health','goal_type',NULL,'primary','N','0','admin','2019-09-18 11:58:22','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('38','1','已删除','1','is_deleted','','primary','N','0','admin','2019-09-18 12:34:46','admin','2019-09-18 12:35:35','');
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('39','0','未删除','0','is_deleted',NULL,'primary','Y','0','admin','2019-09-18 12:35:28','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('40','1','是','1','is_extra',NULL,'primary','N','0','admin','2019-09-18 12:38:36','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('41','0','否','0','is_extra',NULL,'primary','N','0','admin','2019-09-18 12:38:53','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('42','0','技能','skill','idea_type',NULL,NULL,'Y','0','admin','2019-09-27 10:22:26','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('43','1','学习','study','idea_type',NULL,NULL,'Y','0','admin','2019-09-27 10:22:50','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('44','2','生活','life','idea_type',NULL,NULL,'Y','0','admin','2019-09-27 10:23:46','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('45','3','理财','finance','idea_type',NULL,NULL,'Y','0','admin','2019-09-27 10:24:26','',NULL,NULL);
insert into `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) values('46','4','健康','health','idea_type',NULL,NULL,'Y','0','admin','2019-09-27 10:25:04','',NULL,NULL);