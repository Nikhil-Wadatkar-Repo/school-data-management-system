package com.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamNameExamIdStd {
    private Integer std;
    private List<ExamIDExamName> list;

    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }

    public List<ExamIDExamName> getList() {
        return list;
    }

    public void setList(List<ExamIDExamName> list) {
        this.list = list;
    }
}
