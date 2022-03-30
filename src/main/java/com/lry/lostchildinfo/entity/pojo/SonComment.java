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
 * 子评论表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("tbl_son_comment")
@ApiModel(value = "SonComment对象", description = "子评论表")
public class SonComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联父评论表")
    @TableField("father_comment_id")
    private Long fatherCommentId;

    @ApiModelProperty("子评论人id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("子评论人")
    @TableField("user_code")
    @Excel(name = "子评论账号")
    private String userCode;

    @ApiModelProperty("被回复人的id")
    @TableField("replay_id")
    private Long replayId;

    @ApiModelProperty("回复时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Excel(name = "回复时间")
    private LocalDateTime createTime;

    @ApiModelProperty("回复内容")
    @TableField("replay_context")
    @Excel(name = "回复内容")
    private String replayContext;

    @ApiModelProperty("被回复人")
    @TableField("replay_code")
    @Excel(name = "被回复人")
    private String replayCode;

    @ApiModelProperty("是否删除(0为删除,1已删除）")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
