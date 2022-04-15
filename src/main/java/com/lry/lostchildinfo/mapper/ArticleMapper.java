package com.lry.lostchildinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lry.lostchildinfo.entity.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-04-15
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}
