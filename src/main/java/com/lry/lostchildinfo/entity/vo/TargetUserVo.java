package com.lry.lostchildinfo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description : 被评论人的实体
 * @ClassName : TargetUserVo
 * @Author : jdl
 * @Create : 2022-04-09 18:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetUserVo implements Serializable {
    private static final long serialVersionUID = -68484684L;
    private Long id;
    private String nickName;
    private String avatar;
}
