package com.sdms.repo;

import com.sdms.dto.ClassDetailsView;
import com.sdms.dto.SectinoView;
import com.sdms.dto.SectionYearByStdView;
import com.sdms.entity.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

	String query1 = "select * from class_details cd  where cd.class_sect_fk= :section and  cd.year = :year and cd.standard = :std";

	@Query(value = query1, nativeQuery = true)
	List<ClassDetails> getFilteredData(@Param("year") Integer year, @Param("section") Long section,
			@Param("std") Integer std);

	String distinctSections = "select sd.sectionid as sectionId,sd.section_name as sectionName from section_details sd , class_details cd where cd.class_sect_fk =sd.sectionid";

	@Query(nativeQuery = true, value = distinctSections)
	List<SectinoView> getDistinctSection();

	String sectionYearByStd = "select (select sd.sectionid from section_details sd where cd.class_sect_fk=sd.sectionid) as sectionIds, (select sd.section_name from section_details sd where cd.class_sect_fk=sd.sectionid) as sectionNames, cd.year as years from class_details cd where cd.standard =  :std";

	@Query(nativeQuery = true, value = sectionYearByStd)
	List<SectionYearByStdView> getSectionYearByStandard(@Param("std") Integer std);

	@Query(nativeQuery = true, value = "select  cdt.class_id as classId, cdt.classunid as className,cdt.standard as standard from class_details_test cdt")
	List<ClassDetailsView> getAllStandards();
	
	Optional<ClassDetails> findByStandard(Integer std);
	
	@Query(nativeQuery = true, value = "select sdt.section_name as sectionName,sdt2.name as studentName, cdt.standard as standard from class_details_test cdt , section_details_test sdt ,student_details_test sdt2 where cdt.standard = :standard and sdt.section_name = :section")
	Optional<List> getStudentClassSectionStandardWise(Integer standard,String section);
}