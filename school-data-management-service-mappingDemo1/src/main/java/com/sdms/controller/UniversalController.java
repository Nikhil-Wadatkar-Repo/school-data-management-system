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

@RequestMapping("/uni")
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

    // ================================ section section ================================
    @PostMapping("/createNewSection") //DONE
    public ResponseEntity<ResponseDTO> createNewSection(@RequestBody NewSectionDTO dto) {
        if (sectionRepository.existsBySectionName(dto.getSectionName()))
            throw new SDMSException("Section " + dto.getSectionName() + " already exists. Please enter unique one");

        SectionDetails sectionDetails = sectionRepository.save(SectionDetails.builder().status("Active").year(dto.getYear()).sectionUNID("Sect_" + utilityClass.getUniqueString(dto.getSectionName()))
                .sectionName(dto.getSectionName()).build());
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(), HttpStatus.OK);
    }

    @PostMapping("/updateSectionDetails") //DONE
    public ResponseEntity<ResponseDTO> updateSectionDetails(@RequestBody SectionDetails newsectionDetails) {
        SectionDetails sectionNameOptional = getSectionDetailsById(newsectionDetails.getSectionID());
        if (sectionNameOptional == null) {
            throw new SDMSException("No Section found");
        }
        SectionDetails sectionDetails = sectionNameOptional;
        newsectionDetails.setSectionID(sectionDetails.getSectionID());
        sectionRepository.save(newsectionDetails);
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(), HttpStatus.OK);
    }

    private SectionDetails getSectionDetailsById(Integer sectionId) {  //DONE
        Optional<SectionDetails> optionalSectionDetails = sectionRepository.findById(sectionId);
        SectionDetails sectionDetails = optionalSectionDetails.isPresent() ? optionalSectionDetails.get() : null;
        return sectionDetails;
    }


    @GetMapping("/updateSectionById/{type}/{id}") //DONE
    public ResponseEntity<ResponseDTO> updateSection(@PathVariable("type") String operationType, @PathVariable("id") Integer sectionId) {
        SectionDetails sectionDetailsById = getSectionDetailsById(sectionId);
        ResponseEntity<ResponseDTO> response = null;
        switch (operationType) {
            case "D": {
                sectionRepository.deleteById(sectionId);
                response = new ResponseEntity<>(ResponseDTO.builder().message("Deleted successful").flag(true).build(), HttpStatus.OK);
            }
            break;
            case "A": {
                sectionDetailsById.setStatus("Active");
                sectionRepository.save(sectionDetailsById);
                response = new ResponseEntity<>(ResponseDTO.builder().message("Activated successful").flag(true).build(), HttpStatus.OK);
            }
            break;
            case "I": {
                sectionDetailsById.setStatus("In-active");
                sectionRepository.save(sectionDetailsById);
                response = new ResponseEntity<>(ResponseDTO.builder().message("In-Activated successful").flag(true).build(), HttpStatus.OK);
            }
            break;

        }
        return response;
    }

    @GetMapping("/getAllSection")
        //DONE
    List<SectionDetails> getActiveSection() {
        return sectionRepository.findAll().stream().filter(filter -> filter.getStatus().equalsIgnoreCase("active")).
                sorted(Comparator.comparing(SectionDetails::getSectionName)).
                collect(Collectors.toList());
    }

    @GetMapping("/sections")
        //DONE
    List<SectionDetails> getAllSection() {
        return sectionRepository.findAll();
    }

    // ========================= Student section ==========================================
    @PostMapping("/createStudent")
    public StudentDetails createStudentDetails(@RequestBody StudentDetails details) {
        details.setStatus("Active");
        details.setStudUNID(details.getName() + details.getCity());
        return studentRepository.save(details);
    }

    @PostMapping("/updateStudent")
    public StudentDetails updateStudentDetails(@RequestBody StudentDetails details) {
        Optional<StudentDetails> byId = studentRepository.findById(details.getStudId());
        StudentDetails studentDetails = byId.isPresent() ? studentRepository.save(details) : null;
        return studentDetails;
    }

//    public StudentDetails deactivateStudent(String unid) {
//        StudentDetails byStudUNID = studentRepository.findByStudUNID(unid).get();
//        byStudUNID.setStatus("deactive");
//        return byStudUNID = studentRepository.save(byStudUNID);
//    }

    @GetMapping("/getStudents")//DONE
    public List<StudentDetailsView> getStudents() {
        return studentRepository.getAllStudent();
    }


    @GetMapping("/getStudentsByName/{name}")
    public List<StudentDetailsView> getStudentsByName(@PathVariable String name) {
        List<StudentDetailsView> studentsByName = studentRepository.getStudentsByName(name);
        if (studentsByName.isEmpty())
            throw new SDMSException("No Student found wth this name " + name);
        return studentsByName;

    }

    @GetMapping("/getStudentsByUNID/{UNID}")
    public List<StudentDetails> getStudentsByUNID(@PathVariable String UNID) {
        Optional<List<StudentDetails>> studentsByName = studentRepository.findByStudUNID(UNID);
        if (!studentsByName.isPresent())
            throw new SDMSException("No Student found wth this global ID " + UNID);
        return studentsByName.get();

    }

    // add existed student to class or section
    //	add new student to class or section
    @GetMapping("/getStudents/{standard}/{section}")
    public Optional<List<StandardSectionWiseStudent>> filterStudentSectionClassWise(String section, Integer standard) {
        return classDetailsRepository.getStudentClassSectionStandardWise(standard, section);
    }

    @GetMapping("/deleteOrInActiveStudentById/{id}/{type}")
    public String deleteStudentById(@PathVariable Integer id, @PathVariable String type) {
        String message = "";
        StudentDetails studentDetails = null;
        Optional<StudentDetails> studentsByName = studentRepository.findById(id);
        if (studentsByName.isPresent()) {
            studentDetails = studentsByName.get();
            switch (type) {
                case "D": {

                    studentDetails.setStatus("Inactive");
                    studentRepository.save(studentDetails);
                    message = "Inactivated";
                }
                break;
                case "E": {
                    studentDetails.setStatus("Active");
                    studentRepository.save(studentDetails);
                    message = "Activated";
                }
                break;
                case "R": {
                    studentRepository.deleteById(id);
                    message = "Deleted";
                }
                break;
            }
        } else
            throw new SDMSException("No Student found wth this name ");
        return message;
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
                .classTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()).get())
                .section(sectionRepository.findBySectionName(detailsDTO.getSection()).get())
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

    @GetMapping("/getDistinctClasses")
    public ResponseEntity<List> getDistinctClasses() {
        List<Integer> allClasses = classDetailsRepository.getAllClasses().stream().map(det -> det.getStandard()).distinct().collect(Collectors.toList());
        return new ResponseEntity<>(allClasses, HttpStatus.OK);
    }

    @GetMapping("/getSectionByStandard/{std}") //DONE
    public ResponseEntity<List> getSectionByStandard(@PathVariable("std") Integer std) {
        List<StandardWiseSectino_IDTO> sectionByStandard = sectionRepository.getSectionByStandard(std);

        return new ResponseEntity<>(sectionByStandard, HttpStatus.OK);
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

    @PostMapping("/addExistedStudentToClass")//DONE
    public ResponseEntity<ResponseDTO> addExistedStudentToClass(@RequestBody ClassIdStudId_DTO classIdStudId_dto) {
        System.out.println(classIdStudId_dto);
        String message = "";
        StudentDetails studentDetails = null;
        ;
        Optional<StudentDetails> optionalStudent = studentRepository.findById(classIdStudId_dto.getStudId());
        if (optionalStudent.isPresent()) {
            studentDetails = optionalStudent.get();
            ClassDetails classDetails = classDetailsRepository.findById(classIdStudId_dto.getClassId()).get();
            classDetails.getStudents().add(studentDetails);
            classDetails.setPresentStudents(classDetails.getStudents().size());
            classDetailsRepository.save(classDetails);
            message = "Success";
        }
        return new ResponseEntity<>(ResponseDTO.builder().message(message).flag(true).build(), HttpStatus.OK);
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

    @GetMapping("/examList")
    public List<String> getexamList() {
        return Arrays.asList(Constants.Exam_List);
    }

    @PostMapping("/assignExam")
    public ResponseEntity assignExam(@RequestBody ExamDetail examDetail) {
        ResponseDTO responseDTO = ResponseDTO.builder().build();
        Optional<StudentDetails> studentDetailsOptional = studentRepository.findById(examDetail.getStudId());
        if (studentDetailsOptional.isPresent()) {
            StudentDetails studentDetails = studentDetailsOptional.get();
            List<String> examsList = studentDetails.getExamDetails().stream().map(item -> item.getExamName()).collect(Collectors.toList());
            if (examsList.contains(examDetail.getExamName())) {
                throw new SDMSException("This student is already assigned with same exam");
            }
            ExamDetails examDetails = ExamDetails.builder()
                    .examName(examDetail.getExamName())
                    .subject1Name(examDetail.getSubject1Name())
                    .subject2Name(examDetail.getSubject2Name())
                    .subject3Name(examDetail.getSubject3Name())
                    .subject4Name(examDetail.getSubject4Name())
                    .subject5Name(examDetail.getSubject5Name())
                    .subject1totalMarks(examDetail.getSubject1TotalMarks())
                    .subject2totalMarks(examDetail.getSubject2TotalMarks())
                    .subject3totalMarks(examDetail.getSubject3TotalMarks())
                    .subject4totalMarks(examDetail.getSubject4TotalMarks())
                    .subject5totalMarks(examDetail.getSubject5TotalMarks())
                    .status(true)
                    .build();
            studentDetails.getExamDetails().add(examDetails);
            studentRepository.save(studentDetails);
            responseDTO.setFlag(true);
            responseDTO.setMessage("Success");
        } else {
            responseDTO.setFlag(false);
            responseDTO.setMessage("Error");
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getExamDetailsByStudentId/{studId}")
    public List<ExamDetails> getExamDetailsByStudentId(@PathVariable("studId") Integer studId) {
        List<ExamDetails> examList = new ArrayList<>();
        Optional<StudentDetails> optionalStudentDetails = studentRepository.findById(studId);
        if (optionalStudentDetails.isPresent()) {
            if (optionalStudentDetails.get().getExamDetails().size() == 0)
                throw new SDMSException("No Exams Has been assigned to this student");
            examList = optionalStudentDetails.get().getExamDetails();
        } else {
            throw new SDMSException("No Estudent found.");
        }
        return examList;
    }

    @GetMapping("/getExamDetailsByExamId/{examId}")
    public ExamDetails getExamDetailsByExamId(@PathVariable("examId") Integer studId) {
        ExamDetails examDetails = null;
        Optional<ExamDetails> optionalExamDetails = examRepo.findById(studId);
        if (optionalExamDetails.isPresent())
            examDetails = optionalExamDetails.get();
        else throw new SDMSException("No Exam details found");
        return examDetails;
    }

    @GetMapping("/getExamDetailsList")
    public List<ExamDetails> getExamDetailsList() {
        return examRepo.findAll();
    }

    @GetMapping("/getAllExamDetails")
    public List<ExamDetails> getAllExamDetails() {
        return examRepo.findAll();
    }

    @PostMapping("/updateExamDetails")
    public ExamDetails updateExamDetails(@RequestBody ExamDetails examDetails) {
        Optional<ExamDetails> byId = examRepo.findById(examDetails.getSubId());
        if (!byId.isPresent())
            throw new SDMSException("No Exam details found");
        ExamDetails details = byId.get();

        details.setSubject1ObtainedMarks(examDetails.getSubject1ObtainedMarks());
        details.setSubject2ObtainedMarks(examDetails.getSubject2ObtainedMarks());
        details.setSubject3ObtainedMarks(examDetails.getSubject3ObtainedMarks());
        details.setSubject4ObtainedMarks(examDetails.getSubject4ObtainedMarks());
        details.setSubject5ObtainedMarks(examDetails.getSubject5ObtainedMarks());

        int totalMarks = details.getSubject1totalMarks() + details.getSubject2totalMarks() + details.getSubject3totalMarks() + details.getSubject4totalMarks() + details.getSubject5totalMarks();
        int obtainedTotalMarks = details.getSubject1ObtainedMarks() + details.getSubject2ObtainedMarks() + details.getSubject3ObtainedMarks() + details.getSubject4ObtainedMarks() + details.getSubject5ObtainedMarks();
        details.setTotalMarks(totalMarks);
        details.setTotalObtainedMarks(obtainedTotalMarks);
        details.setPercentage((float) ((obtainedTotalMarks * 100) / totalMarks));
        details.setResultStatus(UtilityClass.getStudentPassFailStatus(details.getPercentage()));
        details.setCalcStatus("Calculated");
        return examRepo.save(details);
    }

}
