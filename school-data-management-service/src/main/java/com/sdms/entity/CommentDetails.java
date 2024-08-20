package com.sdms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@Entity
@Table
public class CommentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String msg;
//    @ManyToOne
//    @JoinColumn(name = "post_fk", referencedColumnName = "post_id")
//    @JsonIgnoreProperties("commentList")
//    private PostDetails post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public PostDetails getPost() {
//        return post;
//    }
//
//    public void setPost(PostDetails post) {
//        this.post = post;
//    }
}
