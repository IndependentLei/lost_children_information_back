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
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long userId;

    @ApiModelProperty("qq登录(唯一)")
    private String qqId;

    @ApiModelProperty("用户账号")
    private String userCode;

    @ApiModelProperty("用户密码")
    private String userPwd;

    @ApiModelProperty("用户状态（0为正常，1为禁止，2为禁言）")
    private String state;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("用户性别（0是女，1是男，2为未知）")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("用户头像")
    private String avatarImg;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("修改人")
    private String updateName;

    @ApiModelProperty("修改人id")
    private Long updateId;

    @ApiModelProperty("删除状态(0为未删除,1为已删除)")
    private String deleted;

    private String roleType;


}
