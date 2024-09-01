package com.sdms.repo;


import com.sdms.dto.SectinoView;
import com.sdms.dto.SectionYearByStdView;
import com.sdms.entity.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

    String query1="select * from class_details cd  where cd.class_sect_fk= :section and  cd.year = :year and cd.standard = :std";
    @Query(value = query1,nativeQuery = true)
    List<ClassDetails> getFilteredData(@Param("year") Integer year,@Param("section") Long section,@Param("std") Integer std);

    String distinctSections="select sd.sectionid as sectionId,sd.section_name as sectionName from section_details sd , class_details cd where cd.class_sect_fk =sd.sectionid";
    @Query(nativeQuery = true,value = distinctSections)
    List<SectinoView> getDistinctSection();

    String sectionYearByStd="select (select sd.sectionid from section_details sd where cd.class_sect_fk=sd.sectionid) as sectionIds, (select sd.section_name from section_details sd where cd.class_sect_fk=sd.sectionid) as sectionNames, cd.year as years from class_details cd where cd.standard =  :std";
    @Query(nativeQuery = true,value =sectionYearByStd)
    List<SectionYearByStdView> getSectionYearByStandard(@Param("std") Integer std);

}