package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ArticlePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章主键")
    private Long id;

    @ApiModelProperty("发布者id,关联user表")
    private Long userId;

    @ApiModelProperty("发布者账号")
    private String userCode;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章图片")
    private String picUrl;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("文件内容")
    private String content;

    @ApiModelProperty("更新人账号")
    private String updateCode;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    private String deleted;

    private Long startPage;
    private Long pageSize;

    private String startTime;
    private String endTime;

}
