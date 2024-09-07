package com.sdms.dto;

import org.springframework.stereotype.Component;
@Component
public interface ClassDetailsView {
	String getClassName();

	Integer getClassId();

	Integer getStandard();

}
