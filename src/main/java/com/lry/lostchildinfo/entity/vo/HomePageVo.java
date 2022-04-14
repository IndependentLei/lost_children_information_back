package com.lry.lostchildinfo.entity.vo;

import com.lry.lostchildinfo.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description : 首页实体
 * @ClassName : HomePageVo
 * @Author : jdl
 * @Create : 2022-03-29 19:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageVo {
    /**
     * 自愿者
     */
    private List<UserVo> volunteers;

    /**
     * 统计孩子的总数和已经找到的孩子、未找到的孩子
     */
    private List<Integer> cards;

    /**
     * 获得个人信息
     */
    private User user;

    private EchartsVo echartsList;
}
