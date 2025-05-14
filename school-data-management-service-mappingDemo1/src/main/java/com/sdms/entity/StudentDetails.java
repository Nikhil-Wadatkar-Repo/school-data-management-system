package com.sdms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student_details_test")
@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetails {
	@Id
	@Column(name = "stud_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studId;
	private Integer std;
	@Column
	private Long contact;
	@Column
	private Long pincode;
	@Column
	private String name;
	@Column
	private String city;
	@Column
	private String email;
	@Column
	private String status;
	@Column
	private String classAllottementStatus;

	@Column
	private String examAllottmentStatus;

	@Column
	private String studUNID;
	@Column
	private String dob;
	@Column
	private String examName;
	@Column
	private Integer year;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;

	@OneToMany(targetEntity = ExamDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "exam_stud_fk", referencedColumnName = "stud_Id")
	private List<ExamDetails> examDetails = new ArrayList<>();

//    @Column
//    private String subject1Name;
//    @Column
//    private String subject2Name;
//    @Column
//    private String subject3Name;
//    @Column
//    private String subject4Name;
//    @Column
//    private String subject5Name;
//    @Column
//    private Integer subject1totalMarks;
//    @Column
//    private Integer subject2totalMarks;
//    @Column
//    private Integer subject3totalMarks;
//    @Column
//    private Integer subject4totalMarks;
//    @Column
//    private Integer subject5totalMarks;
//    @Column
//    private Integer subject1ObtainedMarks;
//    @Column
//    private Integer subject2ObtainedMarks;
//    @Column
//    private Integer subject3ObtainedMarks;
//    @Column
//    private Integer subject4ObtainedMarks;
//    @Column
//    private Integer subject5ObtainedMarks;
//    @Column
//    private Integer totalMarks;
//    @Column
//    private Integer totalObtainedMarks;
//    @Column
//    private Float percentage;

	public Integer getStudId() {
		return studId;
	}

	public void setStudId(Integer studId) {
		this.studId = studId;
	}

	public Integer getStd() {
		return std;
	}

	public void setStd(Integer std) {
		this.std = std;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudUNID() {
		return studUNID;
	}

	public void setStudUNID(String studUNID) {
		this.studUNID = studUNID;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<ExamDetails> getExamDetails() {
		return examDetails;
	}

	public void setExamDetails(List<ExamDetails> examDetails) {
		this.examDetails = examDetails;
	}

}