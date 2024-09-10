package com.sdms.dto;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClassSectionYearDTO {
private Integer std;
private List<SectionNameId> sections;
private List<Integer> years;


    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }

    public List<SectionNameId> getSections() {
        return sections;
    }

    public void setSections(List<SectionNameId> sections) {
        this.sections = sections;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }
}


