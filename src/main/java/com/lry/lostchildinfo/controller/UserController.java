package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.Admin;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.entity.vo.UserVo;
import com.lry.lostchildinfo.service.UserRoleService;
import com.lry.lostchildinfo.service.UserService;
import com.lry.lostchildinfo.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
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
    @Autowired
    UserRoleService userRoleService;


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
    @OperationLog(describe = "添加用户")
    @Transactional(rollbackFor = {RuntimeException.class})
    public Result add(@RequestBody UserPo userPo){
        User user = new User();
        BeanUtil.copyProperties(userPo,user);
        User userByName = userService.getUserByName(userPo.getUserCode());
        if (ObjectUtil.isEmpty(userByName)) {
            user.setUserPwd(bCryptPasswordEncoder.encode(user.getUserPwd()));
            user.setCreateId(SecurityUtil.getCurrentUser().getUserId());
            user.setCreateName(SecurityUtil.getCurrentUser().getCreateName());
            if (userService.save(user)) {
                User user1 = userService.getUserByName(userPo.getUserCode());
                UserRole userRole = new UserRole();
                userRole.setUserId(user1.getUserId());
                userRole.setRoleId(Long.valueOf(userPo.getRoleType()));
                userRoleService.save(userRole);
                return Result.success("添加成功");
            } else return Result.error("添加失败");
        }
        return Result.error("用户名重复");
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    @OperationLog(describe = "删除用户")
    @Transactional(rollbackFor = {RuntimeException.class})
    public Result delete(@PathVariable(value = "ids" ,required = false) Long ...ids){
        boolean flag = false;
        for (Long id : ids) {
            if (userService.remove(new QueryWrapper<User>().eq("user_id",id))){
                if(!userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id)))
                    flag = true;
            }else {
                flag = true;
            }
        }
        if (flag)
            return Result.error("删除失败");
        return Result.success("删除成功");
    }

    /**
     * 更新
     * @param userPo
     * @return
     */
    @PostMapping("update")
    @OperationLog(describe = "更新用户信息")
    @Transactional(rollbackFor = {RuntimeException.class})
    public Result update(@RequestBody UserPo userPo){
        User user = new User();
        BeanUtil.copyProperties(userPo,user);
        boolean update = userService.update(user,new LambdaQueryWrapper<User>().eq(ObjectUtil.isNotEmpty(userPo.getUserId()), User::getUserId, userPo.getUserId()));
        if (update) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userPo.getUserId());
            userRole.setRoleId(Long.valueOf(userPo.getRoleType()));
            boolean userRoleUpdate = userRoleService.update(userRole, new LambdaQueryWrapper<UserRole>().eq(ObjectUtil.isNotEmpty(userPo.getUserId()), UserRole::getUserId, userPo.getUserId()));
            if (userRoleUpdate)
                return Result.success("更新成功");
            else return Result.error("更新失败");
        }
        return Result.error("更新失败");
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @OperationLog(describe = "根据id获取用户")
    public Result getUserById(@PathVariable("id") Long id){
        UserVo userVo = userService.getUserById(id);
        return Result.success(userVo);
    }

    /**
     * 修改密码、重置密码
     * @return
     */
    @PostMapping("changePwd")
    @OperationLog(describe = "修改密码、重置密码")
    public Result changePwd(@RequestBody UserPo userPo){
        if (StringUtils.isNotBlank(userPo.getUserPwd())){
            String encode = bCryptPasswordEncoder.encode(userPo.getUserPwd());
            User user = new User();
            user.setUserPwd(encode);
            user.setUserId(userPo.getUserId());

            boolean save = userService.save(user);
            if ( save ){
                return Result.success("修改密码成功");
            }else
                return Result.error("修改密码失败");
        }else{
            // 重置的密码为 123456
            String encode = bCryptPasswordEncoder.encode("123456");
            User user = new User();
            user.setUserPwd(encode);
            user.setUserId(userPo.getUserId());

            boolean save = userService.save(user);
            if ( save ){
                return Result.success("重置密码成功");
            }else
                return Result.error("重置密码失败");

        }

    }

}
