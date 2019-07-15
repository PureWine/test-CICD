package com.yunai.mapper;

import com.yunai.pojo.Image;
import com.yunai.utils.MyMapper;
import com.yunai.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = RedisCache.class)
public interface ImageMapper extends MyMapper<Image> {

}