package com.sdms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_details",schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_Id")
    private Integer classId;
    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "class_sect_fk")
    private SectionDetails section;
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


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public SectionDetails getSection() {
        return section;
    }

    public void setSection(SectionDetails section) {
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
