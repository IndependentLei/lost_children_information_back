package com.lry.lostchildinfo.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 父评论表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("tbl_father_comment")
@ApiModel(value = "FatherComment对象", description = "父评论表")
public class FatherComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联儿童信息表")
    @TableField("children_info_id")
    private Long childrenInfoId;

    @ApiModelProperty("评论人id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("评论人")
    @TableField("user_code")
    private String userCode;

    @ApiModelProperty("评论内容")
    @TableField("comment_content")
    private String commentContent;

    @ApiModelProperty("评论时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除(0为未删除,1为已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
