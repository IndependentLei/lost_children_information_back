package com.lry.lostchildinfo.mapper;

import com.lry.lostchildinfo.entity.pojo.AdminMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 管理员表和菜单表关联(一对多) Mapper 接口
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Mapper
@Repository
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {

}
