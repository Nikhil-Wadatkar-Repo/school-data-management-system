package com.sdms.repo;

import com.sdms.entity.PostDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostDetails, Integer> {
}
