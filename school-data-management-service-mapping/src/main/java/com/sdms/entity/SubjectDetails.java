package com.sdms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_details_test")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDetails {
    @Id
    @Column(name = "subject_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subId;
    @Column
    private String subject1Name;
    @Column
    private String subject2Name;
    @Column
    private String subject3Name;
    @Column
    private String subject4Name;
    @Column
    private String subject5Name;
    @Column
    private Integer subject1totalMarks;
    @Column
    private Integer subject2totalMarks;
    @Column
    private Integer subject3totalMarks;
    @Column
    private Integer subject4totalMarks;
    @Column
    private Integer subject5totalMarks;
    @Column
    private Integer subject1ObtainedMarks;
    @Column
    private Integer subject2ObtainedMarks;
    @Column
    private Integer subject3ObtainedMarks;
    @Column
    private Integer subject4ObtainedMarks;
    @Column
    private Integer subject5ObtainedMarks;
    @Column
    private Integer totalMarks;
    @Column
    private Integer totalObtainedMarks;
    @Column
    private Float percentage;

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubject1Name() {
        return subject1Name;
    }

    public void setSubject1Name(String subject1Name) {
        this.subject1Name = subject1Name;
    }

    public String getSubject2Name() {
        return subject2Name;
    }

    public void setSubject2Name(String subject2Name) {
        this.subject2Name = subject2Name;
    }

    public String getSubject3Name() {
        return subject3Name;
    }

    public void setSubject3Name(String subject3Name) {
        this.subject3Name = subject3Name;
    }

    public String getSubject4Name() {
        return subject4Name;
    }

    public void setSubject4Name(String subject4Name) {
        this.subject4Name = subject4Name;
    }

    public String getSubject5Name() {
        return subject5Name;
    }

    public void setSubject5Name(String subject5Name) {
        this.subject5Name = subject5Name;
    }

    public Integer getSubject1totalMarks() {
        return subject1totalMarks;
    }

    public void setSubject1totalMarks(Integer subject1totalMarks) {
        this.subject1totalMarks = subject1totalMarks;
    }

    public Integer getSubject2totalMarks() {
        return subject2totalMarks;
    }

    public void setSubject2totalMarks(Integer subject2totalMarks) {
        this.subject2totalMarks = subject2totalMarks;
    }

    public Integer getSubject3totalMarks() {
        return subject3totalMarks;
    }

    public void setSubject3totalMarks(Integer subject3totalMarks) {
        this.subject3totalMarks = subject3totalMarks;
    }

    public Integer getSubject4totalMarks() {
        return subject4totalMarks;
    }

    public void setSubject4totalMarks(Integer subject4totalMarks) {
        this.subject4totalMarks = subject4totalMarks;
    }

    public Integer getSubject5totalMarks() {
        return subject5totalMarks;
    }

    public void setSubject5totalMarks(Integer subject5totalMarks) {
        this.subject5totalMarks = subject5totalMarks;
    }

    public Integer getSubject1ObtainedMarks() {
        return subject1ObtainedMarks;
    }

    public void setSubject1ObtainedMarks(Integer subject1ObtainedMarks) {
        this.subject1ObtainedMarks = subject1ObtainedMarks;
    }

    public Integer getSubject2ObtainedMarks() {
        return subject2ObtainedMarks;
    }

    public void setSubject2ObtainedMarks(Integer subject2ObtainedMarks) {
        this.subject2ObtainedMarks = subject2ObtainedMarks;
    }

    public Integer getSubject3ObtainedMarks() {
        return subject3ObtainedMarks;
    }

    public void setSubject3ObtainedMarks(Integer subject3ObtainedMarks) {
        this.subject3ObtainedMarks = subject3ObtainedMarks;
    }

    public Integer getSubject4ObtainedMarks() {
        return subject4ObtainedMarks;
    }

    public void setSubject4ObtainedMarks(Integer subject4ObtainedMarks) {
        this.subject4ObtainedMarks = subject4ObtainedMarks;
    }

    public Integer getSubject5ObtainedMarks() {
        return subject5ObtainedMarks;
    }

    public void setSubject5ObtainedMarks(Integer subject5ObtainedMarks) {
        this.subject5ObtainedMarks = subject5ObtainedMarks;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getTotalObtainedMarks() {
        return totalObtainedMarks;
    }

    public void setTotalObtainedMarks(Integer totalObtainedMarks) {
        this.totalObtainedMarks = totalObtainedMarks;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }
}
