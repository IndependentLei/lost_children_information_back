package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SonCommentPo;
import com.lry.lostchildinfo.entity.pojo.SonComment;
import com.lry.lostchildinfo.mapper.SonCommentMapper;
import com.lry.lostchildinfo.service.SonCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 子评论表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class SonCommentServiceImpl extends ServiceImpl<SonCommentMapper, SonComment> implements SonCommentService {

    @Autowired
    SonCommentMapper sonCommentMapper;

    @Override
    public PageVo listByPage(SonCommentPo sonCommentPo) {
        Page<SonComment> page = new Page<>(sonCommentPo.getStartPage(),sonCommentPo.getPageSize());
        Page<SonComment> sonCommentPage = sonCommentMapper.selectPage(page, new LambdaQueryWrapper<SonComment>()
                                            .like(StringUtils.isNotBlank(sonCommentPo.getUserCode())
                                                    , SonComment::getUserCode, sonCommentPo.getUserCode())
                                            .like(StringUtils.isNotBlank(sonCommentPo.getReplayCode())
                                                    , SonComment::getReplayCode, sonCommentPo.getReplayCode())
                                            .like(StringUtils.isNotBlank(sonCommentPo.getReplayContext())
                                                    , SonComment::getReplayContext
                                                    , sonCommentPo.getReplayContext())
                                            .between(StringUtils.isNotBlank(sonCommentPo.getStartTime()) && StringUtils.isNotBlank(sonCommentPo.getEndTime())
                                                    , SonComment::getCreateTime, sonCommentPo.getStartTime()
                                                    , sonCommentPo.getEndTime())
                                            .orderByDesc(SonComment::getCreateTime));

        PageVo pageVo = new PageVo(
                sonCommentPage.getCurrent()
                ,sonCommentPage.getSize()
                ,sonCommentPage.getTotal()
                ,sonCommentPage.getRecords()
        );
        return pageVo;
    }

    @Override
    public Result add(SonCommentPo sonCommentPo) {
        SonComment sonComment = new SonComment();
        BeanUtil.copyProperties(sonCommentPo,sonComment);
        sonComment.setUserId(SecurityUtil.getCurrentUser().getUserId());
        sonComment.setUserCode(SecurityUtil.getCurrentUser().getUserCode());

        int insert = sonCommentMapper.insert(sonComment);
        return insert > 0 ? Result.success("评论成功") : Result.error("评论失败");
    }

    @Override
    public Result del(Long[] ids) {
        boolean b = removeByIds(Arrays.asList(ids));
        return b ? Result.success("删除成功") : Result.error("删除失败");
    }
}
