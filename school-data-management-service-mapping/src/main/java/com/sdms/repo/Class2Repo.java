package com.sdms.repo;

import com.sdms.entity.Class_1_Details;
import com.sdms.entity.Class_2_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Class2Repo extends JpaRepository<Class_2_Details, Long> {
}
