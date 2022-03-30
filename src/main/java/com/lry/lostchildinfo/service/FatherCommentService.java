package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.FatherCommentPo;
import com.lry.lostchildinfo.entity.pojo.FatherComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 父评论表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface FatherCommentService extends IService<FatherComment> {

    PageVo listByPage(FatherCommentPo fatherCommentPo);

    Result add(FatherCommentPo fatherCommentPo);

    Result del(Long[] ids);
}
