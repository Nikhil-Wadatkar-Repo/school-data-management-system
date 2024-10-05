package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class ClassIdStudId_DTO {
    private Integer classId;
    private Integer studId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }
}
