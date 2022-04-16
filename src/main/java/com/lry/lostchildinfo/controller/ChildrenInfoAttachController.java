package com.lry.lostchildinfo.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    @Autowired
    ChildrenInfoService childrenInfoService;

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

    /**
     * 根据身份证获取儿童附件
     * @param idCard
     * @return
     */
    @OperationLog(describe = "根据身份证获取儿童附件")
    @GetMapping("/getAttachByChildIdCard/{idCard}")
    public Result getAttachByChildIdCard(@PathVariable("idCard") Long idCard){
        ChildrenInfo childInfo = childrenInfoService.getOne(Wrappers.<ChildrenInfo>query()
                .eq("id_card", idCard));
        if (ObjectUtil.isNotNull(childInfo)) {
            Long childrenId = childInfo.getChildrenId();
            List<ChildrenInfoAttach> attachList = childrenInfoAttachService.list(Wrappers.<ChildrenInfoAttach>query().eq("children_info_id", childrenId));
            return Result.success(attachList);
        }else{
            return Result.success(new ArrayList<ChildrenInfoAttach>());
        }
    }

    /**
     * 根据id删除附件
     * @param id
     * @return
     */
    @OperationLog(describe = "根据id删除附件")
    @DeleteMapping("{id}")
    public Result delAttach(@PathVariable("id") Long ...id){
        boolean b = childrenInfoAttachService.removeByIds(Arrays.asList(id));
        return b? Result.success("删除成功"):Result.error("删除失败");
    }

}
