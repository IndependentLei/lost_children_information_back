package com.lry.lostchildinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SlideshowPo;
import com.lry.lostchildinfo.entity.pojo.Slideshow;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface SlideshowService extends IService<Slideshow> {
    PageVo listByPage(SlideshowPo slideshowPo);
}
