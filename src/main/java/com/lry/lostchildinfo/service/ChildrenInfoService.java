package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 儿童信息表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface ChildrenInfoService extends IService<ChildrenInfo> {

    PageVo listByPage(ChildrenInfoPo childrenInfoPo);

    boolean add(ChildrenInfoPo childrenInfoPo);

    boolean del(Long[] ids);

    boolean updateChildrenInfo(ChildrenInfoPo childrenInfoPo);
}
