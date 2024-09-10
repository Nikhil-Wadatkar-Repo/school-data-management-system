package com.sdms.repo;


import com.sdms.dto.SectionDetailsView;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.TeacherDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SectionDetails, Long> {
	@Query(nativeQuery = true, value = "select sdt.section_name as sectionName,sdt.sect_id as sectionId from section_details_test sdt")
	List<SectionDetailsView> getAllSections();
	Optional<SectionDetails> findBySectionName(String Name);
	@Query(nativeQuery = true,value = "select * from section_details_test sdt where sdt.sect_id = :id")
	Optional<SectionDetails> getSectionFromStudent(Long id);
}