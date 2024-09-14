package com.sdms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "section_details_test")
@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDetails implements Serializable {
    @Id
    @Column(name = "sect_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sectionID;
    @Column
    private String sectionName;
    @Column
    private Integer year;
    @Column
    private String status;
    @Column
    private String sectionUNID;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_teacher_fk")
    private TeacherDetails classTeacherName;
    @OneToMany(targetEntity = StudentDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sect_stud_fk", referencedColumnName = "sect_Id")
    private List<StudentDetails> students = new ArrayList<>();

    public Integer getSectionID() {
        return sectionID;
    }

    public void setSectionID(Integer sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TeacherDetails getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(TeacherDetails classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public List<StudentDetails> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDetails> students) {
        this.students = students;
    }
}
