package com.sdms.repo;


import com.sdms.entity.SectionDetails;
import com.sdms.entity.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SectionDetails, Long> {
}