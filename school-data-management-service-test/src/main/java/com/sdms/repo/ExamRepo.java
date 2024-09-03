package com.sdms.repo;

import com.sdms.entity.ExamDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<ExamDetails, Integer> {
}
