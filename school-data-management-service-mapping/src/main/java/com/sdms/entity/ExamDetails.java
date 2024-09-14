package com.sdms.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "exam_details_test")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetails {
    @Id
    @Column(name = "exam_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subId;
    @Column
    private String examName;
    @Column
    private Integer year;
    @Column
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "sub_fk")
    private  SubjectDetails subjectDetails;

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SubjectDetails getSubjectDetails() {
        return subjectDetails;
    }

    public void setSubjectDetails(SubjectDetails subjectDetails) {
        this.subjectDetails = subjectDetails;
    }
}
