package com.yunai.service.impl;

import com.yunai.mapper.CommentMapper;
import com.yunai.mapper.ImageMapper;
import com.yunai.mapper.UserImageMapper;
import com.yunai.pojo.Comment;
import com.yunai.pojo.UserImage;
import com.yunai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserImageMapper userImageMapper;
    @Override
    public void userLike(String userId,Integer num){
        if (num==1){
            userImageMapper.addlike(userId);
        }
        else {
            userImageMapper.cancalLike(userId);
        }

    }

    @Override
    public void userDislike(String userId,Integer num){
        if (num==1){
            userImageMapper.addDislike(userId);
        }
        else {
            userImageMapper.cancalDislike(userId);
        }
    }

    @Override
    public void updateUserImageInfo(UserImage userImage){
        userImageMapper.insert(userImage);

    }
    @Override
    public void userComment(Comment comment){
        commentMapper.insert(comment);
    };

    @Override
    public List<Comment> showComment(String userId){
        Example commentExample = new Example(Comment.class);
        Example.Criteria criteria = commentExample.createCriteria();
        criteria.andEqualTo("fromImageId",userId);

        return commentMapper.selectByExample(commentExample);

    }
}
