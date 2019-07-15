package com.yunai.mapper;

import com.yunai.pojo.Comment;
import com.yunai.utils.MyMapper;
import com.yunai.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = RedisCache.class)
public interface CommentMapper extends MyMapper<Comment> {
}