package com.sdms.repo;

import com.sdms.entity.CommentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<CommentDetails, Integer> {
}
