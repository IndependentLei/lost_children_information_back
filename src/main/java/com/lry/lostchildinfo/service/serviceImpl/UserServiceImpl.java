package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.vo.UserVo;
import com.lry.lostchildinfo.mapper.UserMapper;
import com.lry.lostchildinfo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<UserVo> user = userMapper.listByPage(userPo);
        List<UserVo> userVOS = new ArrayList<>(10);

        int page = userPo.getStartPage() * userPo.getPageSize() > user.size() ? user.size() : (int) (userPo.getStartPage() * userPo.getPageSize());

        for (int i = (int) ((userPo.getStartPage()-1)*userPo.getPageSize()); i < page ; i++){
            userVOS.add(user.get(i));
        }

        PageVo pageVo = new PageVo(userPo.getStartPage(),userPo.getPageSize(), (long) user.size(),userVOS);
        return Result.success(pageVo);
    }

}
