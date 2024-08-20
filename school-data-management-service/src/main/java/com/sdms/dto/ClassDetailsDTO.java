package com.sdms.dto;

import com.sdms.entity.SectionDetails;
import com.sdms.entity.TeacherDetails;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
public class ClassDetailsDTO {
    private Long section;
    private Integer noOfStudents;
    private Integer standards;
    private Long year;
    private Long classTeacherName;
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

    public Long getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(Long classTeacherName) {
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

    public Long getSection() {
        return section;
    }

    public void setSection(Long section) {
        this.section = section;
    }
}
