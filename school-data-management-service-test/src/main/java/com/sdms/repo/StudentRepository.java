package com.sdms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdms.dto.StudentDetailsView;
import com.sdms.entity.StudentDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails,Long> {
	Optional<StudentDetails> findByStudUNID(String studUNID);
	 
	 @Query(value = "select sdt.studunid as studUnid,sdt.name as studName from student_details_test sdt",nativeQuery = true)
	 List<StudentDetailsView> getAllStudent();

	 @Query(value = "select sdt.studunid as studUnid,sdt.name as studName from student_details_test sdt where sdt.name LIKE CONCAT('%',:nameToSearch,'%')",nativeQuery = true)
	 List<StudentDetailsView> getStudentsByName(@Param("nameToSearch") String name);
	 
	
}
