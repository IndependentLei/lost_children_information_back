package com.lry.lostchildinfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.HomeInfoPo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.vo.HomePageVo;
import com.lry.lostchildinfo.entity.vo.UserVo;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.lry.lostchildinfo.service.UserService;
import com.lry.lostchildinfo.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 首页控制器
 * @ClassName : HomePageController
 * @Author : jdl
 * @Create : 2022-03-30 12:51
 */
@ApiOperation("首页控制器")
@RestController
@RequestMapping("/lostchildinfo/homePage")
@Slf4j
public class HomePageController {

    @Autowired
    UserService userService;
    @Autowired
    ChildrenInfoService childrenInfoService;

    /**
     * 后台首页数据
     * @return
     */
    @OperationLog(describe = "后台首页数据")
    @PostMapping("/backInfo")
    public Result homePageInfo(@RequestBody HomeInfoPo homeInfoPo){
        // 管理员数据
        User user = userService.getById(homeInfoPo.getUserId());
        // 所有自愿者
        List<UserVo> volunteers = userService.getVolunteers();
        // 已经发布孩子的数量，已经找到的孩子的数量、未找到孩子的数量
        List<ChildrenInfo> allChildrenInfoList = childrenInfoService
                .list(new QueryWrapper<ChildrenInfo>()
                        .eq("deleted", 0));
        int noFind = (int) allChildrenInfoList.stream()
                .filter(info -> "0".equals(info.getFind()))
                .count();

        int find = allChildrenInfoList.size() - noFind;

        List<Integer> childrenInfo = new ArrayList<>(3);
        childrenInfo.add(allChildrenInfoList.size());
        childrenInfo.add(find);
        childrenInfo.add(noFind);

        HomePageVo homePage = new HomePageVo(volunteers,childrenInfo,user);

        return Result.success(homePage);
    }
}
