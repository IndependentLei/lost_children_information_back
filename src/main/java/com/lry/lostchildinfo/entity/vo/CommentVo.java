package com.lry.lostchildinfo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 评论返回实体
 * @ClassName : CommentVo
 * @Author : jdl
 * @Create : 2022-04-09 18:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo implements Serializable {
    private static final long serialVersionUID = -13123124124124L;

    private Long id;
    private Long userId;
    private String userCode;
    private Long replayId;
    private String replayCode;
    private CommentUserVo commentUser;
    private TargetUserVo targetUser;
    private String content;
    private String createDate;
    private List<CommentVo> childrenList = new ArrayList<>();
}
