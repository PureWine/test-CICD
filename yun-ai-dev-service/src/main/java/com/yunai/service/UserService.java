package com.yunai.service;

import com.yunai.pojo.Comment;
import com.yunai.pojo.UserImage;

import java.util.List;

public interface UserService {

    /**
     * 点赞
     * @param userId
     */
    public void userLike(String userId,Integer num);

    /**
     * 踩
     * @param userId
     */
    public void userDislike(String userId,Integer num);

    public void updateUserImageInfo(UserImage userImage);

    /**
     * 评论
     * @param userId
     * @param num
     */
    public void userComment(Comment comment);



    /**
     * 获取一级评论
     */
    public List<Comment> showComment(String userId);
}
