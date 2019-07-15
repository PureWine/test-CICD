package com.yunai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunai.mapper.ImageMapper;
import com.yunai.pojo.Image;
import com.yunai.service.ImageService;
import com.yunai.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void updateImageInfo(Image image){
//        image.getId();
//        image.getImage();
//        image.getImageId();
        imageMapper.insert(image);
    }

    @Override
    public PagedResult showImageInfo(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Image> imageList=imageMapper.selectAll();
        PageInfo<Image> imagePageInfo=new PageInfo<>(imageList);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(imagePageInfo.getPages());
        pagedResult.setRows(imageList);
        pagedResult.setRecords(imagePageInfo.getTotal());
        return pagedResult;



    }

}
