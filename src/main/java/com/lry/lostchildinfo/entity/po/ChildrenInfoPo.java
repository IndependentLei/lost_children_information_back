package com.lry.lostchildinfo.entity.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 儿童信息表
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long childrenId;

    @ApiModelProperty("关联用户表")
    private Long userId;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("儿童姓名")
    private String childrenName;

    @ApiModelProperty("儿童性别（0为女，1为男）")
    private String sex;

    @ApiModelProperty("儿童年纪")
    private Integer age;

    @ApiModelProperty("丢失位置")
    private String lostLocation;

    @ApiModelProperty("丢失时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lostTime;

    @ApiModelProperty("儿童特征")
    private String childrenFeature;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private String updateName;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除（0为未删除,1为已删除）")
    private String deleted;

    @ApiModelProperty("是否找到（0为未找到,1为已找到）")
    private String find;

    /**
     * 开始页数
     */
    private Long startPage;
    /**
     * 页面容量
     */
    private Long pageSize;


    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 附件列表
     */
    private List<ChildrenInfoAttach> childrenInfoAttachList;

    /**
     * 图片列表
     */
    private List<String> childrenInfoAttach;

}
