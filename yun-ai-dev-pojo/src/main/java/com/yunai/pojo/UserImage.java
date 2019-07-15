package com.yunai.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user_image")
public class UserImage implements Serializable {
    private static final long serialVersionUID = 5459981775429404290L;
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_like")
    private Integer userLike;

    private Integer dislike;

    /**
     * @return id
     */
    public String getuserId() {
        return userId;
    }

    /**
     * @param id
     */
    public void setuserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return like
     */
    public Integer getuserLike() {
        return userLike;
    }

    /**
     * @param like
     */
    public void setuserLike(Integer userLike) {
        this.userLike = userLike;
    }

    /**
     * @return dislike
     */
    public Integer getDislike() {
        return dislike;
    }

    /**
     * @param dislike
     */
    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }
}