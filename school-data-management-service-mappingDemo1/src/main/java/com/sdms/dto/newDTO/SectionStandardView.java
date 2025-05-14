package com.sdms.dto.newDTO;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public interface SectionStandardView {

	String getSectionName();
	Integer getStandard();
}
