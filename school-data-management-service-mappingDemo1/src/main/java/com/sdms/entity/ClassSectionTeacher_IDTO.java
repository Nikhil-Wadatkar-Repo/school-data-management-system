package com.sdms.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public interface ClassSectionTeacher_IDTO {
    public Integer getClassId();
    public Integer getNoOfStudents();
    public Integer getPresentStudents();
    public Integer getStandard();
    public Integer getYear();
    public Integer getCount();
    public String getSection();
    public String getTeacher();
}
