package com.lry.lostchildinfo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.pojo.Admin;
import com.lry.lostchildinfo.mapper.AdminMapper;
import com.lry.lostchildinfo.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Result selectList(AdminPo adminPo) {
        IPage<Admin> page = new Page<>(adminPo.getStartPage(), adminPo.getPageSize());

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
                .eq(StringUtils.isNotBlank(adminPo.getUserCode()),Admin::getUserCode,adminPo.getUserCode());

        IPage<Admin> list = adminMapper.selectPage(page,wrapper);
        PageVo pageVo = new PageVo(list.getCurrent(),list.getSize(),list.getTotal(),list.getRecords());
        return Result.success(pageVo);
    }
}
