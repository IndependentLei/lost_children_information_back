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
public class ChildrenInfoAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联儿童信息表")
    @TableField("children_info_id")
    private Long childrenInfoId;

    @ApiModelProperty("图片地址")
    @TableField("pic")
    private String pic;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人id")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("是否删除(0未删除,1已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
