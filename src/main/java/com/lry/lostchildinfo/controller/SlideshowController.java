package com.lry.lostchildinfo.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SlideshowPo;
import com.lry.lostchildinfo.entity.pojo.Slideshow;
import com.lry.lostchildinfo.service.SlideshowService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import com.lry.lostchildinfo.utils.FileUtil;
import com.lry.lostchildinfo.utils.RedisUtil;
import com.lry.lostchildinfo.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Slf4j
@RestController
@RequestMapping("/lostchildinfo/slideshow")
public class SlideshowController {

    @Autowired
    SlideshowService slideshowService;


    /**
     * 分页查找
     * @param slideshowPo
     * @return
     */
    @OperationLog(describe = "分页查找")
    @PostMapping("list")
    public Result list(@RequestBody SlideshowPo slideshowPo){
        if (slideshowPo.getStartPage() == null)
            slideshowPo.setPageSize(1L);
        if (slideshowPo.getState().equals(""))
            slideshowPo.setState(null);
        if (slideshowPo.getStartPage() == null || slideshowPo.getPageSize() == null) {
            return Result.error("参数出错");
        }
        PageVo pageVo = slideshowService.listByPage(slideshowPo);
        return Result.success(pageVo);
    }

    /**
     * 添加轮播图
     * @param slideshowPo
     * @return
     */
    @PostMapping("add")
    @OperationLog(describe = "添加轮播图")
    public Result add(@RequestBody SlideshowPo slideshowPo){
        if (StringUtils.isNotBlank(slideshowPo.getContext())
                && StringUtils.isNotBlank(slideshowPo.getPic())
                && slideshowPo.getState() != null
                && StringUtils.isNotBlank(slideshowPo.getState())){
            Slideshow slideshow = new Slideshow();
            BeanUtil.copyProperties(slideshowPo,slideshow);
            slideshow.setCreateId(SecurityUtil.getCurrentUser().getUserId());
            slideshow.setCreateCode(SecurityUtil.getCurrentUser().getUserCode());
            if (slideshowService.save(slideshow)) {
                return Result.success("操作成功");
            }
            return Result.error("操作失败");
        }
        return Result.error("参数出错");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @OperationLog(describe = "批量删除")
    @DeleteMapping("/del/{ids}")
    public Result delete(@PathVariable("ids") Long[] ids){
        if (ids.length < 1){
            return Result.error("参数错误");
        }
        if (slideshowService.removeByIds(Arrays.asList(ids))) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 更具id获取详细信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @OperationLog(describe = "获取详细信息")
    public Result getSlideshowById(@PathVariable("id") Long id){
        if (id > 0){
            Slideshow slideshow = slideshowService.getOne(new LambdaQueryWrapper<Slideshow>().eq(Slideshow::getId,id));
            if (ObjectUtil.isNotEmpty(slideshow)){
                return Result.success(slideshow);
            }else {
                return Result.error("查询失败");
            }
        }
        return Result.error("参数错误");
    }

    /**
     * 更新
     * @param slideshowPo
     * @return
     */
    @OperationLog(describe = "更新轮播图信息")
    @PostMapping("update")
    public Result update(@RequestBody SlideshowPo slideshowPo){
        if (StringUtils.isNotBlank(slideshowPo.getContext()) && StringUtils.isNotBlank(slideshowPo.getPic()) && slideshowPo.getState() != null){
            Slideshow slideshow = new Slideshow();
            BeanUtil.copyProperties(slideshowPo,slideshow);
            slideshow.setUpdateId((SecurityUtil.getCurrentUser().getUserId()));
            slideshow.setUpdateCode(SecurityUtil.getCurrentUser().getUserCode());
            if (slideshowService.updateById(slideshow)) {
                return Result.success("操作成功");
            }
            return Result.error("操作失败");
        }
        return Result.error("参数出错");
    }

    /**
     * 导入
     * @param file
     * @return
     */
    @PostMapping("import")
    @OperationLog(describe = "导入轮播图信息")
    public Result slideshowImport(MultipartFile file){
        if (FileUtil.checkFileName(file,"xls","xlsx")){
            ImportParams params = new ImportParams();
            params.setTitleRows(1); // 标题
            params.setHeadRows(1); // 头
            try {
                List<Slideshow> slideshows = ExcelImportUtil.importExcel(file.getInputStream(), Slideshow.class, params);
                if(ObjectUtil.isEmpty(slideshows)){
                    return Result.error("禁止导入空文件");
                }
                slideshowService.saveBatch(slideshows);
                if (slideshowService.saveBatch(slideshows)) {
                    return Result.success("成功导入" + slideshows.size() + "数据");
                } else {
                    return Result.error("导入失败");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("导入失败");
            }
        } else {
            return Result.error("文件类型错误,请重新上传");
        }
    }

    /**
     * 导出所有
     * @return
     */
    @GetMapping("export")
    @OperationLog(describe = "导出轮播图信息")
    public void slideshowExport(HttpServletResponse response) throws UnsupportedEncodingException {
        List<Slideshow> slideshows = slideshowService.list();
        ExcelUtil.exportExcel(response, Slideshow.class,slideshows,"轮播图管理表");
    }
}
