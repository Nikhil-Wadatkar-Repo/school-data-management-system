package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class FilteredClassReq {
    private String section;
    private Integer year;
    private Integer std;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }
}
