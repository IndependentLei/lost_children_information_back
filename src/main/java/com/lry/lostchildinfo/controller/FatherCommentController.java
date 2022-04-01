package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.FatherCommentPo;
import com.lry.lostchildinfo.entity.pojo.FatherComment;
import com.lry.lostchildinfo.service.FatherCommentService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 父评论表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/fatherComment")
public class FatherCommentController {

    @Autowired
    FatherCommentService fatherCommentService;

    /**
     * 父评论分页查询
     * @param fatherCommentPo
     * @return
     */
    @OperationLog(describe = "父评论分页查询")
    @PostMapping("list")
    public Result listByPage(@RequestBody FatherCommentPo fatherCommentPo){
        PageVo pageVo = fatherCommentService.listByPage(fatherCommentPo);
        return Result.success(pageVo);
    }

    /**
     * 添加评论
     * @param fatherCommentPo
     * @return
     */
    @OperationLog(describe = "添加评论")
    @PostMapping("add")
    public Result add(@RequestBody FatherCommentPo fatherCommentPo){
        return fatherCommentService.add(fatherCommentPo);
    }

    /**
     * 删除评论
     * @param ids
     * @return
     */
    @OperationLog(describe = "删除评论")
    @DeleteMapping("{ids}")
    public Result del(@PathVariable("ids") Long ...ids){
        return fatherCommentService.del(ids);
    }

    /**
     * 更新评论
     * @param fatherCommentPo
     * @return
     */
    @OperationLog(describe = "更新评论")
    @PostMapping("update")
    public Result update(@RequestBody FatherCommentPo fatherCommentPo){
        return Result.success();
    }

    /**
     * 导入评论
     * @param file
     * @return
     */
    @OperationLog(describe = "导入评论")
    @PostMapping("import")
    public Result importFatherComment(MultipartFile file){
        return Result.success();
    }

    /**
     * 导出评论
     * @param response
     */
    @OperationLog(describe = "导出评论")
    @GetMapping("export")
    public void exportFatherComment(HttpServletResponse response){
        List<FatherComment> list = fatherCommentService.list();
        ExcelUtil.exportExcel(response,FatherComment.class,list,"父评论表");
    }

}
