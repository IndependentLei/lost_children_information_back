package com.lry.lostchildinfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.po.HomeInfoPo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.vo.EchartsVo;
import com.lry.lostchildinfo.entity.vo.HomePageVo;
import com.lry.lostchildinfo.entity.vo.UserVo;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.lry.lostchildinfo.service.UserService;
import com.lry.lostchildinfo.utils.SecurityUtil;
import com.lry.lostchildinfo.utils.TimeUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

        // 查询当前三天的新增的儿童数据
        String[] today = TimeUtils.startTime(0);
        String[] yesterday = TimeUtils.startTime(-1);
        String[] beforeYesterday = TimeUtils.startTime(-2);
        String[] bbyes = TimeUtils.startTime(-3);
        String[] bbbyes = TimeUtils.startTime(-4);

        // 当天
        ChildrenInfoPo childrenInfoPo1 = new ChildrenInfoPo();
        childrenInfoPo1.setStartTime(today[0]);
        childrenInfoPo1.setEndTime(today[1]);

        // 昨天
        ChildrenInfoPo childrenInfoPo2 = new ChildrenInfoPo();
        childrenInfoPo2.setStartTime(yesterday[0]);
        childrenInfoPo2.setEndTime(yesterday[1]);

        // 前天
        ChildrenInfoPo childrenInfoPo3 = new ChildrenInfoPo();
        childrenInfoPo3.setStartTime(beforeYesterday[0]);
        childrenInfoPo3.setEndTime(beforeYesterday[1]);

        // 大前天
        ChildrenInfoPo childrenInfoPo4 = new ChildrenInfoPo();
        childrenInfoPo4.setStartTime(bbyes[0]);
        childrenInfoPo4.setEndTime(bbyes[1]);

        // 大大前天
        ChildrenInfoPo childrenInfoPo5 = new ChildrenInfoPo();
        childrenInfoPo5.setStartTime(bbbyes[0]);
        childrenInfoPo5.setEndTime(bbbyes[1]);

        Long todayNum = childrenInfoService.findChildrenInfoNum(childrenInfoPo1);
        Long yesterdayNum = childrenInfoService.findChildrenInfoNum(childrenInfoPo2);
        Long beforeYesterdayNum = childrenInfoService.findChildrenInfoNum(childrenInfoPo3);
        Long bbYesNum = childrenInfoService.findChildrenInfoNum(childrenInfoPo4);
        Long bbbYesNum = childrenInfoService.findChildrenInfoNum(childrenInfoPo5);

        EchartsVo echartsVo = new EchartsVo();

        List<Long> threeSum = new ArrayList<>(5);
        threeSum.add(todayNum);
        threeSum.add(yesterdayNum);
        threeSum.add(beforeYesterdayNum);
        threeSum.add(bbYesNum);
        threeSum.add(bbbYesNum);
        echartsVo.setThreeSum(threeSum);

        List<String> threeDate = new ArrayList<>(5);
        threeDate.add(TimeUtils.dateFormat(0));
        threeDate.add(TimeUtils.dateFormat(-1));
        threeDate.add(TimeUtils.dateFormat(-2));
        threeDate.add(TimeUtils.dateFormat(-3));
        threeDate.add(TimeUtils.dateFormat(-4));

        echartsVo.setThreeDate(threeDate);


        HomePageVo homePage = new HomePageVo(volunteers,childrenInfo,user,echartsVo);

        return Result.success(homePage);
    }
}
