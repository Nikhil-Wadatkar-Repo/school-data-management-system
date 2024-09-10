//package com.sdms.service;
//
//
//import com.sdms.dto.*;
//import com.sdms.entity.ClassDetails;
//import com.sdms.entity.StudentDetails;
//import com.sdms.repo.ClassDetailsRepository;
//import com.sdms.repo.SectionRepository;
//import com.sdms.repo.StudentRepository;
//import com.sdms.repo.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.stream.Collectors;
//
//@Service
//public class ClassDetailsService {
//
//
//    @Autowired
//    private ClassDetailsRepository classRepository;
//    @Autowired
//    private TeacherRepository teacherRepository;
//    @Autowired
//    private SectionRepository sectionRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//
//    public List<Integer> getDistinctStandards() {
//        return classRepository.findAll().stream().map(ClassDetails::getStandard).collect(Collectors.toList());
//    }
//
//    public List<Long> getDistinctYears() {
//        return classRepository.findAll().stream().map(ClassDetails::getYear).collect(Collectors.toList());
//    }
//
//    public List<SectinoView> getDistinctSections() {
//        List<String> sections = new ArrayList<>();
//        return classRepository.getDistinctSection();
//    }
//
//
//    public SectionYearListView getSectionYearByStandard(Integer std) {
//
//        List<SectionYearByStdView> sectionYearByStandard = classRepository.getSectionYearByStandard(std);
//        List<Section> sectionList = new ArrayList<>();
//        sectionYearByStandard.stream().forEach(item -> {
//            sectionList.add(Section.builder()
//                    .id(item.getSectionIds())
//                    .name(item.getSectionNames())
//                    .build());
//        });
//        SectionYearListView sectionYearListView = SectionYearListView.builder()
//                .years(sectionYearByStandard.stream().map(item -> item.getYears()).distinct().collect(Collectors.toList()))
//                .sectionDetails(sectionList)
//                .build();
//        return sectionYearListView;
//    }
//
//    public List<ClassDetailsView> getFilteredClass(FilteredClassReq filteredClassReq) {
//        List<ClassDetailsView> detailsViews = new ArrayList<>();
//
//
//        List<ClassDetails> classDetailsList = classRepository.getFilteredData(filteredClassReq.getYear(), filteredClassReq.getSection(), filteredClassReq.getStd());
//        classDetailsList.forEach(item -> {
//            detailsViews.add(ClassDetailsView.builder()
//                    .classId(item.getClassId())
//                    .classTeacherName(item.getClassTeacherName().getName())
//                    .noOfStudents(item.getNoOfStudents())
//                    .year(item.getYear()).section(item.getSection().getSectionName())
//                    .standards(item.getStandard())
//                    .presentStudents(item.getPresentStudents())
//                    .build());
//        });
//        return detailsViews;
//    }
//
//    public List<StudentDetails> getFilteredStudents(FilteredClassReq filteredClassReq) {
//        List<ClassDetails> classDetailsList = classRepository.getFilteredData(filteredClassReq.getYear(), filteredClassReq.getSection(), filteredClassReq.getStd());
//        return classDetailsList.get(0).getCommentList();
//    }
//
//
//    public List<ClassDetailsView> getAllClassDetailsView() {
//        List<ClassDetailsView> detailsViews = new ArrayList<>();
//
//        List<ClassDetails> classDetailsList = classRepository.findAll();
//        classDetailsList.forEach(item -> {
//            detailsViews.add(ClassDetailsView.builder()
//                    .classId(item.getClassId())
//                    .classTeacherName(item.getClassTeacherName().getName())
//                    .noOfStudents(item.getNoOfStudents())
//                    .year(item.getYear()).section(item.getSection().getSectionName())
//                    .standards(item.getStandard())
//                    .presentStudents(item.getPresentStudents())
//                    .build());
//        });
//        return detailsViews;
//    }
//
//    public List<ClassDetails> getAllClass() {
//        return classRepository.findAll();
//    }
//
//    public Optional<ClassDetails> getClassDetailsById(Long userId) {
//        return classRepository.findById(userId);
//    }
//
//    public List<StudentDetails> getStudentsByClassId(Long userId) {
//        return classRepository.findById(userId).get().getCommentList();
//    }
//
//    public StudentDetails getStudentByClassId(Long classId, Long studId) {
//        AtomicReference<StudentDetails> studentDetails = new AtomicReference<>();
//        List<StudentDetails> commentList = classRepository.findById(classId).get()
//                .getCommentList();
//        commentList.forEach(ii -> {
//            if (ii.getStudId() == studId) {
//                studentDetails.set(ii);
//            }
//        });
//
//        return studentDetails.get();
//    }
//
//    public StudentDetails getStudentById( Long studId) {
//        Optional<StudentDetails> byId = studentRepository.findById(studId);
//
//        return byId.get();
//    }
//
//
//    public ClassDetails saveClassDetails(ClassDetailsDTO detailsDTO) {
//        ClassDetails classDetails = ClassDetails.builder()
//                .year(detailsDTO.getYear())
//                .noOfStudents(detailsDTO.getNoOfStudents())
//                .classTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()).get())
//                .section(sectionRepository.findById(detailsDTO.getSection()).get())
//                .standard(detailsDTO.getStandards())
//                .build();
//
//        ClassDetails saved = classRepository.save(classDetails);
//        return saved;
//    }
//
//    public void deleteClassDetails(Long userId) {
//        classRepository.deleteById(userId);
//    }
//
//
//}