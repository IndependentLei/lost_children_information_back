package com.lry.lostchildinfo.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.Admin;
import com.lry.lostchildinfo.mapper.AdminMapper;
import com.lry.lostchildinfo.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
