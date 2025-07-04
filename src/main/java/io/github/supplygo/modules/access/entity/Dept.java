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
 * 部门实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("dept")
public class Dept {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门名称
     */
    private String name;

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