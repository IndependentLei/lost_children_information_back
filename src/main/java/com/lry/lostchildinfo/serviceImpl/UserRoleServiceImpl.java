package com.lry.lostchildinfo.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.mapper.UserRoleMapper;
import com.lry.lostchildinfo.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表和角色表关联 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
