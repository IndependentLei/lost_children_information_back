package com.lry.lostchildinfo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : jdl
 * @description : 分页实体
 * @ClassName : PageVo
 * @create : 2022-01-22 17:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private Long current;

    /**
     * 页面容量
     */
    private Long size;

    /**
     * 总数
     */

    private Long total;

    /**
     * 查询记录
     */

    private List<T> list = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageVo{");
        sb.append("current=").append(current);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}

