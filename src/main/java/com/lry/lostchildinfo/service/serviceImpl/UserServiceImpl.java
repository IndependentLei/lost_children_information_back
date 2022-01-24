package com.lry.lostchildinfo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.mapper.UserMapper;
import com.lry.lostchildinfo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserCode,username));
    }


    @Override
    public Result selectList(UserPo userPo) {
        IPage<User> page = new Page<>(userPo.getStartPage(), userPo.getPageSize());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(StringUtils.isNotBlank(userPo.getUserCode()),User::getUserCode,userPo.getUserCode());

        IPage<User> list = userMapper.selectPage(page,wrapper);
        PageVo pageVo = new PageVo(list.getCurrent(),list.getSize(),list.getTotal(),list.getRecords());
        return Result.success(pageVo);
    }
}
