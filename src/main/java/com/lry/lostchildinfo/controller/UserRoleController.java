package com.lry.lostchildinfo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.service.RoleService;
import com.lry.lostchildinfo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表和角色表关联 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/userrole")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;

    @OperationLog(describe = "更具用户id获取角色id")
    @GetMapping("{userId}")
    public Result getRoleTypeByUserId(@PathVariable("userId") Long userId){
        UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", userId));
        Role role = roleService.getOne(new QueryWrapper<Role>().eq("role_id", userRole.getRoleId()));

        return Result.success(role);
    }

}
