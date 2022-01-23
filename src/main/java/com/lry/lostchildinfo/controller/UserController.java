package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;

    @PostMapping("add")
    public Result add(@RequestBody UserPo userPo){
        User user = new User();
        BeanUtil.copyProperties(userPo,user);
        user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
        if (userService.save(user))
            return Result.success("添加成功");
        else return Result.error("添加失败");
    }

}
