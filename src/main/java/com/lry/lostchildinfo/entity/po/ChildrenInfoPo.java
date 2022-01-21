package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 儿童信息表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long childrenId;

    @ApiModelProperty("关联用户表")
    private Long userId;

    @ApiModelProperty("儿童姓名")
    private String childrenName;

    @ApiModelProperty("儿童性别（0为女，1为男）")
    private String sex;

    @ApiModelProperty("儿童年纪")
    private Integer age;

    @ApiModelProperty("丢失位置")
    private String lostLocation;

    @ApiModelProperty("丢失时间")
    private LocalDateTime lostTime;

    @ApiModelProperty("儿童特征")
    private String childrenFeature;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String updateName;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除（0为未删除,1为已删除）")
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
