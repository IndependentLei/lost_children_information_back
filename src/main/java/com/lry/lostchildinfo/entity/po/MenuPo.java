package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long menuId;

    @ApiModelProperty("菜单名")
    private String label;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("路由")
    private String menuPath;

    @ApiModelProperty("组件名")
    private String component;

    @ApiModelProperty("路由名")
    private String menuName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
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
