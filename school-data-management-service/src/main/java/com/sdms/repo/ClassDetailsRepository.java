package com.sdms.repo;


import com.sdms.entity.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

}