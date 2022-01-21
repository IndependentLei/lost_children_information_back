package com.lry.lostchildinfo.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 儿童信息附件表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("tbl_children_info_attach")
@ApiModel(value = "ChildrenInfoAttach对象", description = "儿童信息附件表")
public class ChildrenInfoAttachVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("关联儿童信息表")
    private Long childrenInfoId;

    @ApiModelProperty("图片地址")
    private String pic;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("是否删除(0未删除,1已删除)")
    private String deleted;


}
