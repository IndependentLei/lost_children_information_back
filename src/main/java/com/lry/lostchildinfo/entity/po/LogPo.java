package com.lry.lostchildinfo.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogPo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String userCode;

    private String description;

    private String methodName;

    private String parameterName;

    private Long startExecutionTime;
    private Long endExecutionTime;

    private String requestUrl;

    private String requestIp;

    private LocalDateTime createTime;

    private String deleted;

    private Long startPage;
    private Long pageSize;


}
