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
 * 系统日志
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Getter
@Setter
@TableName("sys_log")
@ApiModel(value = "Log对象", description = "系统日志")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("账号")
    @TableField("user_code")
    @Excel(name = "账号")
    private String userCode;

    @ApiModelProperty("描述")
    @TableField("description")
    @Excel(name = "描述")
    private String description;

    @ApiModelProperty("请求方法")
    @TableField("method_name")
    @Excel(name = "请求方法")
    private String methodName;

    @ApiModelProperty("参数")
    @TableField("parameter_name")
    @Excel(name = "参数")
    private String parameterName;

    @ApiModelProperty("执行时间")
    @TableField("execution_time")
    @Excel(name = "执行时间")
    private Long executionTime;

    @ApiModelProperty("请求地址")
    @TableField("request_url")
    @Excel(name = "请求地址")
    private String requestUrl;

    @ApiModelProperty("请求ip地址")
    @TableField("request_ip")
    @Excel(name = "请求ip地址")
    private String requestIp;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @Excel(name = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除(0为未删除,1未已删除)")
    @TableField("deleted")
    @TableLogic
    @Excel(name = "是否删除(0为未删除,1未已删除)")
    private String deleted;


}
