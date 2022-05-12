package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.FatherCommentPo;
import com.lry.lostchildinfo.entity.pojo.FatherComment;
import com.lry.lostchildinfo.entity.pojo.SonComment;
import com.lry.lostchildinfo.exception.ServiceException;
import com.lry.lostchildinfo.mapper.FatherCommentMapper;
import com.lry.lostchildinfo.mapper.SonCommentMapper;
import com.lry.lostchildinfo.service.FatherCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.utils.SecurityUtil;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>
 * 父评论表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class FatherCommentServiceImpl extends ServiceImpl<FatherCommentMapper, FatherComment> implements FatherCommentService {

    @Autowired
    FatherCommentMapper fatherCommentMapper;
    @Autowired
    SonCommentMapper sonCommentMapper;

    @Override
    public PageVo listByPage(FatherCommentPo fatherCommentPo) {
        Page<FatherComment> page = new Page<>(fatherCommentPo.getStartPage(),fatherCommentPo.getPageSize());
        Page<FatherComment> fatherCommentPage = fatherCommentMapper.selectPage(page, new LambdaQueryWrapper<FatherComment>()
                                                    .like(StringUtils.isNotBlank(fatherCommentPo.getUserCode())
                                                            , FatherComment::getUserCode
                                                            , fatherCommentPo.getUserCode())
                                                    .like(StringUtils.isNotBlank(fatherCommentPo.getCommentContent())
                                                            , FatherComment::getCommentContent
                                                            , fatherCommentPo.getCommentContent())
                                                    .between(StringUtils.isNotBlank(fatherCommentPo.getStartTime()) && StringUtils.isNotBlank(fatherCommentPo.getEndTime())
                                                            , FatherComment::getCreateTime
                                                            , fatherCommentPo.getStartTime()
                                                            , fatherCommentPo.getEndTime())
                                                    .orderByDesc(FatherComment::getCreateTime));

        PageVo<FatherComment> pageVo = new PageVo<>(
                fatherCommentPage.getCurrent()
                ,fatherCommentPage.getSize()
                ,fatherCommentPage.getTotal()
                ,fatherCommentPage.getRecords()
        );
        return pageVo;
    }

    @Override
    public Result add(FatherCommentPo fatherCommentPo) {
        FatherComment fatherComment = new FatherComment();
        BeanUtil.copyProperties(fatherCommentPo,fatherComment);
        int insert = fatherCommentMapper.insert(fatherComment);
        return insert > 0 ? Result.success("评论成功"):Result.error("评论失败,请联系管理员");
    }

    @Override
    public Result del(Long[] ids) {
        for(Long id : ids){
            Integer sonCommentCount = sonCommentMapper.selectCount(new QueryWrapper<SonComment>().eq("father_comment_id", id));
            if (sonCommentCount != 0){
                return Result.error("有子评论,不可以删除");
            }
        }
        int i = fatherCommentMapper.deleteBatchIds(Arrays.asList(ids));

        return i == ids.length ? Result.success("删除成功"):Result.error("删除失败");
    }
}
