package com.lry.lostchildinfo.mapper;

import com.lry.lostchildinfo.entity.pojo.FatherComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lry.lostchildinfo.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 父评论表 Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Mapper
@Repository
public interface FatherCommentMapper extends BaseMapper<FatherComment> {

    List<CommentVo> getCommentByChilId(@Param("id") Long id);
}
