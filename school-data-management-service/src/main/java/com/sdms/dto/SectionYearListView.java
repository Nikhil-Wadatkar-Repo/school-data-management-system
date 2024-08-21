package com.sdms.dto;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
@Builder
@Component
public class SectionYearListView {


    private List<Section> sectionDetails;
    private List<Long> years;

    public List<Section> getSectionDetails() {
        return sectionDetails;
    }

    public void setSectionDetails(List<Section> sectionDetails) {
        this.sectionDetails = sectionDetails;
    }

    public List<Long> getYears() {
        return years;
    }

    public void setYears(List<Long> years) {
        this.years = years;
    }
}

