package com.sdms.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.sdms.dto.*;
import com.sdms.entity.*;
import com.sdms.helper.Constants;
import com.sdms.helper.UtilityClass;
import com.sdms.repo.*;
//import com.sdms.utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sdms.exception.SDMSException;


@RestController
@CrossOrigin
public class UniversalController {
    @Autowired
    private ClassDetailsRepository classDetailsRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private SubjectDetailsRepo subjectDetailsRepo;
    @Autowired
    private TeacherRepository teacherRepository;
    private List<SectionDetails> sections;
    @Autowired
    private UtilityClass utilityClass;


    // ========================= Student section ==========================================
   

//    public StudentDetails deactivateStudent(String unid) {
//        StudentDetails byStudUNID = studentRepository.findByStudUNID(unid).get();
//        byStudUNID.setStatus("deactive");
//        return byStudUNID = studentRepository.save(byStudUNID);
//    }

    @GetMapping("/getStudents")//DONE
    public List<StudentDetailsView> getStudents() {
        return studentRepository.getAllStudent();
    }


    

   

    // add existed student to class or section
    //	add new student to class or section
    @GetMapping("/getStudents/{standard}/{section}")
    public Optional<List<StandardSectionWiseStudent>> filterStudentSectionClassWise(String section, Integer standard) {
        return classDetailsRepository.getStudentClassSectionStandardWise(standard, section);
    }

   

    @GetMapping("/getStudentsById/{id}")
    public StudentDetails getStudentDetails(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    }

    @PostMapping("/createTeacher")
    public TeacherDetails createTeacher(@RequestBody TeacherDetails user) {
        return teacherRepository.save(user);
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherDetails> getAllTeachers() {
        return teacherRepository.findAll();
    }
// ================= class section ===============================

    @PostMapping("/createClass")
    public ClassDetails createClass(@RequestBody ClassDetailsDTO detailsDTO) {
        ClassDetails classDetails = ClassDetails.builder()
                .year(detailsDTO.getYear())
                .noOfStudents(detailsDTO.getNoOfStudents())
                .presentStudents(0)
                .standard(detailsDTO.getStandards())
//                .classTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()).get())
//                .section(sectionRepository.findById(Integer.valueOf(detailsDTO.getSection())).get())
                .build();
        ClassDetails saved = classDetailsRepository.save(classDetails);
        return saved;
    }


    @PutMapping("/updateClass/{id}")
    public ResponseEntity<ClassDetails> updateClass(@PathVariable("id") Integer userId, @RequestBody ClassDetailsDTO detailsDTO) {
        ClassDetails updatedTeacher = null;
        Optional<ClassDetails> optionalClass = classDetailsRepository.findById(userId);
        if (!optionalClass.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            ClassDetails classDetails = optionalClass.get();
            classDetails.setClassUNID(classDetails.getClassUNID());
            classDetails.setPresentStudents(classDetails.getPresentStudents());
            classDetails.setNoOfStudents(classDetails.getNoOfStudents());
            classDetails.setStandard(classDetails.getStandard());
            classDetails.setYear(classDetails.getYear());
            updatedTeacher = classDetailsRepository.save(classDetails);
        }
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/deleteClass/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable("id") Integer userId) {
        if (!classDetailsRepository.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        classDetailsRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAllClasses")
    public ResponseEntity<List> getAllClasses() {
        List<ClassSectionTeacher_IDTO> allClasses = classDetailsRepository.getAllClasses();
        return new ResponseEntity<>(allClasses, HttpStatus.OK);
    }

   

    

    @GetMapping("/getAllStudentsByClassId/{classId}") //DONE
    public List<StudentDetails> getAllStudentsByClassId(@PathVariable("classId") Integer classId) {
        Optional<ClassDetails> byId = classDetailsRepository.findById(classId);
        List<StudentDetails> students = new ArrayList<>();
        if (byId.isPresent()) {
            students = byId.get().getStudents();
        }
        return students;
    }

    @GetMapping("/getAllStudentsByStandard/{standard}") //DONE
    public List<StudentDetails> getAllStudentsByStandard(@PathVariable("standard") Integer classId) {
        List<ClassDetails> byId = classDetailsRepository.findByStandard(classId);
        List<StudentDetails> students = new ArrayList<>();
        if (!byId.isEmpty()) {
            byId.forEach(item->{
                students.addAll(item.getStudents());
            });
            return students;
        }
        return new LinkedList<>();
    }


    @GetMapping("/getAllStudents")
    public ResponseEntity<List> getAllStudents() {
        List<StudentDetails> all = studentRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/subjectList")
    public List<String> getSubjectList() {
        return Arrays.asList(Constants.Subject_List);
    }

    @GetMapping("/getAllClassesCount")
    public ResponseEntity<List> getAllClassesCount() {
        List<ClassSectionTeacher_IDTO> allClasses = classDetailsRepository.getAllClassesCount();
        return new ResponseEntity<>(allClasses, HttpStatus.OK);
    }


}
