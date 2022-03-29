package com.lry.lostchildinfo.service;

import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.MenuPo;
import com.lry.lostchildinfo.entity.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
public interface MenuService extends IService<Menu> {

    PageVo listByPage(MenuPo menuPo);
}
