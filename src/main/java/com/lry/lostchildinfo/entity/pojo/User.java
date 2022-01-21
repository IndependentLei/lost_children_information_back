package com.lry.lostchildinfo.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 用户表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("sys_user")
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
    private String userCode;

    @ApiModelProperty("用户密码")
    @TableField("user_pwd")
    private String userPwd;

    @ApiModelProperty("用户状态（0为正常，1为禁止，2为禁言）")
    @TableField("state")
    private String state;

    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("用户性别（0是女，1是男，2为未知）")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("用户头像")
    @TableField("avatar_img")
    private String avatarImg;

    @ApiModelProperty("创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    @TableField("update_name")
    private String updateName;

    @ApiModelProperty("修改人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("删除状态(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
