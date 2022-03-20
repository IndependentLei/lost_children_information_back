package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.AdminPo;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lry.lostchildinfo.entity.vo.UserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface UserService extends IService<User> {

    User getUserByName(String username);

    Result selectList(UserPo userPo);

    UserVo getUserById(Long id);
}
