package com.sdms.repo;


import java.util.List;
import java.util.Optional;

import com.sdms.dto.SectionNameAndIdView;
import com.sdms.dto.StandardSectionWiseStudent;
import com.sdms.dto.newDTO.SectionStandardView;
import com.sdms.entity.ClassSectionTeacher_IDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdms.dto.ClassDetailsView;
import com.sdms.dto.ClassIdStudId_DTO;
//import com.sdms.dto.SectionYearByStdView;
import com.sdms.entity.ClassDetails;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Integer> {

	@Query(nativeQuery = true, value = "select * from class_details_test where standard in (:std)")
	List<ClassDetails> findByStandard(Integer std);

	@Query(nativeQuery = true, value = "select sdt.section_name as sectionName,sdt2.name as studentName, cdt.standard as standard from class_details_test cdt , section_details_test sdt ,student_details_test sdt2 where cdt.standard = :standard and sdt.section_name = :section")
	Optional<List<StandardSectionWiseStudent>> getStudentClassSectionStandardWise(Integer standard, String section);

	@Query(nativeQuery = true, value = "select cdt.class_id, cdt.no_of_students ,cdt.present_students ,cdt.standard ,cdt.year, (select sdt2.section_name from section_details_test sdt2 where sdt2.sect_id=cdt.class_sect_fk) as section, (select td.name from teacher_details td where td.teacher_id=cdt.class_teacher_fk) as Teacher from  class_details_test cdt")
	List<ClassSectionTeacher_IDTO> getAllClasses();
	@Query(nativeQuery = true, value = "select standard as standard,count(standard) as count from   class_details_test cdt group by standard")
	List<ClassSectionTeacher_IDTO> getAllClassesCount();

	// all newly implemented method
	@Query(value = "select section_name, standard from class_details_test cdt where standard = :std", nativeQuery = true)
	List<SectionStandardView> getSectionsByStandard(@Param("std") Integer std);
	
	@Query(value = "select * from class_details_test cdt where standard = :std and section_name =:section", nativeQuery = true)
	Optional<ClassDetails> getClassBySectionsAndStandard(@Param("std") Integer std,@Param("section")String section);
}