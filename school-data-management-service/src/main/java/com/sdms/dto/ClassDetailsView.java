package com.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailsView {
    private String section;
    private Long year;
    private Integer noOfStudents;
    private Integer standards;
    private Integer classId;
    private String classTeacherName;
    private Integer presentStudents;

}
