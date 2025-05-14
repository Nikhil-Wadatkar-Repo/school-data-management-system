package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public interface StudentDetailsView {
	String getStudName();

	Integer getStandard();

	String getSectionName();

	Integer getStudId();

	Integer getClassId();

	String getStudUnid();

}
