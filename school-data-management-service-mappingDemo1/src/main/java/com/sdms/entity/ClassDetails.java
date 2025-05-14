package com.sdms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_details_test",schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_Id")
    private Integer classId;
    @ManyToOne
    @JoinColumn(name = "class_teacher_fk")
    private TeacherDetails classTeacherName;
    @Column
    private Integer noOfStudents;
    @Column
    private Integer standard;
    @Column
    private Integer presentStudents;
    @Column
    private Long year;
    @Column
    private String sectionName;
    @Column
    private String status;
    @Column
    private String classUNID;
//    @OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_sect_fk")
//    private SectionDetails section;

    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;
    
    
    
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @OneToMany(targetEntity = StudentDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "class_stud_fk", referencedColumnName = "class_Id")
    private List<StudentDetails> students = new ArrayList<>();

    private Integer totalStudents;
    
    public TeacherDetails getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(TeacherDetails classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(Integer noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(Integer presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getClassUNID() {
        return classUNID;
    }

    public void setClassUNID(String classUNID) {
        this.classUNID = classUNID;
    }

//    public SectionDetails getSection() {
//        return section;
//    }
//
//    public void setSection(SectionDetails section) {
//        this.section = section;
//    }

    public List<StudentDetails> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDetails> students) {
        this.students = students;
    }

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}
}
