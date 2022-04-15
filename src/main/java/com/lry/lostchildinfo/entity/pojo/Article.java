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
 *
 * </p>
 *
 * @author jdl
 * @since 2022-04-14
 */
@Getter
@Setter
@TableName("tbl_article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("发布者id,关联user表")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("发布者账号")
    @TableField("user_code")
    @Excel(name = "发布者账号")
    private String userCode;

    @ApiModelProperty("文章标题")
    @TableField("title")
    @Excel(name = "文章标题")
    private String title;

    @ApiModelProperty("文章图片")
    @TableField("pic_url")
    @Excel(name = "文章图片")
    private String picUrl;

    @ApiModelProperty("发布时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Excel(name = "发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("文件内容")
    @TableField("content")
    @Excel(name = "文件内容")
    private String content;

    @ApiModelProperty("更新人账号")
    @TableField(value = "update_code",fill = FieldFill.INSERT_UPDATE)
    private String updateCode;

    @ApiModelProperty("更新人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
