package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SonCommentPo;
import com.lry.lostchildinfo.entity.pojo.SonComment;
import com.lry.lostchildinfo.service.SonCommentService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 子评论表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/sonComment")
@Slf4j
public class SonCommentController {

    @Autowired
    SonCommentService sonCommentService;
    /**
     * 子评论分页查询
     * @return
     */
    @OperationLog(describe = "子评论分页查询")
    @PostMapping("list")
    public Result listByPage(@RequestBody SonCommentPo sonCommentPo){
        PageVo pageVo = sonCommentService.listByPage(sonCommentPo);
        return Result.success(pageVo);
    }

    /**
     * 添加子评论
     * @param sonCommentPo
     * @return
     */
    @OperationLog(describe = "添加子评论")
    @PostMapping("add")
    public Result add(@RequestBody SonCommentPo sonCommentPo){
        return sonCommentService.add(sonCommentPo);
    }

    /**
     * 删除子评论
     * @param ids
     * @return
     */
    @OperationLog(describe = "删除子评论")
    @DeleteMapping("{ids}")
    public Result del(@PathVariable("ids") Long ...ids){
        return sonCommentService.del(ids);
    }

    /**
     * 更新子评论
     * @param sonCommentPo
     * @return
     */
    @OperationLog(describe = "更新子评论")
    @PostMapping("update")
    public Result update(@RequestBody SonCommentPo sonCommentPo){
        return Result.success();
    }

    /**
     * 导入子评论
     * @param file
     * @return
     */
    @OperationLog(describe = "导入子评论")
    @PostMapping("import")
    public Result importSonComment(MultipartFile file){
        return Result.success();
    }

    /**
     * 导出子评论
     * @param response
     */
    @OperationLog(describe = "导出子评论")
    @GetMapping("export")
    public void exportSonComment(HttpServletResponse response){
        List<SonComment> list = sonCommentService.list();
        ExcelUtil.exportExcel(response,SonComment.class,list,"子评论表");
    }

}
