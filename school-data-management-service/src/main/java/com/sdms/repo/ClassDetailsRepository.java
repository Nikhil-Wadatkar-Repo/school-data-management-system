package com.sdms.repo;


import com.sdms.dto.ClassDetailsView;
import com.sdms.entity.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

    String query1="select * from class_details cd where \n" +
            "cd.year = ':year' \n" +
            "and cd.standard = ':std'\n" +
            "and \n" +
            "cd.class_sect_fk = \n" +
            "(select sd.sectionid from section_details sd where sd.section_name = ':section')";
    @Query(value = query1,nativeQuery = true)
    List<ClassDetails> getFilteredData(Integer year,String section,Integer std);

}