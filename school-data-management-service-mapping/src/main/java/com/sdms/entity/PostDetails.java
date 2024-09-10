package com.sdms.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class PostDetails {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String title;
    @Column(name = "desp")
    private String desc;
    @OneToMany(targetEntity = CommentDetails.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "post_fk", referencedColumnName = "post_id")
    @JsonIgnoreProperties("post")
    private List< CommentDetails > commentList=new ArrayList<>();

    public PostDetails(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<CommentDetails> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDetails> commentList) {
        this.commentList = commentList;
    }
}
