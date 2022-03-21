package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.LogPo;
import com.lry.lostchildinfo.entity.pojo.Log;
import com.lry.lostchildinfo.service.LogService;
import com.lry.lostchildinfo.utils.ExcelUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/log")
@Slf4j
public class LogController {

    @Autowired
    LogService logService;

    /**
     * 分页查找
     * @param log
     * @return
     */
    @OperationLog(describe = "日志分页查找")
    @PostMapping("/list")
    public Result list(@RequestBody LogPo log){
        PageVo pageVo = logService.listByPage(log);
        return Result.success(pageVo);
    }

    /**
     * 添加日志
     * @return
     */
    @OperationLog(describe = "添加日志")
    @PostMapping("/add")
    public Result add(){
        return Result.success();
    }

    /**
     * 修改日志
     * @return
     */
    @OperationLog(describe = "修改日志")
    @PostMapping("/update")
    public Result update(){
        return Result.success();
    }

    /**
     * 删除日志
     * @return
     */
    @OperationLog(describe = "删除日志")
    @DeleteMapping("{ids}")
    public Result delete(@PathVariable("ids") Long ...ids){
        boolean b = logService.removeByIds(Arrays.asList(ids));
        if (b){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * 导入日志
     * @param file
     * @return
     */
    @OperationLog(describe = "导入日志")
    @PostMapping("/import")
    public Result importLog(MultipartFile file){
        return Result.success("导入成功");
    }

    /**
     * 导出日志
     * @return
     */
    @OperationLog(describe = "导出日志")
    @GetMapping("/export")
    public void exportLog(HttpServletResponse response){
        List<Log> logs = logService.allLog();
        ExcelUtil.exportExcel(response,Log.class,logs,"日志表");
    }
}
