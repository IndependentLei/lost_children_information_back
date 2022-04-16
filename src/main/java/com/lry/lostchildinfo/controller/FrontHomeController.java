package com.lry.lostchildinfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ArticlePo;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.pojo.Article;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.entity.pojo.Slideshow;
import com.lry.lostchildinfo.entity.vo.FrontHome;
import com.lry.lostchildinfo.service.ArticleService;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.lry.lostchildinfo.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description : 首页数据
 * @ClassName : FrontHomeController
 * @Author : jdl
 * @Create : 2022-04-09 17:47
 */
@RestController
@RequestMapping("/lostchildinfo/frontHome")
public class FrontHomeController {

    @Autowired
    SlideshowService slideshowService;
    @Autowired
    ChildrenInfoService childrenInfoService;
    @Autowired
    ChildrenInfoAttachService childrenInfoAttachService;
    @Autowired
    ArticleService articleService;

    /**
     * 首页数据获取
     * @return
     */
    @OperationLog(describe = "首页数据获取")
    @GetMapping("/all")
    public Result frontHome(){
        FrontHome frontHome = new FrontHome();
        // 查询所有可用的轮播图
        List<Slideshow> list = slideshowService.list(new QueryWrapper<Slideshow>().eq("state",0));
        // 查询前五个未找到的儿童信息
        ChildrenInfoPo childrenInfoPo = new ChildrenInfoPo();
        childrenInfoPo.setStartPage(1L);
        childrenInfoPo.setPageSize(5L);
        childrenInfoPo.setFind("0");
        PageVo<ChildrenInfo> pageVo = childrenInfoService.listByPage(childrenInfoPo);
        List<Long> collect = pageVo.getList().stream()
                .map(ChildrenInfo::getChildrenId)
                .collect(Collectors.toList());

        List<String> firstPic = new ArrayList<>(15);
        for (Long childrenInfoId : collect) {
            List<ChildrenInfoAttach> attachList = childrenInfoAttachService.getBaseMapper()
                                                    .selectList(new QueryWrapper<ChildrenInfoAttach>()
                                                            .eq("children_info_id", childrenInfoId)
                                                            .orderByDesc("create_time"));
            if (attachList != null && attachList.size() != 0){
                firstPic.add(attachList.get(0).getPic());
            }else{
                firstPic.add("");
            }
        }

        // 查询文章,默认展示10篇文章
        Page<Article> articlePage = articleService.getBaseMapper()
                .selectPage(new Page<>(1, 10), null);
        List<Article> articles = articlePage.getRecords();

        frontHome.setSlideshows(list);
        frontHome.setChildrenInfos(pageVo.getList());
        frontHome.setFirstPic(firstPic);
        frontHome.setArticles(articles);

        return Result.success(frontHome);

    }
}
