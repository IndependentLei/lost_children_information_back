package com.lry.lostchildinfo.service.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.Log;
import com.lry.lostchildinfo.mapper.LogMapper;
import com.lry.lostchildinfo.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
