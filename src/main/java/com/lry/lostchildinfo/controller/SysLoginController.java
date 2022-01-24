package com.lry.lostchildinfo.controller;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : jdl
 * @description : 用户登录前端控制器
 * @ClassName : SysLoginController
 * @create : 2022-01-23 13:35
 */
@RestController
@Slf4j
public class SysLoginController {

    @Resource
    LoginService loginService;

    @PostMapping("/login")
    public Result login(LoginUser loginUser){
//        log.info("账号为:"+loginUser.(),loginUser.getUserPwd())
        return Result.success("成功");
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/test")
    public Result test(){
        return Result.success("11111");
    }

    @PreAuthorize("hasRole('test')")
    @GetMapping("/test1")
    public Result test1(){
        return Result.success("11111");
    }









    //-----------------------------------------------------------------------------------------


//    @GetMapping("list")
//    @ApiOperation(value="管理员实体", notes="分页查找", produces="application/json")
//    public Result list(@Validated @RequestBody AdminPo adminPo){
//        return adminService.selectList(adminPo);
//    }
//
//    @PostMapping("login")
//    public Result login( AdminPo adminPo){
//        log.info("进来了");
//        Admin admin = adminService.getOne(new LambdaQueryWrapper<Admin>()
//                .eq(Admin::getUserCode, adminPo.getUserCode()).eq(Admin::getUserPwd,adminPo.getUserPwd()));
//        if(ObjectUtils.isEmpty(admin))
//            return Result.error("没找到");
//        return Result.success("找到了");
//    }
//
//    @PostMapping("add")
//    public Result add(@Validated @RequestBody AdminPo adminPo){
//        Admin adminOne = adminService.getOne(new QueryWrapper<Admin>().eq("user_code",adminPo.getUserCode()));
//        if (!ObjectUtils.isEmpty(adminOne)){
//            return Result.error("账号已存在");
//        }
//        log.info("增加操作");
//        Admin  admin = new Admin();
//        BeanUtil.copyProperties(adminPo,admin);
//        if (adminService.save(admin))
//            return Result.success("插入成功");
//        return Result.error("插入失败");
//    }
//
//    @DeleteMapping("/{ids}")
//    public Result delete(@PathVariable("ids") Long ...ids){
//        if (adminService.removeByIds(Arrays.asList(ids)))
//            return Result.success("删除成功");
//        return Result.error("删除失败");
//    }
//
//    @PostMapping("update")
//    public Result update(@RequestBody AdminPo adminPo){
//        Admin admin = new Admin();
//        BeanUtil.copyProperties(adminPo,admin);
//        if (adminService.updateById(admin))
//            return Result.success("更新成功");
//        return Result.error("更新失败");
//    }
}
