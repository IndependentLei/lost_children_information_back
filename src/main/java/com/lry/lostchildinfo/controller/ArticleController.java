package com.lry.lostchildinfo.controller;


import com.lry.lostchildinfo.annotation.OperationLog;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.ArticlePo;
import com.lry.lostchildinfo.entity.pojo.Article;
import com.lry.lostchildinfo.service.ArticleService;
import com.lry.lostchildinfo.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jdl
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/lostchildinfo/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 分页查询文章
     * @return
     */
    @OperationLog(describe = "分页查询文章")
    @PostMapping("list")
    public Result listByPage(@RequestBody ArticlePo articlePo){
        return articleService.listByPage(articlePo);
    }

    /**
     * 新增文章
     * @return
     */
    @OperationLog(describe = "新增文章")
    @PostMapping("add")
    public Result add(@RequestBody ArticlePo articlePo){
        return articleService.add(articlePo);
    }

    /**
     * 删除文章
     * @param ids
     * @return
     */
    @OperationLog(describe = "删除文章")
    @DeleteMapping("{ids}")
    public Result del(@PathVariable("ids") Long[] ids){
        return articleService.del(ids);
    }

    /**
     * 修改文章
     * @param articlePo
     * @return
     */
    @OperationLog(describe = "修改文章")
    @PostMapping("update")
    public Result update(@RequestBody ArticlePo articlePo){
        return articleService.updateArticle(articlePo);
    }

    /**
     * 根据id获取文章信息
     * @return
     */
    @OperationLog(describe = "根据id获取文章信息")
    @GetMapping("/getArticleById/{id}")
    public Result getArticleById(@PathVariable("id") Long id){
        return Result.success(articleService.getById(id));
    }

    /**
     * 导入文章
     * @param file
     * @return
     */
    @OperationLog(describe = "导入文章")
    @PostMapping("import")
    public Result importArticle(MultipartFile file){
        return Result.success();
    }

    /**
     * 导出文章
     * @param response
     * @return
     */
    @OperationLog(describe = "导出文章")
    @GetMapping("export")
    public void exportArticle(HttpServletResponse response){
        List<Article> list = articleService.list();
        ExcelUtil.exportExcel(response,Article.class,list,"文章表");
    }

}
