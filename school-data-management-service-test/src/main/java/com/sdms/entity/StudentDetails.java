package com.sdms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="student_details_test")
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
    private String studUNID;
    @Column
    private String dob;


    @OneToMany(targetEntity = ExamDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stud_exam_fk", referencedColumnName = "stud_Id")
    private List<ExamDetails> exams = new ArrayList<>();

    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
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

    public List<ExamDetails> getExams() {
        return exams;
    }

    public void setExams(List<ExamDetails> exams) {
        this.exams = exams;
    }
}