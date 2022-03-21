package com.lry.lostchildinfo.mapper;

import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.po.UserPo;
import com.lry.lostchildinfo.entity.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lry.lostchildinfo.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<UserVo> listByPage(@Param("userPo") UserPo userPo);

    List<User> allUser();
}
