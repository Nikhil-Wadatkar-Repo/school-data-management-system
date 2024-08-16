package com.sdms.service;


import com.sdms.dto.ClassDetailsDTO;
import com.sdms.dto.ClassDetailsView;
import com.sdms.entity.ClassDetails;
import com.sdms.entity.SectionDetails;
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
import java.util.stream.Collectors;

@Service
public class ClassDetailsService {


    @Autowired
    private ClassDetailsRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SectionRepository sectionRepository;

    public List<ClassDetailsView> getAllClassDetails() {
        Map<Long, String> sectionMap = sectionRepository.findAll().stream().collect(Collectors.toMap(SectionDetails::getSectionID, SectionDetails::getSectionName));
        Map<Long, String> teacherMap = teacherRepository.findAll().stream().collect(Collectors.toMap(TeacherDetails::getTeacherId, TeacherDetails::getName));
        List<ClassDetailsView> detailsViews=new ArrayList<>();
        userRepository.findAll().forEach(item->{
                detailsViews.add(ClassDetailsView.builder()
                                .classId(item.getClassId())
                                .classTeacherName(teacherMap.get(item.getClassTeacherName()))
                                .noOfStudents(item.getNoOfStudents())
                                .year(item.getYear()).section(sectionMap.get(item.getSection()))
                                .standards(item.getStandard())
                                .presentStudents(item.getPresentStudents())
                        .build());
        });
        return detailsViews;
    }

    public Optional<ClassDetails> getClassDetailsById(Long userId) {
        return userRepository.findById(userId);
    }

    public ClassDetails saveClassDetails(ClassDetailsDTO detailsDTO) {
        ClassDetails classDetails = ClassDetails.builder()
                .year(detailsDTO.getYear())
                .noOfStudents(detailsDTO.getNoOfStudents())
                .classTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()).get().getTeacherId())
                .section(sectionRepository.findById(detailsDTO.getSection()).get().getSectionID())
                .standard(detailsDTO.getStandards())
                .build();

        ClassDetails saved = userRepository.save(classDetails);
        return saved;
    }

    public void deleteClassDetails(Long userId) {
        userRepository.deleteById(userId);
    }


}