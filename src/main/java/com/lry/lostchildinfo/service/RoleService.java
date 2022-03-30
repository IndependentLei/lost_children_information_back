package com.lry.lostchildinfo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.RolePo;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Arrays;
import java.util.List;

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

    List<Role> listAll();
}
