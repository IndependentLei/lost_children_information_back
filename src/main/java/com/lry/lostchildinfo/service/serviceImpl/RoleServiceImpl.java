package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.RolePo;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.mapper.RoleMapper;
import com.lry.lostchildinfo.mapper.UserRoleMapper;
import com.lry.lostchildinfo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public PageVo listByPage(RolePo rolePo) {
        Page<Role> page = new Page<>(rolePo.getStartPage(),rolePo.getPageSize());
        Page<Role> rolePage = roleMapper.selectPage(page, new LambdaQueryWrapper<Role>().orderByDesc(Role::getCreateTime));

        PageVo pageVo = new PageVo(
                rolePage.getCurrent(),
                rolePage.getSize(),
                rolePage.getTotal(),
                rolePage.getRecords()
        );

        return pageVo;
    }

    @Override
    public boolean delByIds(Long ...roleIds) {
        for(Long roleId : roleIds){
            List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("role_id", roleId));
            if (userRoleList.size() != 0){
                return false;
            }
        }
        int i = roleMapper.deleteBatchIds(Arrays.asList(roleIds));

        return i > 0;
    }
}
