package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public interface StudentDetailsView {
    String getStudName();

    Integer getStudId();

    String getStudUnid();


}
