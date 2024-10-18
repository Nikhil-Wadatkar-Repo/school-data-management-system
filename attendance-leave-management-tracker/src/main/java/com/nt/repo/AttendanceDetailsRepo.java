package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.AttendanceDetails;

@Repository
public interface AttendanceDetailsRepo extends JpaRepository<AttendanceDetails, Integer>{

}
