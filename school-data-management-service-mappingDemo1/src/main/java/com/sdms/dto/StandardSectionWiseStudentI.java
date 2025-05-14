package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component

public interface StandardSectionWiseStudentI {
    public String getSectionName();
    public String getExamStatus();
    public String getStudentName();
    public String getAssignedStatus();
    public Integer getStandard();
    public Integer getStudId();

   
}
