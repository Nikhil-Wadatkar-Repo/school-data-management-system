package com.sdms.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SectionNameAndIdView {

     Integer getSectionId();
     String getSectionName();
     String getSectionUNID();

}
