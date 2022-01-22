package com.lry.lostchildinfo.mapper;

import com.lry.lostchildinfo.entity.pojo.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Mapper
@Repository
public interface LogMapper extends BaseMapper<Log> {

}
