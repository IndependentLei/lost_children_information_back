package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.SonCommentPo;
import com.lry.lostchildinfo.entity.pojo.SonComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 子评论表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface SonCommentService extends IService<SonComment> {

    PageVo listByPage(SonCommentPo sonCommentPo);

    Result add(SonCommentPo sonCommentPo);

    Result del(Long[] ids);
}
