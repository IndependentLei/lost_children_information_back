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
 * 儿童信息表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("tbl_children_info")
@ApiModel(value = "ChildrenInfo对象", description = "儿童信息表")
public class ChildrenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "children_id", type = IdType.AUTO)
    private Long childrenId;

    @ApiModelProperty("关联用户表")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("儿童姓名")
    @TableField("children_name")
    private String childrenName;

    @ApiModelProperty("儿童性别（0为女，1为男）")
    @TableField("sex")
    private String sex;

    @ApiModelProperty("儿童年纪")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("丢失位置")
    @TableField("lost_location")
    private String lostLocation;

    @ApiModelProperty("丢失时间")
    @TableField("lost_time")
    private LocalDateTime lostTime;

    @ApiModelProperty("儿童特征")
    @TableField("children_feature")
    private String childrenFeature;

    @ApiModelProperty("创建人")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    @TableField("update_name")
    private String updateName;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除（0为未删除,1为已删除）")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}