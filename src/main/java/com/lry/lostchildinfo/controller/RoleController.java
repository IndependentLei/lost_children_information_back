package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.RolePo;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.service.RoleService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import com.lry.lostchildinfo.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 角色分页查询
     * @param rolePo
     * @return
     */
    @OperationLog(describe = "角色分页查询")
    @PostMapping("/list")
    public Result list(@RequestBody RolePo rolePo){
        PageVo pageVo = roleService.listByPage(rolePo);
        return Result.success(pageVo);
    }

    /**
     * 未删除的角色
     * @return
     */
    @OperationLog(describe = "未删除的角色")
    @GetMapping("noDeleteRole")
    public Result noDeleteRole(){
        return Result.success(roleService.list());
    }

    /**
     * 添加角色
     * @return
     */
    @OperationLog(describe = "添加角色")
    @PostMapping("/add")
    public Result add(@RequestBody RolePo rolePo){
        // 去前后空格
        String roleValue = rolePo.getRoleValue().trim();
        Role hasRole = roleService.getOne(new QueryWrapper<Role>().eq("role_value", roleValue));
        if (ObjectUtil.isEmpty(hasRole)) {
            Role role = new Role();
            BeanUtil.copyProperties(rolePo, role);
            int count  = roleService
                    .listAll().size();
            role.setRoleId(count+1L);
            role.setRoleType(String.valueOf(count+1));
            role.setCreateId(SecurityUtil.getCurrentUser().getUserId());
            role.setCreateName(SecurityUtil.getCurrentUser().getUserCode());
            if(roleService.save(role)){
                return Result.success("添加成功");
            }else{
                return Result.error("添加失败");
            }
        }else {
            return Result.error("已存在该角色");
        }
    }


    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    @OperationLog(describe = "删除角色")
    @DeleteMapping("/{ids}")
    public Result del(@PathVariable("ids") Long ...roleIds){
        // 判断当前角色下有人，如果有，不可以删除
        if (roleService.delByIds(roleIds)){
            return Result.success("删除成功");
        }else {
            return Result.error("当前角色存在用户,禁止删除");
        }
    }

    /**
     * 修改角色信息
     * @param rolePo
     * @return
     */
    @OperationLog(describe = "修改角色信息")
    @PostMapping("/update")
    public Result update(@RequestBody RolePo rolePo){
        Role role = new Role();
        BeanUtil.copyProperties(rolePo,role);
        role.setUpdateId(SecurityUtil.getCurrentUser().getUserId());
        role.setUpdateName(SecurityUtil.getCurrentUser().getUserCode());
        boolean flag = roleService.updateById(role);
        if ( flag ){
            return Result.success("修改成功");
        }else{
            return Result.error("修改失败");
        }

    }


    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    @OperationLog(describe = "根据id查询角色信息")
    @GetMapping("/{id}")
    public Result getRoleById(@PathVariable Long id){
        Role role = roleService.getById(id);
        return Result.success(role);
    }

    /**
     * 导入角色
     * @param file
     * @return
     */
    @OperationLog(describe = "导入角色")
    @PostMapping("/import")
    public Result importRole(MultipartFile file){
        //TODO...
        return Result.success("导入成功");
    }

    /**
     * 导出所有角色
     * @param response
     */
    @OperationLog(describe = "导出所有角色")
    @GetMapping("/export")
    public void exportRole(HttpServletResponse response){
        List<Role> list = roleService.list();
        ExcelUtil.exportExcel(response,Role.class,list,"角色表");
    }






}
