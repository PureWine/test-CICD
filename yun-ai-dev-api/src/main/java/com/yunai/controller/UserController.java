package com.yunai.controller;


import com.yunai.pojo.Comment;
import com.yunai.service.UserService;
import com.yunai.utils.AcquireId;
import com.yunai.utils.IMoocJSONResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Api(value="用户操作的接口",tags={"用户操作的controller"})
public class UserController extends BasicController {
    @Autowired
    private UserService userService;
    @CrossOrigin
    @ApiOperation(value ="图片点赞",notes="图片点赞的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "图片ID",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "num",value = "标识数字",required = true,dataType = "Integer",paramType = "query")
    })
    @GetMapping("/userLike")
    public IMoocJSONResult userLike(String userId,Integer num) throws Exception{
        userService.userLike(userId,num);
        return IMoocJSONResult.ok();
    }

    @CrossOrigin
    @ApiOperation(value ="图片踩",notes="图片踩的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "图片ID",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "num",value = "标识数字",required = true,dataType = "Integer",paramType = "query")
    })
    @GetMapping("/userDislike")
    public IMoocJSONResult userDisLike(String userId,Integer num) throws Exception{
        userService.userDislike(userId,num);
        return IMoocJSONResult.ok();
    }

    @CrossOrigin
    @ApiOperation(value ="图片评论",notes="图片评论接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "图片ID",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "userComment",value = "图片评论",required = true,dataType = "string",paramType = "query")
    })
    @GetMapping("/userComment")
    public IMoocJSONResult userComment(String userId,String userComment) throws Exception{
        Comment comment = new Comment();
        comment.setcId(AcquireId.getUUID());
        comment.setFromImageId(userId);
        comment.setComment(userComment);
        userService.userComment(comment);
        return IMoocJSONResult.ok();
    }

    @CrossOrigin
    @ApiOperation(value ="评论的回复",notes="图片评论的回复的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId",value = "评论ID",required = true,dataType = "string",paramType = "query"),
    })
    @PostMapping("/toUserComment")
    public IMoocJSONResult toUserComment(@RequestBody @ApiParam(name="评论对象",value = "传入json格式",required=true) Comment comment, String commentId) throws Exception{
//        comment.setcId(AcquireId.getUUID());
        comment.setFatherCommentId(commentId);
        userService.userComment(comment);
        return IMoocJSONResult.ok();
    }


    @CrossOrigin
    @ApiOperation(value ="图片一级评论展示",notes="图片一级评论展示的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "图片ID",required = true,dataType = "string",paramType = "query"),
    })
    @GetMapping("/showComment")
    public IMoocJSONResult showComment(String userId) throws Exception{
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SEESION,uniqueToken,1000*60*30);
        return IMoocJSONResult.ok(userService.showComment(userId));
    }



}
