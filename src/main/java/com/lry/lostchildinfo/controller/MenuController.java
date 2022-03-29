package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.MenuPo;
import com.lry.lostchildinfo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 菜单分页查询
     * @return
     */
    @OperationLog(describe = "菜单分页查询")
    @PostMapping("list")
    public Result listByPage(@RequestBody MenuPo menuPo){
        PageVo pageVo = menuService.listByPage(menuPo);
        return Result.success(pageVo);
    }

}
