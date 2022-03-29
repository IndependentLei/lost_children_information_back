package com.lry.lostchildinfo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.MenuPo;
import com.lry.lostchildinfo.entity.pojo.Menu;
import com.lry.lostchildinfo.mapper.MenuMapper;
import com.lry.lostchildinfo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Override
    public PageVo listByPage(MenuPo menuPo) {
        Page<Menu> page = new Page<>(menuPo.getStartPage(),menuPo.getPageSize());
        Page<Menu> menuPage = menuMapper.selectPage(page, new LambdaQueryWrapper<Menu>()
                .like(StringUtils.isNotBlank(menuPo.getLabel())
                        , Menu::getLabel
                        , menuPo.getLabel())
                .isNull(Menu::getFatherId)
                .orderByDesc(Menu::getCreateTime));

        List<Menu> list = menuPage.getRecords();

        // 将他的子叶查询出来，放在children中
        for(Menu menu : list){
            List<Menu> menusByFatherId = getMenusByFatherId(menu.getMenuId());
            menu.setChildren(menusByFatherId);
        }

        menuPage.setRecords(list);

        PageVo pageVo = new PageVo(
                menuPage.getCurrent()
                ,menuPage.getSize()
                ,menuPage.getTotal()
                ,menuPage.getRecords()
        );
        return pageVo;
    }

    /**
     * 更具father_id查询所有menu集合
     * @param id
     * @return
     */
    private List<Menu> getMenusByFatherId(Long id){
        List<Menu> list = menuMapper
                .selectList(new QueryWrapper<Menu>()
                        .eq("father_id", id));
        return list;
    }
}
