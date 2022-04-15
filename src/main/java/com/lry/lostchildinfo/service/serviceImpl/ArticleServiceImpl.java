package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ArticlePo;
import com.lry.lostchildinfo.entity.pojo.Article;
import com.lry.lostchildinfo.mapper.ArticleMapper;
import com.lry.lostchildinfo.service.ArticleService;
import com.lry.lostchildinfo.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-04-14
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Result listByPage(ArticlePo articlePo) {
        Page<Article> page = new Page<Article>(articlePo.getStartPage(),articlePo.getPageSize());
        Page<Article> articlePage = articleMapper.selectPage(page, Wrappers.<Article>lambdaQuery()
                .like(StringUtils.isNotBlank(articlePo.getTitle()), Article::getTitle, articlePo.getTitle())
                .like(StringUtils.isNotBlank(articlePo.getContent()), Article::getContent, articlePo.getContent())
                .between((StringUtils.isNotBlank(articlePo.getStartTime()) && StringUtils.isNotBlank(articlePo.getEndTime()))
                        , Article::getCreateTime
                        , articlePo.getStartTime(), articlePo.getEndTime())
                .orderByDesc(Article::getCreateTime));
        PageVo<Article> pageVo = new PageVo<>(
                articlePage.getCurrent(),
                articlePage.getSize(),
                articlePage.getTotal(),
                articlePage.getRecords()
        );
        return Result.success(pageVo);
    }

    @Override
    public Result add(ArticlePo articlePo) {
        List<Article> articles = articleMapper.selectList(Wrappers.<Article>query().eq("title", articlePo.getTitle()));
        if ( articles.size() > 0){
            return Result.error("已经有该标题的文章");
        }
        Article article = new Article();
        BeanUtil.copyProperties(articlePo,article);
        article.setUserId(SecurityUtil.getCurrentUser().getUserId());
        article.setUserCode(SecurityUtil.getCurrentUser().getUserCode());

        int insert = articleMapper.insert(article);
        return insert > 0 ? Result.success("新增成功"):Result.error("新增失败");
    }

    @Override
    public Result del(Long[] ids) {
        return removeByIds(Arrays.asList(ids))?Result.success("删除成功"):Result.error("删除失败");
    }

    @Override
    public Result updateArticle(ArticlePo articlePo) {
        Article article = new Article();
        BeanUtil.copyProperties(articlePo,article);
        article.setUpdateId(SecurityUtil.getCurrentUser().getUserId());
        article.setUserCode(SecurityUtil.getCurrentUser().getUserCode());
        int i = articleMapper.updateById(article);
        return i>0?Result.success("修改成功"):Result.error("修改失败");
    }
}
