package io.github.supplygo.modules.system.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据权限实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_data_scope")
public class SysDataScope {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    /**
     * 菜单主键
     */
    private Long menuId;

    /**
     * 资源编号
     */
    private String resourceCode;

    /**
     * 数据权限名称
     */
    private String scopeName;

    /**
     * 数据权限字段
     */
    private String scopeField;

    /**
     * 数据权限类名
     */
    private String scopeClass;

    /**
     * 数据权限字段
     */
    private String scopeColumn;

    /**
     * 数据权限类型：1-全部，2-自定义，3-本部门，4-本部门及以下，5-仅本人
     */
    private Integer scopeType;

    /**
     * 数据权限值域
     */
    private String scopeValue;

    /**
     * 数据权限备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 创建时间
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否已删除
     */
    @Column(isLogicDelete = true)
    private Integer isDeleted;
} 