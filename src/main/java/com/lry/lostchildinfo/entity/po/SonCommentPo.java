package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 子评论表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SonCommentPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("关联父评论表")
    private Long fatherCommentId;

    @ApiModelProperty("子评论人id")
    private Long userId;

    @ApiModelProperty("子评论人")
    private String userCode;

    @ApiModelProperty("被回复人的id")
    private Long replayId;

    @ApiModelProperty("回复内容")
    private String replayContext;

    @ApiModelProperty("被回复人")
    private String replayCode;

    @ApiModelProperty("是否删除(0为删除,1已删除）")
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
