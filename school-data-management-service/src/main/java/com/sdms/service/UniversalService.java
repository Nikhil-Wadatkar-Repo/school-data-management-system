package com.sdms.service;

import com.sdms.entity.Class_1_Details;
import com.sdms.entity.Class_2_Details;
import com.sdms.repo.Class1Repo;
import com.sdms.repo.Class2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversalService {

    @Autowired
    private Class1Repo class1Repo;
    @Autowired
    private Class2Repo class2Repo;

    public Class_1_Details saveClass1Details(Class_1_Details class_1_details) {
        Class_1_Details saved = null;
        try {
            saved = class1Repo.save(class_1_details);
            System.out.println("Save successfully in class 1 std.");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }

    public Class_1_Details addingStudentToClass1Std(String stdUNID) {
        Class_1_Details details = Class_1_Details.builder().studUNID(stdUNID).build();
        return saveClass1Details(details);
    }

    public Class_1_Details getClass1DetailsBySTDUNID(String unid){
        Optional<Class_1_Details> byStdUNIDOptional = class1Repo.findByStdUNID(unid);
        if(byStdUNIDOptional.isPresent())
            return  byStdUNIDOptional.get();
        return null;
    }

    public Class1Repo updateClass1Repo() {
        return class1Repo;
    }
}
