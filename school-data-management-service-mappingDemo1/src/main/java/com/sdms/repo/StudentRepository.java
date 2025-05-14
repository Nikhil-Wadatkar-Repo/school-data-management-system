package com.sdms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdms.dto.StandardSectionWiseStudentI;
import com.sdms.dto.StudentDetailsView;
import com.sdms.entity.StudentDetails;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails, Integer> {
    Optional<List<StudentDetails>> findByStudUNID(String studUNID);

    @Query(value = "select * from student_details_test sdt where sdt.studunid = :unid and sdt.name =:name", nativeQuery = true)
    Optional<StudentDetails> getSpecificStudentByStudUNIDAndName(@Param("unid") String studUNID, @Param("name") String name);

    @Query(value = "select sdt.stud_id as studId,sdt.studunid as studUnid,sdt.name as studName from student_details_test sdt", nativeQuery = true)
    List<StudentDetailsView> getAllStudent();

    @Query(value = "select studunid as studUnid,name as studName, stud_id as studId from student_details_test where name LIKE CONCAT('%',:nameToSearch,'%')", nativeQuery = true)
    List<StudentDetailsView> getStudentsByName(@Param("nameToSearch") String name);

    String stdSectionWiseStudent="select sdt.exam_allottment_status as examStatus,sdt.stud_id as studId,sdt.\"name\" as studentName,sdt.assigned_status as assignedStatus,sdt.std as standard,cdt.standard,cdt.section_name as sectionName from class_details_test cdt , student_details_test sdt where \r\n"
    		+ "cdt.class_id =sdt.class_stud_fk and sdt.assigned_status='Allotted'\r\n"
    		+ "and cdt.section_name = :section and (cdt.standard=:std)";
//    @Query(value = "select * from student_details_test sdt where sdt.class_stud_fk = (select cdt.class_id  from class_details_test cdt  where cdt.standard = :std and cdt.class_sect_fk=(select sdt2.sect_id from section_details_test sdt2 where sdt2.section_name=:section))", nativeQuery = true)
    @Query(value = stdSectionWiseStudent, nativeQuery = true)
    List<StandardSectionWiseStudentI> getStudentByStdAndSection(String section, Integer std);
    
    String unmappedstdSectionWiseStudent="select sdt.exam_allottment_status as examStatus,sdt.stud_id as studId,sdt.name as studentName,sdt.assigned_status as assignedStatus,sdt.std as standard,cdt.standard,cdt.section_name as sectionName from class_details_test cdt , student_details_test sdt,exam_details_test edt   where cdt.class_id =sdt.class_stud_fk and sdt.classAllottementStatus='Allotted' and edt.exam_stud_fk = sdt.stud_id and"
    		+ " cdt.section_name = :section and (cdt.standard=:std)";
//    @Query(value = "select * from student_details_test sdt where sdt.class_stud_fk = (select cdt.class_id  from class_details_test cdt  where cdt.standard = :std and cdt.class_sect_fk=(select sdt2.sect_id from section_details_test sdt2 where sdt2.section_name=:section))", nativeQuery = true)
    @Query(value = stdSectionWiseStudent, nativeQuery = true)
    List<StandardSectionWiseStudentI> getExamUnmappedStudentByStdAndSection(String section, Integer std);

    String getAllStudent="select sdt.name as studName,sdt.std as standard, cdt.section_name as sectionName, cdt.class_id as classId,sdt.stud_id as studId  from student_details_test sdt ,class_details_test cdt where sdt.class_stud_fk =cdt.class_id";
    @Query(value = getAllStudent, nativeQuery = true)
    List<StudentDetailsView> getAllStudentsByStandardSection();
    
    


}
