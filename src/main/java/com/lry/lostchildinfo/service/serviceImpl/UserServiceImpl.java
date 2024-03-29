package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.enums.RoleType;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.Role;
import com.lry.lostchildinfo.entity.pojo.User;
import com.lry.lostchildinfo.entity.pojo.UserRole;
import com.lry.lostchildinfo.entity.vo.UserVo;
import com.lry.lostchildinfo.mapper.RoleMapper;
import com.lry.lostchildinfo.mapper.UserMapper;
import com.lry.lostchildinfo.mapper.UserRoleMapper;
import com.lry.lostchildinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;


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

        PageVo<UserVo> pageVo = new PageVo<>(userPo.getStartPage(),userPo.getPageSize(), (long) user.size(),userVOS);
        return Result.success(pageVo);
    }

    @Override
    public UserVo getUserById(Long id) {
       User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id",id));
       UserVo userVo = new UserVo();
       BeanUtil.copyProperties(user,userVo);

       Long roleId = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id",id)).getRoleId();

        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id", roleId));
        userVo.setRoleType(role.getRoleType());

        return userVo;
    }

    @Override
    public List<User> allUser() {
        return userMapper.allUser();
    }

    @Override
    public List<UserVo> getVolunteers() {
        List<UserVo> userVos = userMapper.listByPage(new UserPo());
        List<UserVo> collect = userVos.stream()
                .filter(
                        userVo -> Integer.parseInt(userVo.getRoleType()) == RoleType.VOLUNTEER.getType())
                .collect(Collectors.toList());
        return collect;
    }

}
