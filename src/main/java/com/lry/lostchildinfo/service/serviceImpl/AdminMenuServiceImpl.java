package com.lry.lostchildinfo.service.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.AdminMenu;
import com.lry.lostchildinfo.mapper.AdminMenuMapper;
import com.lry.lostchildinfo.service.AdminMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

}
