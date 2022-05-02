package com.lry.lostchildinfo.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lry.lostchildinfo.common.Result;
import com.lry.lostchildinfo.entity.PageVo;
import com.lry.lostchildinfo.entity.po.ChildrenInfoPo;
import com.lry.lostchildinfo.entity.pojo.*;
import com.lry.lostchildinfo.entity.vo.CommentUserVo;
import com.lry.lostchildinfo.entity.vo.CommentVo;
import com.lry.lostchildinfo.entity.vo.TargetUserVo;
import com.lry.lostchildinfo.exception.ServiceException;
import com.lry.lostchildinfo.mapper.ChildrenInfoMapper;
import com.lry.lostchildinfo.mapper.FatherCommentMapper;
import com.lry.lostchildinfo.mapper.SonCommentMapper;
import com.lry.lostchildinfo.mapper.UserMapper;
import com.lry.lostchildinfo.service.ChildrenInfoAttachService;
import com.lry.lostchildinfo.service.ChildrenInfoService;
import com.lry.lostchildinfo.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 儿童信息表 服务实现类
 * </p>
 *
 * @author jdl
 * @since 2022-01-21
 */
@Service
public class ChildrenInfoServiceImpl extends ServiceImpl<ChildrenInfoMapper, ChildrenInfo> implements ChildrenInfoService {

    @Autowired
    ChildrenInfoMapper childrenInfoMapper;
    @Autowired
    ChildrenInfoAttachService childrenInfoAttachService;
    @Autowired
    FatherCommentMapper fatherCommentMapper;
    @Autowired
    SonCommentMapper sonCommentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public PageVo<ChildrenInfo> listByPage(ChildrenInfoPo childrenInfoPo) {
        Page<ChildrenInfo> page = new Page<>(childrenInfoPo.getStartPage(),childrenInfoPo.getPageSize());
        Page<ChildrenInfo> childrenInfoPage = childrenInfoMapper.selectPage(page, new LambdaQueryWrapper<ChildrenInfo>()
                .like(StringUtils.isNotBlank(childrenInfoPo.getChildrenName()),ChildrenInfo::getChildrenName,childrenInfoPo.getChildrenName())
                .like(StringUtils.isNotBlank(childrenInfoPo.getCreateName()), ChildrenInfo::getCreateName, childrenInfoPo.getCreateName())
                .like(StringUtils.isNotBlank(childrenInfoPo.getIdCard()),ChildrenInfo::getIdCard,childrenInfoPo.getIdCard())
                .like(StringUtils.isNotBlank(childrenInfoPo.getLostLocation()), ChildrenInfo::getLostLocation, childrenInfoPo.getLostLocation())
                .like(StringUtils.isNotBlank(childrenInfoPo.getChildrenFeature()), ChildrenInfo::getChildrenFeature, childrenInfoPo.getChildrenFeature())
                .between(StringUtils.isNotBlank(childrenInfoPo.getStartTime()) && StringUtils.isNotBlank(childrenInfoPo.getEndTime())
                        , ChildrenInfo::getLostTime
                        , childrenInfoPo.getStartTime()
                        , childrenInfoPo.getEndTime())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getAge()), ChildrenInfo::getAge, childrenInfoPo.getAge())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getSex()), ChildrenInfo::getSex, childrenInfoPo.getSex())
                .eq(ObjectUtil.isNotEmpty(childrenInfoPo.getFind()),ChildrenInfo::getFind,childrenInfoPo.getFind())
                .orderByDesc(ChildrenInfo::getCreateTime));

        PageVo<ChildrenInfo> pageVo = new PageVo<>(childrenInfoPage.getCurrent()
                ,childrenInfoPage.getSize()
                ,childrenInfoPage.getTotal()
                ,childrenInfoPage.getRecords());


        return pageVo;
    }

    @Override
    public Result add(ChildrenInfoPo childrenInfoPo) {
        ChildrenInfo childrenInfo1 = childrenInfoMapper.selectOne(Wrappers.<ChildrenInfo>query().eq("id_card", childrenInfoPo.getIdCard()));
        if(childrenInfo1!=null){
            return Result.error("已存在此身份证的丢失儿童");
        }
        ChildrenInfo childrenInfo = new ChildrenInfo();
        BeanUtil.copyProperties(childrenInfoPo,childrenInfo);
        User currentUser = SecurityUtil.getCurrentUser();
        childrenInfo.setUserId(currentUser.getUserId());
        childrenInfo.setCreateName(currentUser.getUserCode());
        childrenInfo.setFind("0"); // 未找到

        if (childrenInfoMapper.insert(childrenInfo) > 0){
            ChildrenInfo childrenInfo2 = childrenInfoMapper.selectOne(Wrappers.<ChildrenInfo>query().eq("id_card", childrenInfoPo.getIdCard()));
            List<ChildrenInfoAttach> attachList = new ArrayList<>(5);
            for (String picUrl : childrenInfoPo.getChildrenInfoAttach()){
                ChildrenInfoAttach attach = new ChildrenInfoAttach();
                attach.setChildrenInfoId(childrenInfo2.getChildrenId());
                attach.setPic(picUrl);
                attachList.add(attach);
            }
            if (childrenInfoAttachService.saveBatch(attachList)){
                return Result.success("添加成功");
            }
        }
        return Result.error("添加失败");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean del(Long[] ids) {
        int i = childrenInfoMapper.deleteBatchIds(Arrays.asList(ids));
        if ( i == ids.length ){
            for(Long id : ids) {
                if (!childrenInfoAttachService.remove(new QueryWrapper<ChildrenInfoAttach>().eq("children_info_id", id))) {
                    throw new ServiceException("删除失败");
                }
                List<FatherComment> fatherCommentList = fatherCommentMapper.selectList(Wrappers.<FatherComment>query().eq("children_info_id", id));
                if (ObjectUtil.isNotEmpty(fatherCommentList)) {
                    List<Long> fatherIds = fatherCommentList.stream().map(FatherComment::getId).collect(Collectors.toList());
                    if (fatherCommentMapper.deleteBatchIds(fatherIds) > 0) {
                        for (Long sonId : fatherIds) {
                            List<SonComment> sonCommentList = sonCommentMapper.selectList(Wrappers.<SonComment>query().eq("father_comment_id", sonId));
                            if (ObjectUtil.isNotEmpty(sonCommentList)) {
                                List<Long> sonIds = sonCommentList.stream().map(SonComment::getId).collect(Collectors.toList());
                                if (!(sonCommentMapper.deleteBatchIds(sonIds) > 0)) {
                                    throw new ServiceException("删除失败");
                                }
                            }
                        }
                    } else {
                        throw new ServiceException("删除失败");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateChildrenInfo(ChildrenInfoPo childrenInfoPo) {
        ChildrenInfo childrenInfo = new ChildrenInfo();
        BeanUtil.copyProperties(childrenInfoPo,childrenInfo);
        int i = childrenInfoMapper.updateById(childrenInfo);
//        if ( i > 0 ){
//            return childrenInfoAttachService.saveBatch(childrenInfoPo.getChildrenInfoAttachList());
//        }
        return i > 0 ;
    }

    @Override
    public List<CommentVo> getCommentByChildId(Long id) {
        /**
         * [
         *     {
         *         "id": 1,
         *         "userId": 1,
         *         "userCode": "admin",
         *         "replayId": null,
         *         "replayCode": null,
         *         "commentUser": {
         *             "id": 1,
         *             "nickName": "admin",
         *             "avatar": "https://file.7b114.xyz/blog_avater/2022/04/04/1649052164482413.jpg"
         *         },
         *         "targetUserVo": null,
         *         "content": "很好",
         *         "createDate": "2022-03-30 19:05:43",
         *         "childrenList": [
         *             {
         *                 "id": 1,
         *                 "userId": 1,
         *                 "userCode": "admin",
         *                 "replayId": 1,
         *                 "replayCode": "mr.li",
         *                 "commentUser": {
         *                     "id": 1,
         *                     "nickName": "admin",
         *                     "avatar": "https://file.7b114.xyz/blog_avater/2022/04/04/1649052164482413.jpg"
         *                 },
         *                 "targetUserVo": {
         *                     "id": 1,
         *                     "nickName": "mr.li",
         *                     "avatar": "https://file.7b114.xyz/blog_avater/2022/04/04/1649052164482413.jpg"
         *                 },
         *                 "content": "可以",
         *                 "createDate": "2022-03-30 20:01:15",
         *                 "childrenList": []
         *             }
         *         ]
         *     }
         * ]
         */
        List<CommentVo> commentList = fatherCommentMapper.getCommentByChilId(id);
        for(CommentVo fatherComment : commentList){
            User commentUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", fatherComment.getUserId()));
            CommentUserVo commentUserVo = new CommentUserVo();
            commentUserVo.setId(fatherComment.getUserId());
            commentUserVo.setNickName(fatherComment.getUserCode());
            commentUserVo.setAvatar(commentUser.getAvatarImg());
            // 填充信息
            fatherComment.setCommentUser(commentUserVo);


            List<CommentVo> sonCommentList = sonCommentMapper.getSonCommentByFatherCommentId(fatherComment.getId());
            for(CommentVo  sonComment : sonCommentList ){
                // 评论人的信息
                User sonCommentUser = userMapper
                        .selectOne(new QueryWrapper<User>()
                                .eq("user_id", sonComment.getUserId()));
                CommentUserVo commentUserVo1 = new CommentUserVo();
                commentUserVo1.setId(fatherComment.getUserId());
                commentUserVo1.setNickName(fatherComment.getUserCode());
                commentUserVo1.setAvatar(sonCommentUser.getAvatarImg());

                // 填充信息
                sonComment.setCommentUser(commentUserVo1);

                // 被回复人的信息
                User replayUser = userMapper
                        .selectOne(new QueryWrapper<User>()
                                .eq("user_id", sonComment.getReplayId()));
                TargetUserVo targetUserVo = new TargetUserVo();
                targetUserVo.setId(sonComment.getReplayId());
                targetUserVo.setNickName(sonComment.getReplayCode());
                targetUserVo.setAvatar(replayUser.getAvatarImg());
                // 填入信息
                sonComment.setTargetUser(targetUserVo);
            }
            fatherComment.setChildrenList(sonCommentList);
        }
        return commentList;
    }

    @Override
    public PageVo<ChildrenInfo> listAndPicAttachByPage(ChildrenInfoPo childrenInfoPo) {
        PageVo<ChildrenInfo> pageVo = listByPage(childrenInfoPo);
        List<ChildrenInfo> childrenInfoList = pageVo.getList()
                .stream()
                .peek(childrenInfo -> {
                    List<ChildrenInfoAttach> attachList = childrenInfoAttachService.list(new QueryWrapper<ChildrenInfoAttach>()
                            .eq("children_info_id", childrenInfo.getChildrenId()));
                    if (attachList == null) {
                        childrenInfo.setPic("");
                    } else {
                        String pic = attachList.get(0).getPic();
                        childrenInfo.setPic(pic);
                    }
                })
                .collect(Collectors.toList());

        pageVo.setList(childrenInfoList);
        return pageVo;
    }

    @Override
    public Long findChildrenInfoNum(ChildrenInfoPo childrenInfoPo) {
        // 查询在规定时间内的儿童信息个数
        int childrenNum = childrenInfoMapper.selectList(Wrappers.<ChildrenInfo>lambdaQuery()
                .between(StringUtils.isNotBlank(childrenInfoPo.getStartTime()) && StringUtils.isNotBlank(childrenInfoPo.getEndTime())
                        , ChildrenInfo::getCreateTime
                        , childrenInfoPo.getStartTime()
                        , childrenInfoPo.getEndTime())).size();
        return (long)childrenNum;
    }


}
