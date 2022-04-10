package com.lry.lostchildinfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 儿童信息附件表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/childrenInfoAttach")
public class ChildrenInfoAttachController {

    @Autowired
    ChildrenInfoAttachService childrenInfoAttachService;

    @OperationLog(describe = "更具儿童信息id查询附件")
    @GetMapping("{id}")
    public Result getAttachByInfoId(@PathVariable("id") Long id){
        List<ChildrenInfoAttach> attachList = childrenInfoAttachService
                                                        .getBaseMapper()
                                                        .selectList(new QueryWrapper<ChildrenInfoAttach>()
                                                                .eq("children_info_id", id));
        return Result.success(attachList);
    }

    /**
     * 根据儿童信息id查询第一张图片
     * @param ids
     * @return
     */
    @OperationLog(describe = "根据儿童信息id查询第一张图片")
    @GetMapping("/firstPic/{ids}")
    public Result getFistAttachById(@PathVariable("ids") String[] ids){
        List<String> picList = new ArrayList<>(15);
        for(String id : ids){
            ChildrenInfoAttach attach = childrenInfoAttachService.list(new QueryWrapper<ChildrenInfoAttach>().eq("children_info_id", id)).get(0);
            if (attach == null){
                picList.add("");
            }else {
                picList.add(attach.getPic());
            }
        }
        return Result.success(picList);
    }

}
