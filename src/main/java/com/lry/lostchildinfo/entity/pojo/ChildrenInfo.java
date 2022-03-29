package com.lry.lostchildinfo.entity.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;

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

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty("关联用户表")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("儿童姓名")
    @TableField("children_name")
    @Excel(name = "儿童姓名")
    private String childrenName;

    @ApiModelProperty("儿童性别（0为女，1为男）")
    @TableField("sex")
    @Excel(name = "儿童性别（0为女，1为男）")
    private String sex;

    @ApiModelProperty("儿童年纪")
    @TableField("age")
    @Excel(name = "儿童年纪")
    private Integer age;

    @ApiModelProperty("联系电话")
    @TableField("contact_phone")
    @Excel(name = "联系电话")
    private String contactPhone;

    @ApiModelProperty("丢失位置")
    @TableField("lost_location")
    @Excel(name = "丢失位置")
    private String lostLocation;

    @ApiModelProperty("丢失时间")
    @TableField("lost_time")
    @Excel(name = "丢失时间")
    private LocalDateTime lostTime;

    @ApiModelProperty("儿童特征")
    @TableField("children_feature")
    @Excel(name = "儿童特征")
    private String childrenFeature;

    @ApiModelProperty("创建人")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    @TableField("update_name")
    private String updateName;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除（0为未删除,1为已删除）")
    @TableField("deleted")
    @TableLogic
    @Excel(name = "是否删除（0为未删除,1为已删除）")
    private String deleted;

    @ApiModelProperty("是否找到（0为未找到,1为已找到）")
    @TableField("find")
    @Excel(name = "是否找到（0为未找到,1为已找到）")
    private String find;


}
