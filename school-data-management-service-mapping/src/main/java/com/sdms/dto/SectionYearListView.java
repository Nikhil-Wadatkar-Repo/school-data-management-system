package com.sdms.dto;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
@Builder
@Component
public class SectionYearListView {


    private List<NewSectionDTO> sectionDetails;
    private List<Long> years;

    public List<NewSectionDTO> getSectionDetails() {
        return sectionDetails;
    }

    public void setSectionDetails(List<NewSectionDTO> sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

    public List<Long> getYears() {
        return years;
    }

    public void setYears(List<Long> years) {
        this.years = years;
    }
}

