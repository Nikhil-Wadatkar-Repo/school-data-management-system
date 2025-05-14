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
    private Boolean isMarksAssigned;
    @Column
    private Boolean finalyCalculated;
    @Column
    private String resultStatus;
    @Column
    private String calcStatus;
    @Column
    private String subject1Name;
    @Column
    private String subject2Name;
    @Column
    private String subject3Name;
    @Column
    private String subject4Name;
    @Column
    private String subject5Name;
    @Column
    private Integer subject1totalMarks;
    @Column
    private Integer subject2totalMarks;
    @Column
    private Integer subject3totalMarks;
    @Column
    private Integer subject4totalMarks;
    @Column
    private Integer subject5totalMarks;
    @Column
    private Integer subject1ObtainedMarks;
    @Column
    private Integer subject2ObtainedMarks;
    @Column
    private Integer subject3ObtainedMarks;
    @Column
    private Integer subject4ObtainedMarks;
    @Column
    private Integer subject5ObtainedMarks;
    @Column
    private Integer totalMarks;
    @Column
    private Integer totalObtainedMarks;
    @Column
    private Float percentage;
}
