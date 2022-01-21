package com.lry.lostchildinfo.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色类型")
    private String roleType;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("更新人")
    private String updateName;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    private String deleted;


}
