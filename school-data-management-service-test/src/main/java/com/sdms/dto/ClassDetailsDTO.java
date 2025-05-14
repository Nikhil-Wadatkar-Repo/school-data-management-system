package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class ClassDetailsDTO {
    private Integer section;
    private Integer noOfStudents;
    private Integer standards;
    private Integer year;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(Integer noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }
}
