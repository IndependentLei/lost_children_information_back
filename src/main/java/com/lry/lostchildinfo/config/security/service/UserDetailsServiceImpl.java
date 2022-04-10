package com.lry.lostchildinfo.config.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lry.lostchildinfo.entity.enums.RoleType;
import com.lry.lostchildinfo.entity.enums.UserState;
import com.lry.lostchildinfo.config.security.LoginUser;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.exception.ServiceException;
import com.lry.lostchildinfo.service.RoleService;
import com.lry.lostchildinfo.service.UserRoleService;
import com.lry.lostchildinfo.service.UserService;
import com.lry.lostchildinfo.utils.RedisUtil;
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

    public static final String PERMISSIONS_CACHE = "PermissionsCache:";

    @Resource
    UserService userService;
    @Resource
    UserRoleService userRoleService;
    @Resource
    RoleService roleService;
    @Resource
    RedisUtil redisUtil;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("已经进入身份认证");
        User user = userService.getUserByName(username);
        if (ObjectUtils.isEmpty(user)){
            log.error("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        if(user.getState().equals(String.valueOf(UserState.DISABLED.getType()))){
            log.error("禁止登录");
            throw new ServiceException("用户禁止登录");
        }
//        Role role = getUserRoleById(user.getUserId());
//        if ( role.getRoleType().equals(String.valueOf(RoleType.CUSTOMER.getType()))){
//            log.debug("客户不能登录后台");
//            throw new ServiceException("您没有权限,请联系管理员");
//        }

        return new LoginUser(user.getUserId(),username,user.getUserPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(getAuthority(user.getUserId())));
    }

    /**
     * 根据用户id获得权限
     * @param userId
     * @return
     */
    public String getAuthority(Long userId){
        String authority = "";
        // 权限缓存
        if (redisUtil.hasKey(PERMISSIONS_CACHE+userId))
            authority =(String) redisUtil.get(PERMISSIONS_CACHE+userId);
        else {
            Role role = getUserRoleById(userId);
            authority = role.getRoleName();
            // 缓存时间为三个小时
            redisUtil.set(PERMISSIONS_CACHE+userId,authority,3*60*60);
        }

        return authority;
    }

    private Role getUserRoleById(Long userId){
        UserRole userRole = userRoleService.getBaseMapper()
                .selectOne(new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getUserId,userId));


        // 获取角色名
        Role role = roleService.getBaseMapper()
                .selectOne(new LambdaQueryWrapper<Role>()
                        .eq(Role::getRoleId,userRole.getRoleId()));
        return role;
    }
}
