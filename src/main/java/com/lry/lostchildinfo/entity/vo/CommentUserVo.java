package com.lry.lostchildinfo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description : 评论用户实体
 * @ClassName : CommentUserVo
 * @Author : jdl
 * @Create : 2022-04-09 18:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUserVo implements Serializable {
    private static final long serialVersionUID = -4684653468L;
    private Long id;
    private String nickName;
    private String avatar;
}
