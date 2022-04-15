package com.lry.lostchildinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.ArticlePo;
import com.lry.lostchildinfo.entity.pojo.Article;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jdl
 * @since 2022-04-15
 */
public interface ArticleService extends IService<Article> {
    Result listByPage(ArticlePo articlePo);

    Result add(ArticlePo articlePo);

    Result del(Long[] ids);

    Result updateArticle(ArticlePo articlePo);
}
