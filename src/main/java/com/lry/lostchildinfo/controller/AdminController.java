package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.pojo.Admin;
import com.lry.lostchildinfo.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Slf4j
@RestController
@RequestMapping("/lostchildinfo/admin")
@Api(description = "管理员前端控制器")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping("list")
    @ApiOperation(value="管理员实体", notes="分页查找", produces="application/json")
    public Result list(@Validated @RequestBody AdminPo adminPo){
        return adminService.selectList(adminPo);
    }

    @PostMapping("login")
    public Result login( AdminPo adminPo){
        log.info("进来了");
        Admin admin = adminService.getOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUserCode, adminPo.getUserCode()).eq(Admin::getUserPwd,adminPo.getUserPwd()));
        if(ObjectUtils.isEmpty(admin))
            return Result.error("没找到");
        return Result.success("找到了");
    }

    @PostMapping("add")
    public Result add(@Validated @RequestBody AdminPo adminPo){
        Admin adminOne = adminService.getOne(new QueryWrapper<Admin>().eq("user_code",adminPo.getUserCode()));
        if (!ObjectUtils.isEmpty(adminOne)){
            return Result.error("账号已存在");
        }
        log.info("增加操作");
        Admin  admin = new Admin();
        BeanUtil.copyProperties(adminPo,admin);
        if (adminService.save(admin))
            return Result.success("插入成功");
        return Result.error("插入失败");
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable("ids") Long ...ids){
        if (adminService.removeByIds(Arrays.asList(ids)))
            return Result.success("删除成功");
        return Result.error("删除失败");
    }

    @PostMapping("update")
    public Result update(@RequestBody AdminPo adminPo){
        Admin admin = new Admin();
        BeanUtil.copyProperties(adminPo,admin);
        if (adminService.updateById(admin))
            return Result.success("更新成功");
        return Result.error("更新失败");
    }
}
