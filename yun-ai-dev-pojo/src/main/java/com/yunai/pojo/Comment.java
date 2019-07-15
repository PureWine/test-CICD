package com.yunai.pojo;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(value ="comment对象" ,description = "评论对象user")
public class Comment implements Serializable {
    private static final long serialVersionUID = -3537700012563111432L;
    @Id
    @Column(name = "c_id")
    private String cId;

    @Column(name = "from_image_id")
    private String fromImageId;

    @Column(name = "father_comment_id")
    private String fatherCommentId;

    private String comment;

    /**
     * @return c_id
     */
    public String getcId() {
        return cId;
    }

    /**
     * @param cId
     */
    public void setcId(String cId) {
        this.cId = cId;
    }

    /**
     * @return from_image_id
     */
    public String getFromImageId() {
        return fromImageId;
    }

    /**
     * @param fromImageId
     */
    public void setFromImageId(String fromImageId) {
        this.fromImageId = fromImageId;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return fatherCommentId;
     */
    public String getFatherCommentId() {
        return fatherCommentId;
    }

    /**
     * @param fatherCommentId;
     */
    public void setFatherCommentId(String fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }
}