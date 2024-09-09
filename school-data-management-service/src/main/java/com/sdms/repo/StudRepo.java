package com.sdms.repo;

import com.sdms.entity.Book;
import com.sdms.entity.StudDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudRepo extends JpaRepository<StudDetails, Integer> {
}
