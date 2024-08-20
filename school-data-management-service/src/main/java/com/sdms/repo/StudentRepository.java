package com.sdms.repo;

import com.sdms.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDetails,Long> {
}
