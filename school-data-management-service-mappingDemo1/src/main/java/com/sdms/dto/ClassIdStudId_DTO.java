package com.sdms.dto;

import org.springframework.stereotype.Component;

@Component
public class ClassIdStudId_DTO {
//	{
//	   
//	    "contact": 5454545454,
//	    "pincode": 500038,
//	    "name": "Ankur Wadatkar 11",
//	    "city": "Hyderabad",
//	    "email": "test8",
//	    "status": "Active",
//	    "assignedStatus": null,
//	    "studUNID": "Ankur Wadatkar 9Hyderabad",
//	    "dob": "2024-11-19",
//	    "examName": null,
//	    "year": null,
//	    "createdBy": null,
//	    "updatedBy": null,
//	    "createdDate": null,
//	    "updatedDate": null,
//	    "examDetails": [],
//	    "classId": "Kakaji"
//	}
    private String sectionName;
    private Integer studId;
    private Integer standard;


    public Integer getStandard() {
		return standard;
	}

	public void setStandard(Integer standard) {
		this.standard = standard;
	}

	

    public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }
}
