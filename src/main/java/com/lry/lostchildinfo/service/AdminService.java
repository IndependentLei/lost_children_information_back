package com.lry.lostchildinfo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface AdminService extends IService<Admin> {
    Result selectList(AdminPo adminPo);
}
