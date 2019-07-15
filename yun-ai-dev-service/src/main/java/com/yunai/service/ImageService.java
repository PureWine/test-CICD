package com.yunai.service;

import com.yunai.pojo.Image;
import com.yunai.utils.PagedResult;
import org.springframework.stereotype.Service;



public interface ImageService {
    /*
    *@Description 上传图片到数据库
     */
    public void updateImageInfo(Image image);

    /**
     * @Desciption 分页读取图片信息
     */
    public PagedResult showImageInfo(Integer page, Integer pageSize);
}
