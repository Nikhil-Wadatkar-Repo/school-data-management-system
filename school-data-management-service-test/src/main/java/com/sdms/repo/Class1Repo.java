//package com.sdms.repo;
//
//import com.sdms.entity.AddressDetails;
//import com.sdms.entity.Class_1_Details;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface Class1Repo extends JpaRepository<Class_1_Details, Long> {
//
//    @Query(value = "select * from Class_1_Details cd where cd.studUNID = ?1 ", nativeQuery = true)
//    Optional<Class_1_Details> findByStdUNID( String unid);
//}
