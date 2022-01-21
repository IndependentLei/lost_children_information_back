package com.lry.lostchildinfo.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class LogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("账号")
    private String userCode;

    @ApiModelProperty("请求方法")
    private String methodName;

    @ApiModelProperty("参数")
    private String parameterName;

    @ApiModelProperty("执行时间")
    private Integer executionTime;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("请求ip地址")
    private String requestIp;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除(0为未删除,1未已删除)")
    private String deleted;


}
