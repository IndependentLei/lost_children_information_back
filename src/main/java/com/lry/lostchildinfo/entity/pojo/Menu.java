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
 * 菜单表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty("菜单名")
    @TableField("label")
    private String label;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("路由")
    @TableField("menu_path")
    private String menuPath;

    @ApiModelProperty("组件名")
    @TableField("component")
    private String component;

    @ApiModelProperty("路由名")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
