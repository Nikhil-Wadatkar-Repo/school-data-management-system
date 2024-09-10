package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class SectionNameId {
    private String sectionName;
    private Integer sectionId;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}
