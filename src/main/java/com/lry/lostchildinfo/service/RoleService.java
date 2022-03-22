package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.RolePo;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface RoleService extends IService<Role> {

    PageVo listByPage(RolePo rolePo);

    boolean delByIds(Long ...roleIds);
}
