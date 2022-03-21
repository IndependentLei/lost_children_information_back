package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.LogPo;
import com.lry.lostchildinfo.entity.pojo.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface LogService extends IService<Log> {
    PageVo listByPage(LogPo log);

    List<Log> allLog();
}
