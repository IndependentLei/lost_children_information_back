package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SlideshowPo;
import com.lry.lostchildinfo.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/slideshow")
public class SlideshowController {

    @Autowired
    SlideshowService slideshowService;

    @PostMapping("list")
    public Result list(@RequestBody SlideshowPo slideshowPo){
        if (slideshowPo.getState().equals(""))
            slideshowPo.setState(null);
        if (slideshowPo.getStartPage() == null || slideshowPo.getPageSize() == null){
            return Result.error("参数出错");
        }
        PageVo pageVo = slideshowService.listByPage(slideshowPo);
        System.out.println(pageVo.getList().toString());
        return Result.success(pageVo);
    }

}
