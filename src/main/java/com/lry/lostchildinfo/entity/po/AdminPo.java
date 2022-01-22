package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long userId;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("性别(0为女,1为男,2为未知)")
    private String sex;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("账号")
    private String userCode;

    @ApiModelProperty("密码")
    private String userPwd;

    @ApiModelProperty("头像")
    private String avatarImg;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("更新人")
    private String updateName;

    @ApiModelProperty("账号状态(0为正常,1为禁号，2为禁言)")
    private String state;

    @ApiModelProperty("是否删除(0为未删除，1为已删除)")
    private String deleted;

    /**
     * 开始页数
     */
    private Long startPage;
    /**
     * 页面容量
     */
    private Long pageSize;

}
