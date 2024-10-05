package com.sdms.service;


import com.sdms.entity.TeacherDetails;
import com.sdms.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
	

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDetails> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherDetails> getTeacherById(Integer teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public TeacherDetails saveTeacher(TeacherDetails teacher) {
//    	teacher.setStatus("active");
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Integer teacherId) {
        teacherRepository.deleteById(teacherId);
    }
    
    public void uploadTeachers() {
        
    }
}