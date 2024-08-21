package com.sdms.repo;

import com.sdms.entity.AddressDetails;
import com.sdms.entity.PostDetails;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<AddressDetails, Integer> {
}
