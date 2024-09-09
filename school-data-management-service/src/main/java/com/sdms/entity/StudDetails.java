package com.sdms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudDetails {
    @Id
    private Integer sid;

    private String sname;


    @ManyToOne
    @JoinColumn(name = "bidFk")
    private Book bob;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Book getBob() {
        return bob;
    }

    public void setBob(Book bob) {
        this.bob = bob;
    }
}
