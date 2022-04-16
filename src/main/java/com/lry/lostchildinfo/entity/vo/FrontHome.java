package com.lry.lostchildinfo.entity.vo;

import com.lry.lostchildinfo.entity.pojo.Article;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfo;
import com.lry.lostchildinfo.entity.pojo.ChildrenInfoAttach;
import com.lry.lostchildinfo.entity.pojo.Slideshow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description : 首页数据实体
 * @ClassName : FrontHome
 * @Author : jdl
 * @Create : 2022-04-09 17:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontHome implements Serializable {
    private static final long serialVersionUID = 5468446848L;

    private List<Slideshow> slideshows;
    private List<ChildrenInfo> childrenInfos;
    private List<String> firstPic;
    private List<Article> articles;
}
