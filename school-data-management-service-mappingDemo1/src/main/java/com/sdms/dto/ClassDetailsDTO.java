package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class ClassDetailsDTO {
    private String section;
    private Integer noOfStudents;
    private Integer standards;
    private Long year;
    private Integer classTeacherName;
    private Integer presentStudents;

    public Integer getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(Integer presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Integer getStandards() {
        return standards;
    }

    public void setStandards(Integer standards) {
        this.standards = standards;
    }

    public Integer getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(Integer classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Integer getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(Integer noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
