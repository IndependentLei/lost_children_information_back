package com.lry.lostchildinfo.service.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.UserMenu;
import com.lry.lostchildinfo.mapper.UserMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.service.UserMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表和菜单表关联(一对多) 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenu> implements UserMenuService {

}
