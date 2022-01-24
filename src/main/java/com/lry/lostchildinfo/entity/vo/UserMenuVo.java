package com.lry.lostchildinfo.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : jdl
 * @description : 用户关联菜单表
 * @ClassName : AdminMenu
 * @create : 2022-01-21 20:20
 */
@Getter
@Setter
@TableName("sys_user_menu")
@ApiModel(value = "user_menu对象", description = "人员管理菜单表")
public class UserMenuVo implements Serializable {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("菜单id")
    private Long menuId;
}
