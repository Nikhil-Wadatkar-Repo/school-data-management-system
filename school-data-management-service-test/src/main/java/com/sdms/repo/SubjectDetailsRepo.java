package com.sdms.repo;

//import com.sdms.entity.Class_1_Details;
import com.sdms.entity.SubjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectDetailsRepo extends JpaRepository<SubjectDetails, Integer> {

}
