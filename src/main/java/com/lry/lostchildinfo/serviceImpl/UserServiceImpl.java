package com.lry.lostchildinfo.serviceImpl;

import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.mapper.UserMapper;
import com.lry.lostchildinfo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
