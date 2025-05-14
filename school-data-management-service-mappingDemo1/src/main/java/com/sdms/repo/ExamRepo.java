package com.sdms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sdms.entity.ExamDetails;

@Repository
public interface ExamRepo extends JpaRepository<ExamDetails, Integer> {

	@Query(value = "select * from exam_details_test edt where exam_stud_fk = :id", nativeQuery = true)
	public List<ExamDetails> getExamDetailsByStudentId(Integer id);
}
