package com.lry.lostchildinfo.mapper;

import com.lry.lostchildinfo.entity.pojo.Slideshow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Mapper
@Repository
public interface SlideshowMapper extends BaseMapper<Slideshow> {

}
