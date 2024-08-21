package com.sdms.service;


import com.sdms.dto.ClassDetailsDTO;
import com.sdms.dto.ClassDetailsView;
import com.sdms.dto.FilteredClassReq;
import com.sdms.entity.ClassDetails;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StudentDetails;
import com.sdms.entity.TeacherDetails;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.SectionRepository;
import com.sdms.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ClassDetailsService {


    @Autowired
    private ClassDetailsRepository classRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SectionRepository sectionRepository;

    public List<Integer> getDistinctStandards() {
        return classRepository.findAll().stream().map(ClassDetails::getStandard).collect(Collectors.toList());
    }

    public List<Long> getDistinctYears() {
        return classRepository.findAll().stream().map(ClassDetails::getYear).collect(Collectors.toList());
    }

    public List<String> getDistinctSections() {
        List<String> sections = new ArrayList<>();
//        classRepository.findAll().stream().map(ClassDetails::getYear).collect(Collectors.toList());
        return null;
    }

    public List<ClassDetailsView> getFilteredClass(FilteredClassReq filteredClassReq) {
        List<ClassDetailsView> detailsViews = new ArrayList<>();
        List<ClassDetails> classDetailsList = classRepository.getFilteredData(filteredClassReq.getYear(), filteredClassReq.getSection(), filteredClassReq.getStd());
        classDetailsList.forEach(item -> {
            detailsViews.add(ClassDetailsView.builder()
                    .classId(item.getClassId())
                    .classTeacherName(item.getClassTeacherName().getName())
                    .noOfStudents(item.getNoOfStudents())
                    .year(item.getYear()).section(item.getSection().get(0).getSectionName())
                    .standards(item.getStandard())
                    .presentStudents(item.getPresentStudents())
                    .build());
        });
        return detailsViews;
    }


    public List<ClassDetailsView> getAllClassDetailsView() {
        List<ClassDetailsView> detailsViews = new ArrayList<>();

        List<ClassDetails> classDetailsList = classRepository.findAll();
        classDetailsList.forEach(item -> {
            detailsViews.add(ClassDetailsView.builder()
                    .classId(item.getClassId())
                    .classTeacherName(item.getClassTeacherName().getName())
                    .noOfStudents(item.getNoOfStudents())
                    .year(item.getYear()).section(item.getSection().get(0).getSectionName())
                    .standards(item.getStandard())
                    .presentStudents(item.getPresentStudents())
                    .build());
        });
        return detailsViews;
    }

    public List<ClassDetails> getAllClass() {
        return classRepository.findAll();
    }

    public Optional<ClassDetails> getClassDetailsById(Long userId) {
        return classRepository.findById(userId);
    }

    public List<StudentDetails> getStudentsByClassId(Long userId) {
        return classRepository.findById(userId).get().getCommentList();
    }

    public StudentDetails getStudentByClassId(Long classId, Long studId) {
        AtomicReference<StudentDetails> studentDetails = new AtomicReference<>();
        List<StudentDetails> commentList = classRepository.findById(classId).get()
                .getCommentList();
        commentList.forEach(ii -> {
            if (ii.getStudId() == studId) {
                studentDetails.set(ii);
            }
        });
        return studentDetails.get();
    }


    public ClassDetails saveClassDetails(ClassDetailsDTO detailsDTO) {
        ClassDetails classDetails = ClassDetails.builder()
                .year(detailsDTO.getYear())
                .noOfStudents(detailsDTO.getNoOfStudents())
                .classTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()).get())
                .section((List<SectionDetails>) sectionRepository.findById(detailsDTO.getSection()).get())
                .standard(detailsDTO.getStandards())
                .build();

        ClassDetails saved = classRepository.save(classDetails);
        return saved;
    }

    public void deleteClassDetails(Long userId) {
        classRepository.deleteById(userId);
    }


}