package com.lry.lostchildinfo.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId("role_id")
    private Long roleId;

    @ApiModelProperty("角色名")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("角色类型")
    @TableField("role_type")
    private String roleType;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("创建人")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Long updateTime;

    @ApiModelProperty("更新人")
    @TableField("update_name")
    private String updateName;

    @ApiModelProperty("更新人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
