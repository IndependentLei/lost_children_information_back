package com.lry.lostchildinfo.entity.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("sys_user")
@ToString
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty("qq登录(唯一)")
    @TableField("qq_id")
    private String qqId;

    @ApiModelProperty("用户账号")
    @TableField("user_code")
    @Excel(name = "用户账号")
    private String userCode;

    @ApiModelProperty("用户密码")
    @TableField("user_pwd")
    private String userPwd;

    @ApiModelProperty("用户状态（0为正常，1为禁止，2为禁言）")
    @TableField("state")
    @Excel(name = "用户状态（0为正常，1为禁止，2为禁言）")
    private String state;

    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("用户性别(0为未删除,1为已删除)")
    @TableField("sex")
    @Excel(name = "用户性别(0为未删除,1为已删除)")
    private String sex;

    @ApiModelProperty("年龄")
    @TableField("age")
    @Excel(name = "用户年龄")
    private Integer age;

    @ApiModelProperty("用户头像")
    @TableField("avatar_img")
    @Excel(name = "用户头像地址")
    private String avatarImg;

    @ApiModelProperty("创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    @TableField(value = "update_name",fill = FieldFill.INSERT_UPDATE)
    private String updateName;

    @ApiModelProperty("修改人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("删除状态(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    @Excel(name = "删除状态(0为未删除,1为已删除)")
    private String deleted;


}
