package com.lry.lostchildinfo.entity.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 父评论表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatherCommentPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("关联儿童信息表")
    private Long childrenInfoId;

    @ApiModelProperty("评论人id")
    private Long userId;

    @ApiModelProperty("评论人")
    private String userCode;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("评论时间")
    private LocalDateTime createTime;

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

    private String startTime;

    private String endTime;

    private String timeToString;
}
