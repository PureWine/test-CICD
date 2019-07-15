package com.yunai.pojo;

import javax.persistence.*;
import java.io.Serializable;

public class Image implements Serializable {
    private static final long serialVersionUID = -5967309664943906486L;
    @Id
    private String id;

    /**
     * 图片id
     */
    @Column(name = "image_id")
    private String imageId;

    /**
     * 图片
     */
    private String image;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取图片id
     *
     * @return image_id - 图片id
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * 设置图片id
     *
     * @param imageId 图片id
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取图片
     *
     * @return image - 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

}