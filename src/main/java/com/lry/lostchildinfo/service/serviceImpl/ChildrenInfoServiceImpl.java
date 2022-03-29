package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.exception.ServiceException;
import com.lry.lostchildinfo.mapper.ChildrenInfoAttachMapper;
import com.lry.lostchildinfo.mapper.ChildrenInfoMapper;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.utils.SecurityUtil;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.property.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.util.Arrays;

/**
 * <p>
 * 儿童信息表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class ChildrenInfoServiceImpl extends ServiceImpl<ChildrenInfoMapper, ChildrenInfo> implements ChildrenInfoService {

    @Autowired
    ChildrenInfoMapper childrenInfoMapper;
    @Autowired
    ChildrenInfoAttachService childrenInfoAttachService;
    @Override
    public PageVo listByPage(ChildrenInfoPo childrenInfoPo) {
        Page<ChildrenInfo> page = new Page<>(childrenInfoPo.getStartPage(),childrenInfoPo.getPageSize());
        Page<ChildrenInfo> childrenInfoPage = childrenInfoMapper.selectPage(page, new LambdaQueryWrapper<ChildrenInfo>()
                .like(StringUtils.isNotBlank(childrenInfoPo.getCreateName()), ChildrenInfo::getCreateName, childrenInfoPo.getCreateName())
                .like(StringUtils.isNotBlank(childrenInfoPo.getIdCard()),ChildrenInfo::getIdCard,childrenInfoPo.getIdCard())
                .like(StringUtils.isNotBlank(childrenInfoPo.getLostLocation()), ChildrenInfo::getLostLocation, childrenInfoPo.getLostLocation())
                .like(StringUtils.isNotBlank(childrenInfoPo.getChildrenFeature()), ChildrenInfo::getChildrenFeature, childrenInfoPo.getChildrenFeature())
                .between(StringUtils.isNotBlank(childrenInfoPo.getStartTime()) && StringUtils.isNotBlank(childrenInfoPo.getEndTime())
                        , ChildrenInfo::getLostTime
                        , childrenInfoPo.getStartTime(), childrenInfoPo.getEndTime())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getAge()), ChildrenInfo::getAge, childrenInfoPo.getAge())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getSex()), ChildrenInfo::getSex, childrenInfoPo.getSex())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getFind()),ChildrenInfo::getFind,childrenInfoPo.getFind())
                .orderByDesc(ChildrenInfo::getCreateTime));

        PageVo pageVo = new PageVo(childrenInfoPage.getCurrent()
                ,childrenInfoPage.getSize()
                ,childrenInfoPage.getTotal()
                ,childrenInfoPage.getRecords());


        return pageVo;
    }

    @Override
    public boolean add(ChildrenInfoPo childrenInfoPo) {
        ChildrenInfo childrenInfo = new ChildrenInfo();
        BeanUtil.copyProperties(childrenInfoPo,childrenInfo);
        User currentUser = SecurityUtil.getCurrentUser();
        childrenInfo.setUserId(currentUser.getUserId());
        childrenInfo.setCreateName(currentUser.getUserName());

        if (childrenInfoMapper.insert(childrenInfo) > 0){
            for (ChildrenInfoAttach attach : childrenInfoPo.getChildrenInfoAttachList()){
                attach.setChildrenInfoId(childrenInfo.getChildrenId());
            }
            if (childrenInfoAttachService.saveBatch(childrenInfoPo.getChildrenInfoAttachList())){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean del(Long[] ids) {
        int i = childrenInfoMapper.deleteBatchIds(Arrays.asList(ids));
        if ( i == ids.length ){
            for(Long id : ids){
                if(!childrenInfoAttachService.remove(new QueryWrapper<ChildrenInfoAttach>().eq("children_info_id", id))){
                    throw new ServiceException("删除失败");
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateChildrenInfo(ChildrenInfoPo childrenInfoPo) {
        ChildrenInfo childrenInfo = new ChildrenInfo();
        BeanUtil.copyProperties(childrenInfoPo,childrenInfo);
        int i = childrenInfoMapper.updateById(childrenInfo);
        if ( i > 0 ){
            return childrenInfoAttachService.saveBatch(childrenInfoPo.getChildrenInfoAttachList());
        }
        return false;
    }
}
