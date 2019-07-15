package com.yunai.mapper;


import com.yunai.pojo.UserImage;
import com.yunai.utils.MyMapper;
import com.yunai.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;


@CacheNamespace(implementation = RedisCache.class)
public interface UserImageMapper extends MyMapper<UserImage> {
    public int addlike( String userId );
    public int cancalLike( String userId );
    public int addDislike( String userId );
    public int cancalDislike( String userId );
}