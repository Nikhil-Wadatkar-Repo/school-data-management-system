package com.sdms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdms.service.SectionService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_Id")
    private Long classId;
//    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_sect_fk")

    @OneToMany(targetEntity = SectionDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_sect_fk", referencedColumnName = "class_Id")
    private List<SectionDetails> section;
    @Column
    private Integer noOfStudents;
    @Column
    private Integer standard;
    @Column
    private Integer presentStudents;
    @Column
    private Long year;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_teacher_fk")
    private TeacherDetails classTeacherName;
    @Column
    private String classUNID;

    @OneToMany(targetEntity = StudentDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_stud_fk", referencedColumnName = "class_Id")
    private List<StudentDetails> commentList = new ArrayList<>();

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public List<SectionDetails> getSection() {
        return section;
    }

    public void setSection(List<SectionDetails> section) {
        this.section = section;
    }

    public Integer getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(Integer noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(Integer presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public TeacherDetails getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(TeacherDetails classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public String getClassUNID() {
        return classUNID;
    }

    public void setClassUNID(String classUNID) {
        this.classUNID = classUNID;
    }

    public List<StudentDetails> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<StudentDetails> commentList) {
        this.commentList = commentList;
    }
}
