package com.sdms.service;

import com.sdms.entity.Class_1_Details;
import com.sdms.entity.Class_2_Details;
import com.sdms.repo.Class1Repo;
import com.sdms.repo.Class2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversalService {

    @Autowired
    private Class1Repo class1Repo;
    @Autowired
    private Class2Repo class2Repo;

    @Value("${class1.subject1}")
    private String subject1;
    @Value("${class1.subject2}")
    private String subject2;
    @Value("${class1.subject3}")
    private String subject3;
    @Value("${class1.subject4}")
    private String subject4;
    @Value("${class1.subject5}")
    private String subject5;

    public Class_1_Details saveClass1Details(Class_1_Details class_1_details) {
        Class_1_Details saved = null;
        try {
            class_1_details.setSubject1Name(subject1);
            class_1_details.setSubject2Name(subject2);
            class_1_details.setSubject3Name(subject3);
            class_1_details.setSubject4Name(subject4);
            class_1_details.setSubject5Name(subject5);
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

    public Class_1_Details getClass1DetailsBySTDUNID(String unid) {
        Optional<Class_1_Details> byStdUNIDOptional = class1Repo.findByStdUNID(unid);
        if (byStdUNIDOptional.isPresent())
            return byStdUNIDOptional.get();
        return null;
    }

    public Class_1_Details updateClass1Repo(Class_1_Details class_1_details) {
        Class_1_Details updateClass1Details = updateClass_1_Details(class_1_details);
        Class_1_Details saveClass1Details = null;
        try {
            saveClass1Details = saveClass1Details(updateClass1Details);
            System.out.println("Updated and saved");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return saveClass1Details;
    }

    private Class_1_Details updateClass_1_Details(Class_1_Details newClass_1_details) {
        Class_1_Details existedDetails = getClass1DetailsBySTDUNID(newClass_1_details.getStudUNID());
        if (existedDetails.getSubject1ObtainedMarks() != newClass_1_details.getSubject1ObtainedMarks() ||
                existedDetails.getSubject1ObtainedMarks() == 0)
            existedDetails.setSubject1ObtainedMarks(newClass_1_details.getSubject1ObtainedMarks());
        if (existedDetails.getSubject1ObtainedMarks() != newClass_1_details.getSubject1ObtainedMarks() ||
                existedDetails.getSubject1ObtainedMarks() == 0)
            existedDetails.setSubject1ObtainedMarks(newClass_1_details.getSubject1ObtainedMarks());
        if (existedDetails.getSubject2ObtainedMarks() != newClass_1_details.getSubject2ObtainedMarks() ||
                existedDetails.getSubject2ObtainedMarks() == 0)
            existedDetails.setSubject2ObtainedMarks(newClass_1_details.getSubject2ObtainedMarks());

        if (existedDetails.getSubject3ObtainedMarks() != newClass_1_details.getSubject3ObtainedMarks() ||
                existedDetails.getSubject3ObtainedMarks() == 0)
            existedDetails.setSubject3ObtainedMarks(newClass_1_details.getSubject3ObtainedMarks());

        if (existedDetails.getSubject4ObtainedMarks() != newClass_1_details.getSubject4ObtainedMarks() ||
                existedDetails.getSubject4ObtainedMarks() == 0)
            existedDetails.setSubject4ObtainedMarks(newClass_1_details.getSubject4ObtainedMarks());

        if (existedDetails.getSubject5ObtainedMarks() != newClass_1_details.getSubject5ObtainedMarks() ||
                existedDetails.getSubject5ObtainedMarks() == 0)
            existedDetails.setSubject5ObtainedMarks(newClass_1_details.getSubject5ObtainedMarks());

        existedDetails.setTotalObtainedMarks(existedDetails.getSubject1ObtainedMarks() + existedDetails.getSubject2ObtainedMarks() + existedDetails.getSubject3ObtainedMarks() + existedDetails.getSubject4ObtainedMarks() + existedDetails.getSubject5ObtainedMarks());
        existedDetails.setPercentage(existedDetails.getTotalMarks() * 0.5f);
        return existedDetails;

    }
}
