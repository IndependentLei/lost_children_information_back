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
    private String userCode;

    @ApiModelProperty("请求方法")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty("参数")
    @TableField("parameter_name")
    private String parameterName;

    @ApiModelProperty("执行时间")
    @TableField("execution_time")
    private Integer executionTime;

    @ApiModelProperty("请求地址")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty("请求ip地址")
    @TableField("request_ip")
    private String requestIp;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除(0为未删除,1未已删除)")
    @TableField("deleted")
    @TableLogic
    private String deleted;


}
