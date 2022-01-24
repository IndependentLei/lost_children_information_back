package com.lry.lostchildinfo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SlideshowPo;
import com.lry.lostchildinfo.entity.pojo.Slideshow;
import com.lry.lostchildinfo.mapper.SlideshowMapper;
import com.lry.lostchildinfo.service.SlideshowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jdl
 * @description : 轮播图管理业务层
 * @ClassName : SlideshowServiceImpl
 * @create : 2022-01-24 17:15
 */
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow> implements SlideshowService {

    @Autowired
    SlideshowMapper slideshowMapper;

    @Override
    public PageVo listByPage(SlideshowPo slideshowPo) {
        System.out.println(slideshowPo.toString());
        IPage<Slideshow> page =  new Page<>(slideshowPo.getStartPage(),slideshowPo.getPageSize());
        IPage<Slideshow> pageList = slideshowMapper.selectPage(page,new LambdaQueryWrapper<Slideshow>()
                .like(StringUtils.isNotBlank(slideshowPo.getContext()),Slideshow::getContext,slideshowPo.getContext())
                .eq(slideshowPo.getState()!=null,Slideshow::getState,slideshowPo.getState()));

        return new PageVo(pageList.getCurrent(),pageList.getSize(),pageList.getTotal(),pageList.getRecords());
    }
}
