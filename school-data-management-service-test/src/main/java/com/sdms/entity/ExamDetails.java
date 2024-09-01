package com.sdms.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String examName;
    @Column
    private String year;
    @Column
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
