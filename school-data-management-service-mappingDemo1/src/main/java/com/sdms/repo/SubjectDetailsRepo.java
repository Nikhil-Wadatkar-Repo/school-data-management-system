package com.sdms.repo;

import com.sdms.entity.SubjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDetailsRepo extends JpaRepository<SubjectDetails, Integer> {

}
