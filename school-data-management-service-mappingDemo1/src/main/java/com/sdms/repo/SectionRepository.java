package com.sdms.repo;


import com.sdms.dto.SectionDetailsView;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StandardWiseSectino_IDTO;
import com.sdms.entity.TeacherDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SectionDetails, Integer> {
	boolean existsBySectionName(String sectionName);
	@Query(nativeQuery = true, value = "select sdt.section_name as sectionName,sdt.sect_id as sectionId from section_details_test sdt")
	List<SectionDetailsView> getAllSections();
//	@Query(nativeQuery = true, value = "select sdt.section_name as sectionName,sdt.sect_id as sectionId from section_details_test sdt")
//	List<SectionDetailsView> getDistinctSections();
	Optional<SectionDetails> findBySectionName(String Name);
	@Query(nativeQuery = true,value = "select sdt.section_name,sdt.sect_id,cdt.class_id from class_details_test cdt, section_details_test sdt where cdt.class_sect_fk= sdt.sect_id and cdt.standard= :std")
	List<StandardWiseSectino_IDTO> getSectionByStandard(@Param("std") Integer std);


}