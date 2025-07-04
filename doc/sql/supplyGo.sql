-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(12) NULL DEFAULT '000000' COMMENT '租户ID',
  `code` BIGINT AUTO_INCREMENT UNIQUE COMMENT '用户编号',
  `account` varchar(45) NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(10) NULL DEFAULT NULL COMMENT '真名',
  `avatar` varchar(2000) NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(45) NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(45) NULL DEFAULT NULL COMMENT '手机',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` TINYINT NULL DEFAULT NULL COMMENT '性别，0-隐藏，1-男，2-女',
  `role_id` varchar(1000) NULL DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(1000) NULL DEFAULT NULL COMMENT '部门id',
  `post_id` varchar(1000) NULL DEFAULT NULL COMMENT '岗位id',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT 1 COMMENT '状态，0-禁用，1-启用',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除',
   -- 针对 is_deleted=0 时的 account 做唯一约束
  `active_account` varchar(45) GENERATED ALWAYS AS (
    CASE WHEN is_deleted = 0 THEN account ELSE NULL END
  ) STORED,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`),
  UNIQUE KEY `uk_active_account` (`active_account`)
) ENGINE = InnoDB AUTO_INCREMENT=1000 COMMENT='用户表';


-- 角色表
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(12) NULL DEFAULT '000000' COMMENT '租户ID',
  `code` varchar(12) NULL DEFAULT NULL COMMENT '角色编号',
  `name` varchar(45) NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) NULL DEFAULT NULL COMMENT '角色描述',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色表';

-- 用户角色关联表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户角色关联表';

-- 菜单表
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级ID',
  `code` varchar(45) NULL COMMENT '菜单编号',
  `name` varchar(45) NULL COMMENT '菜单名称',
  `path` varchar(200) NULL COMMENT '路由地址',
  `component` varchar(200) NULL COMMENT '组件路径',
  `permission` varchar(200) NULL COMMENT '权限标识',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型：1-目录，2-菜单，3-按钮',
  `icon` varchar(100) NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `hierarchy` varchar(200) NULL DEFAULT NULL COMMENT '层级结构',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '菜单表';

-- 角色菜单关联表
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_menu` (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色菜单关联表';

-- 部门表
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) NULL COMMENT '父级ID',
  `code` varchar(45) NULL DEFAULT NULL COMMENT '部门编号',
  `name` varchar(45) NULL DEFAULT NULL COMMENT '部门名称',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型：0-公司，1-部门，2-小组',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `status` int(2) NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '部门表';

-- 岗位表
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(45) NULL DEFAULT NULL COMMENT '岗位编号',
  `name` varchar(45) NULL DEFAULT NULL COMMENT '岗位名称',
  `description` varchar(200) NULL DEFAULT NULL COMMENT '岗位描述',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '岗位表';

-- 数据权限表
DROP TABLE IF EXISTS `data_scope`;
CREATE TABLE `data_scope` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单主键',
  `resource_code` varchar(255) NULL DEFAULT NULL COMMENT '资源编号',
  `scope_name` varchar(255) NULL DEFAULT NULL COMMENT '数据权限名称',
  `scope_field` varchar(255) NULL DEFAULT NULL COMMENT '数据权限字段',
  `scope_class` varchar(500) NULL DEFAULT NULL COMMENT '数据权限类名',
  `scope_column` varchar(255) NULL DEFAULT NULL COMMENT '数据权限字段',
  `scope_type` int(2) NULL DEFAULT NULL COMMENT '数据权限类型：1-全部，2-自定义，3-本部门，4-本部门及以下，5-仅本人',
  `scope_value` varchar(2000) NULL DEFAULT NULL COMMENT '数据权限值域',
  `remark` varchar(255) NULL DEFAULT NULL COMMENT '数据权限备注',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `is_deleted` int(2) NULL DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '数据权限表';

-- 角色数据权限关联表
DROP TABLE IF EXISTS `role_data_scope`;
CREATE TABLE `role_data_scope` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `data_scope_id` bigint(20) NOT NULL COMMENT '数据权限ID',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_data_scope` (`role_id`, `data_scope_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色数据权限关联表';

-- 角色部门关联表（用于自定义数据权限）
DROP TABLE IF EXISTS `role_dept`;
CREATE TABLE `role_dept` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_dept` (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '角色部门关联表'; 