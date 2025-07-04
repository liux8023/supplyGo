package io.github.supplygo.modules.access.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 角色实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("role")
public class Role {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 角色编号
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 数据范围：1-全部，2-自定义，3-本部门，4-本部门及以下，5-仅本人
     */
    private Integer dataScope;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private Long createUser;

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