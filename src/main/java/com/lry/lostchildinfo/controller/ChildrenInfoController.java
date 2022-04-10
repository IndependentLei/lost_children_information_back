package com.lry.lostchildinfo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 儿童信息表 前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/lostchildinfo/childrenInfo")
public class ChildrenInfoController {

    @Autowired
    ChildrenInfoService childrenInfoService;
    @Autowired
    ChildrenInfoAttachService childrenInfoAttachService;

    /**
     * 儿童信息列表
     * @param childrenInfoPo
     * @return
     */
    @OperationLog(describe = "儿童信息列表")
    @PostMapping("list")
    public Result list(@RequestBody ChildrenInfoPo childrenInfoPo){
        PageVo pageVo = childrenInfoService.listByPage(childrenInfoPo);
        return Result.success(pageVo);
    }

    /**
     * 新增儿童信息
     * @param childrenInfoPo
     * @return
     */
    @OperationLog(describe = "新增儿童信息")
    @PostMapping("add")
    public Result add(@RequestBody ChildrenInfoPo childrenInfoPo) {
        if (childrenInfoService.add(childrenInfoPo))
            Result.success("操作成功");
        return Result.error("操作失败");
    }

    /**
     * 删除儿童信息
     * @param ids
     * @return
     */
    @OperationLog(describe = "删除儿童信息")
    @DeleteMapping("{ids}")
    public Result del(@PathVariable("ids") Long ...ids){
        if (childrenInfoService.del(ids))
            return Result.success("删除成功");
        return Result.error("删除失败");
    }

    /**
     * 更新儿童信息
     * @param childrenInfoPo
     * @return
     */
    @OperationLog(describe = "更新儿童信息")
    @PostMapping("update")
    public Result update(@RequestBody ChildrenInfoPo childrenInfoPo){
        if (childrenInfoService.updateChildrenInfo(childrenInfoPo))
            return Result.success("更新成功");
        return Result.error("更新失败");
    }

    /**
     * 根据id获取儿童信息
     * @param id
     * @return
     */
    @OperationLog(describe = "根据id获取儿童信息")
    @GetMapping("{id}")
    public Result getChildrenInfoById(@PathVariable("id") Long id){
        ChildrenInfo childrenInfo = childrenInfoService.getById(id);
        List<ChildrenInfoAttach> childrenInfoAttach = childrenInfoAttachService
                .getBaseMapper()
                .selectList(new QueryWrapper<ChildrenInfoAttach>()
                        .eq("children_info_id", id));
        ChildrenInfoPo childrenInfoPo = new ChildrenInfoPo();
        BeanUtil.copyProperties(childrenInfo,childrenInfoPo);
        childrenInfoPo.setChildrenInfoAttachList(childrenInfoAttach);
        return Result.success(childrenInfoPo);
    }

    /**
     * 导入儿童信息
     * @param file
     * @return
     */
    @OperationLog(describe = "导入儿童信息")
    @PostMapping("import")
    public Result importChildrenInfo(MultipartFile file){
        // TODO ...
        return Result.success("导入成功");
    }

    /**
     * 导出儿童信息
     * @param response
     */
    @OperationLog(describe = "导出儿童信息")
    @GetMapping("export")
    public void exportChildrenInfo(HttpServletResponse response){
        List<ChildrenInfo> list = childrenInfoService.list();
        ExcelUtil.exportExcel(response,ChildrenInfo.class,list,"儿童信息表");
    }


    /**
     * 根据儿童信息id查询所有评论
     * @param id
     * @return
     */
    @OperationLog(describe = "根据儿童信息id查询所有评论")
    @GetMapping("/commentById/{id}")
    public Result getCommentByChildId(@PathVariable("id") Long id){
        // 评论实体
        return Result.success(childrenInfoService.getCommentByChildId(id));
    }

    /**
     * 儿童信息列表和第一张图片
     * @param childrenInfoPo
     * @return
     */
    @OperationLog(describe = "儿童信息列表")
    @PostMapping("listAndPicAttach")
    public Result listAndPicAttach(@RequestBody ChildrenInfoPo childrenInfoPo){
        PageVo<ChildrenInfo> pageVo = childrenInfoService.listAndPicAttachByPage(childrenInfoPo);
        return Result.success(pageVo);
    }

}
