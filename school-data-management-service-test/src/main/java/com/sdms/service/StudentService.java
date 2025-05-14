package com.sdms.service;


import com.sdms.entity.StudentDetails;
import com.sdms.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDetails> getAllStudentDetails() {
        return studentRepository.findAll();
    }

    public Optional<StudentDetails> getStudentDetailsById(Integer userId) {
        return studentRepository.findById(userId);
    }

    public StudentDetails saveStudentDetails(StudentDetails sectionDetails) {
//    	user.setStatus("active");
        return studentRepository.save(sectionDetails);
    }

    public void deleteStudentDetails(Integer userId) {
        studentRepository.deleteById(userId);
    }
    

}