package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.LogPo;
import com.lry.lostchildinfo.entity.pojo.Log;
import com.lry.lostchildinfo.mapper.LogMapper;
import com.lry.lostchildinfo.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public PageVo listByPage(LogPo log) {
        Page<Log> page = new Page<>(log.getStartPage(),log.getPageSize());
        Page<Log> pageList = logMapper.selectPage(page, new LambdaQueryWrapper<Log>()
                .like(StringUtils.isNotEmpty(log.getUserCode())
                        , Log::getUserCode
                        , log.getUserCode())
                .between(ObjectUtil.isNotEmpty(log.getStartExecutionTime())
                        , Log::getExecutionTime
                        , log.getStartExecutionTime()
                        , log.getEndExecutionTime())
                .orderByDesc(Log::getCreateTime));

        return new PageVo(pageList.getCurrent()
                ,pageList.getSize()
                ,pageList.getTotal()
                ,pageList.getRecords());
    }
}
