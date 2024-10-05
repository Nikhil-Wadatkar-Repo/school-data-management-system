package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class ExamDetail {
    private Integer subject1TotalMarks;
    private Integer subject2TotalMarks;
    private Integer subject3TotalMarks;
    private Integer subject4TotalMarks;
    private Integer subject5TotalMarks;
    private String  subject1Name;
    private String   subject2Name;
    private String   subject3Name;
    private String   subject4Name;
    private String   subject5Name;
    private String   examName;
    private Integer studId;

    public Integer getSubject1TotalMarks() {
        return subject1TotalMarks;
    }

    public void setSubject1TotalMarks(Integer subject1TotalMarks) {
        this.subject1TotalMarks = subject1TotalMarks;
    }

    public Integer getSubject2TotalMarks() {
        return subject2TotalMarks;
    }

    public void setSubject2TotalMarks(Integer subject2TotalMarks) {
        this.subject2TotalMarks = subject2TotalMarks;
    }

    public Integer getSubject3TotalMarks() {
        return subject3TotalMarks;
    }

    public void setSubject3TotalMarks(Integer subject3TotalMarks) {
        this.subject3TotalMarks = subject3TotalMarks;
    }

    public Integer getSubject4TotalMarks() {
        return subject4TotalMarks;
    }

    public void setSubject4TotalMarks(Integer subject4TotalMarks) {
        this.subject4TotalMarks = subject4TotalMarks;
    }

    public Integer getSubject5TotalMarks() {
        return subject5TotalMarks;
    }

    public void setSubject5TotalMarks(Integer subject5TotalMarks) {
        this.subject5TotalMarks = subject5TotalMarks;
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }
}
