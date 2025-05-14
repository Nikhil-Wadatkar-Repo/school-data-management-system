package com.sdms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_details_test",schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_Id")
    private Integer classId;
    @ManyToOne
    @JoinColumn(name = "class_teacher_fk")
    private TeacherDetails classTeacherName;
    @Column
    private Integer noOfStudents;
    @Column
    private Integer standard;
    @Column
    private Integer presentStudents;
    @Column
    private Integer year;

    @Column
    private String classUNID;

    @OneToMany(targetEntity = SectionDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_sect_fk", referencedColumnName = "class_Id")
    private List<SectionDetails> sections = new ArrayList<>();

    public TeacherDetails getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(TeacherDetails classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getClassUNID() {
        return classUNID;
    }

    public void setClassUNID(String classUNID) {
        this.classUNID = classUNID;
    }

    public List<SectionDetails> getSections() {
        return sections;
    }

    public void setSections(List<SectionDetails> sections) {
        this.sections = sections;
    }
}
