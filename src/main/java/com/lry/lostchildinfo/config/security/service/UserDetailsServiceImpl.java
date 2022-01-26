package com.lry.lostchildinfo.config.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lry.lostchildinfo.common.UserState;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.exception.ServiceException;
import com.lry.lostchildinfo.service.RoleService;
import com.lry.lostchildinfo.service.UserRoleService;
import com.lry.lostchildinfo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author : jdl
 * @description : 登录验证身份
 * @ClassName : UserDetailsServiceImpl
 * @create : 2022-01-22 22:25
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;
    @Resource
    UserRoleService userRoleService;
    @Resource
    RoleService roleService;


    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户信息为：{}",username);
        log.info("已经进入身份认证");
        User user = userService.getUserByName(username);
        if (ObjectUtils.isEmpty(user)){
            log.error("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        if(user.getDeleted().equals(String.valueOf(UserState.disabled))){
            log.error("禁止登录");
            throw  new ServiceException("用户禁止登录");
        }


        return new LoginUser(user.getUserId(),username,user.getUserPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(getAuthority(user.getUserId())));
    }

    /**
     * 根据用户id获得权限
     * @param userId
     * @return
     */
    public String getAuthority(Long userId){

        UserRole userRole = userRoleService.getBaseMapper()
                .selectOne(new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getUserId,userId));


        // 获取角色名
        Role role = roleService.getBaseMapper()
                .selectOne(new LambdaQueryWrapper<Role>()
                        .eq(Role::getRoleId,userRole.getRoleId()));
        return role.getRoleName();
    }
}
