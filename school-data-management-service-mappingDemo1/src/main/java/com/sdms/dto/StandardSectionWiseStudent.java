package com.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardSectionWiseStudent {
    public String sectionName;
    public String studentName;
    public String assignedStatus;
    public Integer standard;
    public Integer studId;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

	public String getAssignedStatus() {
		return assignedStatus;
	}

	public void setAssignedStatus(String assignedStatus) {
		this.assignedStatus = assignedStatus;
	}
}
