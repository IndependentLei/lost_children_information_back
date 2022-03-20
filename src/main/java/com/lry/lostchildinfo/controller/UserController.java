package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.Admin;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/user")
@Slf4j
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;


    /**
     * 分页查询
     * @param userpo
     * @return
     */
    @PostMapping("list")
    @OperationLog(describe = "用户分页查询")
    public Result list(@RequestBody UserPo userpo){
        return userService.selectList(userpo);
    }

    /**
     * 添加
     * @param userPo
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody UserPo userPo){
        User user = new User();
        BeanUtil.copyProperties(userPo,user);
        user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
        if (userService.save(user))
            return Result.success("添加成功");
        else return Result.error("添加失败");
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable("ids") Long ...ids){
        if (userService.removeByIds(Collections.singletonList(ids)))
            return Result.success("删除成功");
        return Result.error("删除失败");
    }

    /**
     * 更新
     * @param userPo
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody UserPo userPo){
        User user = new User();
        BeanUtil.copyProperties(userPo,user);
        if (userService.updateById(user))
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

}
