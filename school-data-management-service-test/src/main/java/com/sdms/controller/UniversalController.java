package com.sdms.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.sdms.dto.*;
import com.sdms.entity.*;
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

//    @GetMapping("/saveDetails")
//    public List<ClassDetails> saveClass() {
//        ClassDetails save1 = new ClassDetails(), save2 = new ClassDetails();
//        try {
//            ExamDetails examDetails1 = ExamDetails.builder().examName("Unit test 1").subject1Name("sub1")
//                    .subject1ObtainedMarks(10).subject1totalMarks(100).build();
//            ExamDetails examDetails2 = ExamDetails.builder().examName("Unit test 2").subject1Name("sub1")
//                    .subject1ObtainedMarks(10).subject1totalMarks(100).subject2Name("sub2").subject2ObtainedMarks(10)
//                    .subject2totalMarks(100).build();
//
//            List<ExamDetails> exams = Arrays.asList(examDetails1, examDetails2);
//
//            StudentDetails studentDetails1 = StudentDetails.builder().exams(exams).name("Ankur").city("Morshi")
//                    .dob("04/06/1993").contact(123L).email("ankur@test.com").pincode(111250l).studUNID("ANK!123")
//                    .status("active").build();
//            StudentDetails studentDetails2 = StudentDetails.builder().exams(exams).name("Dhanu").city("Morshi")
//                    .dob("04/06/1993").contact(123L).email("dhanu@test.com").pincode(111250l).studUNID("DHA!123")
//                    .status("active").build();
//            SectionDetails sectionDetails1 = SectionDetails.builder().year(2025).sectionName("A").status("active")
//                    .students(Arrays.asList(studentDetails1, studentDetails2)).build();
//            SectionDetails sectionDetails2 = SectionDetails.builder().year(2025).sectionName("A").status("active")
//                    .students(Arrays.asList(studentDetails1, studentDetails2)).build();
//
//            ClassDetails classDetails = ClassDetails.builder().classUNID("A1").year(2012L).presentStudents(20)
//                    .noOfStudents(2).standard(12).sections(Arrays.asList(sectionDetails1, sectionDetails2)).build();
//            ClassDetails classDetails1 = ClassDetails.builder().classUNID("A2").year(2042L).presentStudents(50)
//                    .noOfStudents(2).standard(15).sections(Arrays.asList(sectionDetails1, sectionDetails2)).build();
//            save1 = classDetailsRepository.save(classDetails);
//            System.out.println("Class 1 saved");
//            save2 = classDetailsRepository.save(classDetails1);
//            System.out.println("Class 2 saved");
//
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//        return Arrays.asList(save1, save2);
//    }

    // section section----------------------------------
    @PostMapping("/createNewSection")
    public ResponseEntity<ResponseDTO> createNewSection(@RequestBody NewSectionDTO dto) {
        if (sectionRepository.existsBySectionName(dto.getSectionName()))
            throw new SDMSException("Section " + dto.getSectionName() + " already exists. Please enter unique one");

        SectionDetails sectionDetails = sectionRepository.save(SectionDetails.builder().status("Active").year(dto.getYear()).sectionUNID("Sect_" + utilityClass.getUniqueString(dto.getSectionName()))
                .sectionName(dto.getSectionName()).build());
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(),HttpStatus.OK);
    }
    @PostMapping("/updateSectionDetails")
    public ResponseEntity<ResponseDTO> updateSectionDetails(@RequestBody SectionDetails newsectionDetails) {
        SectionDetails sectionNameOptional = getSectionDetailsById(newsectionDetails.getSectionID());
        if(sectionNameOptional==null){
            throw new SDMSException("No Section found" );
        }
        SectionDetails sectionDetails = sectionNameOptional;
        newsectionDetails.setSectionID(sectionDetails.getSectionID());
        sectionRepository.save(newsectionDetails);
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(),HttpStatus.OK);
    }

    private SectionDetails getSectionDetailsById(Integer sectionId) {
        Optional<SectionDetails> optionalSectionDetails = sectionRepository.findById(sectionId);
        SectionDetails sectionDetails = optionalSectionDetails.isPresent() ? optionalSectionDetails.get() : null;
        return sectionDetails;
    }


    @GetMapping("/updateSectionById/{type}/{id}")
    public ResponseEntity<ResponseDTO> updateSection(@PathVariable("type") String operationType,@PathVariable("id") Integer sectionId){
        SectionDetails sectionDetailsById = getSectionDetailsById(sectionId);
        ResponseEntity<ResponseDTO> response=null;
        switch (operationType){
            case "D": {
                sectionRepository.deleteById(sectionId);
                response=new ResponseEntity<>(ResponseDTO.builder().message("Deleted successful").flag(true).build(),HttpStatus.OK);
            }break;
            case "A": {
                sectionDetailsById.setStatus("Active");
                sectionRepository.save(sectionDetailsById);
                response=new ResponseEntity<>(ResponseDTO.builder().message("Activated successful").flag(true).build(),HttpStatus.OK);
            }break;
            case "I": {
                sectionDetailsById.setStatus("In-active");
                sectionRepository.save(sectionDetailsById);
                response=new ResponseEntity<>(ResponseDTO.builder().message("In-Activated successful").flag(true).build(),HttpStatus.OK);
            }break;

        }
        return response;
    }

    @GetMapping("/getActiveSection")
    List<SectionDetails> getActiveSection(){
        return sectionRepository.findAll().stream().filter(filter->filter.getStatus().equalsIgnoreCase("active")).
                sorted(Comparator.comparing(SectionDetails::getSectionName)).
                collect(Collectors.toList());
    }
    @GetMapping("/getAllSection")
    List<SectionDetails> getAllSection(){
        return sectionRepository.findAll().stream().sorted(Comparator.comparing(SectionDetails::getSectionName)).
                collect(Collectors.toList());
    }

    @PostMapping("/createStudent")
    public StudentDetails createStudentDetails(@RequestBody StudentDetails details) {
        details.setStatus("Active");
        details.setStudUNID("Not decided");
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

    // get classes, section names, list of studentUNID with name
    @GetMapping("/getAllClassDetailsWithSectionYear")
    public List<ClassSectionYearDTO> getAllClassDetailsWithSectionYear() {
        //get class by standard
        //get


        return null;
    }

    @GetMapping("/getSections")
    public List<SectionDetailsView> getSections() {
        return sectionRepository.getAllSections();
    }

    @GetMapping("/getStudents")
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
    @PostMapping("/addExistedStudentToClass")
    public void addExistedStudentToClassSection(@RequestBody ClassSectionStudentDTO dto) {
        // get class by standard
        StudentDetails studentDetails = null;
        Optional<ClassDetails> optional = classDetailsRepository.findByStandard(dto.getStandard());
        if (optional.isPresent()) {
            ClassDetails classDetails = optional.get();
            Optional<SectionDetails> optionalSection = sectionRepository.findBySectionName(dto.getSectionName());
            if (optionalSection.isPresent()) {
                classDetails.getSections().add(optionalSection.get());
                Optional<StudentDetails> optionalStudent = studentRepository.getSpecificStudentByStudUNIDAndName(dto.getStudUNID(), dto.getStudentName());
                if (optionalStudent.isPresent()) {
                    studentDetails = optionalStudent.get();
                    studentDetails.setStd(classDetails.getStandard());
                    sections = classDetails.getSections();
                    SectionDetails sectionDetails = SectionDetails.builder().build();
                    sections.forEach(sect -> {
                        if (sect.getSectionName().equalsIgnoreCase(dto.getSectionName())) {
                            sectionDetails.setSectionID(sect.getSectionID());
                            sectionDetails.setClassTeacherName(sect.getClassTeacherName());
                            sectionDetails.setSectionName(sect.getSectionName());
                            sectionDetails.setStudents(sect.getStudents());
                            sectionDetails.setYear(sect.getYear());


//							classDetailsRepository.save(classDetails);
                        }
                    });

                    sectionDetails.getStudents().add(studentDetails);
                    sectionRepository.save(sectionDetails);
                }
            } else
                throw new SDMSException("Section is not present");
        } else
            throw new SDMSException("Class with standard is ot exist");
    }

    //	add new student to class or section
    @PostMapping("/addNewStudentToClass")
    public ResponseEntity<ClassDetails> addNewStudentToClassSection(@RequestBody ClassSectionStudentDTO dto) {
        ClassDetails response = null;
        // get class by standard
        Optional<ClassDetails> optional = classDetailsRepository.findByStandard(dto.getStd());
        if (optional.isPresent()) {
            ClassDetails classDetails = optional.get();
            Optional<SectionDetails> optionalSection = sectionRepository.findBySectionName(dto.getSection());
            if (optionalSection.isPresent()) {
                classDetails.getSections().add(optionalSection.get());
                StudentDetails studentDetails = StudentDetails.builder()
                        .city(dto.getCity())
                        .contact(dto.getContact())
                        .dob(dto.getDob())
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .pincode(dto.getPincode())
                        .studUNID("Not done")
                        .status("active")
                        .std(dto.getStd())
                        .build();
                optionalSection.get().getStudents().add(studentDetails);
                response = classDetailsRepository.save(classDetails);

            } else
                throw new SDMSException("Section is not present");
        } else
            throw new SDMSException("Class with standard is ot exist");
        return new ResponseEntity<ClassDetails>(response, HttpStatus.OK);
    }

    @GetMapping("/getStudents/{standard}/{section}")
    public Optional<List<StandardSectionWiseStudent>> filterStudentSectionClassWise(String section, Integer standard) {
        return classDetailsRepository.getStudentClassSectionStandardWise(standard, section);
    }

    @GetMapping("/getAllStudents")
    public List<StandardSectionWiseStudent> getAllStudentSectionClassWise() {
        List<ClassDetails> classList = classDetailsRepository.findAll();
        List<StandardSectionWiseStudent> response = new LinkedList<>();

        for (ClassDetails classDetails : classList) {
            Integer std = classDetails.getStandard();
            System.out.println("STD::::" + std);
            String studentName = "";
            String sectionName = "";
            Long studentId = 0L;

            List<SectionDetails> sections = classDetails.getSections();
            for (SectionDetails section : sections) {
                List<StudentDetails> studentDetails = section.getStudents();
                for (StudentDetails details : studentDetails) {
                    if (details.getStatus().equalsIgnoreCase("active")
                            || details.getStatus().equalsIgnoreCase("inactive")) {
                        StandardSectionWiseStudent standardSectionWiseStudent = StandardSectionWiseStudent.builder()
                                .studentName(details.getName())
                                .sectionName(section.getSectionName())
                                .standard(std)
                                .studId(details.getStudId())
                                .build();
                        response.add(standardSectionWiseStudent);
                    }

                }

            }
        }
        return response;
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

//    @GetMapping("/setSubjectsExistedExam")
//    public void setSubjectsExistedExam() {
//        ExamDetails examDetails1 = ExamDetails.builder()
//                .examName("Unit test 3")
//                .status(true)
//                .year(2010)
//                .build();
//        ExamDetails examDetails2 = ExamDetails.builder()
//                .examName("Unit test 4")
//                .status(true)
//                .year(2010)
//                .build();
//        SubjectDetails subjectDetails1= SubjectDetails.builder()
//                .subject1Name("English")
//                .subject1totalMarks(100)
//                .subject1ObtainedMarks(0)
//                .build();
//        SubjectDetails subjectDetails2= SubjectDetails.builder()
//                .subject1Name("Marathi")
//                .subject1totalMarks(100)
//                .subject1ObtainedMarks(0)
//                .build();
//        SubjectDetails subjectDetails3= SubjectDetails.builder()
//                .subject1Name("Telugu")
//                .subject1totalMarks(100)
//                .subject1ObtainedMarks(0)
//                .build();
//        SubjectDetails subjectDetails4= SubjectDetails.builder()
//                .subject1Name("Hindi")
//                .subject1totalMarks(100)
//                .subject1ObtainedMarks(0)
//                .build();
//        subjectDetailsRepo.save(subjectDetails1);
//        subjectDetailsRepo.save(subjectDetails2);
//        subjectDetailsRepo.save(subjectDetails3);
//        subjectDetailsRepo.save(subjectDetails4);
//
//        examDetails1.setSubjectDetails(subjectDetailsRepo.findById(1).get());
//        examDetails1.setSubjectDetails(subjectDetailsRepo.findById(2).get());
//        examDetails2.setSubjectDetails(subjectDetailsRepo.findById(3).get());
//        examDetails2.setSubjectDetails(subjectDetailsRepo.findById(4).get());
//        ExamDetails save1 = examRepo.save(examDetails1);
//        System.out.println("save1 " + save1);
//        ExamDetails save2 = examRepo.save(examDetails2);
//        System.out.println("save2 " + save2);
//    }

    /**
     * Used to get the exam details of student based on STUNID
     *
     * @param unid
     * @param std
     * @return
     */
    @GetMapping("/getExamInfoByStdUnid/{unid}/{std}")// need to modify based on situation
    public List<ExamIDExamName> getExamListByStudentNameAndStandard(@PathVariable("unid") String unid, @PathVariable("std") Integer std) {
        SectionDetails sectionDetails = null;
        StudentDetails studentDetails = null;
        ClassDetails classDetails = null;
        List<ExamIDExamName> examIDExamNames = new LinkedList<>();
        Optional<StudentDetails> optionalStudentDetails = studentRepository.getSpecificStudentByStudUNIDAndName(unid, "");
        ExamDetails examDetails = null;
        if (optionalStudentDetails.isPresent()) {
            studentDetails = optionalStudentDetails.get();
            Optional<SectionDetails> sectionDetailsOptional = sectionRepository.getSectionFromStudent(studentDetails.getStudId());
            if (sectionDetailsOptional.isPresent()) {
                sectionDetails = sectionDetailsOptional.get();
                Optional<ClassDetails> classDetailsOptional = classDetailsRepository.getClassDetailsFromSectionId(sectionDetails.getSectionID());
                if (classDetailsOptional.isPresent()) {
                    classDetails = classDetailsOptional.get();
                    List<ExamDetails> exams = studentDetails.getExams();
                    exams.forEach(utem -> {
                        examIDExamNames.add(ExamIDExamName.builder()
                                .examId(utem.getSubId())
                                .examName(utem.getExamName())
                                .build());
                    });
                }
            }
        }
        return examIDExamNames;
    }

    @PostMapping("/createTeacher")
    public TeacherDetails createTeacher(@RequestBody TeacherDetails user) {
        return teacherRepository.save(user);
    }

    @GetMapping("/getAllSections")
    public List<SectionDetailsView> getAllSections() {
        System.out.println("ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
        return sectionRepository.getAllSections();
    }

    @GetMapping("/getDistinctSections")
    public List<SectionDetailsView> getDistinctSections() {
        return sectionRepository.getAllSections();
    }

    @GetMapping("/getAllTeachers")
    public List<TeacherDetails> getAllTeachers() {
        return teacherRepository.findAll();
    }


    @PostMapping("/createClass")
    public ClassDetails createClass(@RequestBody ClassDetailsDTO detailsDTO) {

        ClassDetails classDetails = null;
        classDetails=new ClassDetails();
        classDetails.setYear(detailsDTO.getYear());
        classDetails.setNoOfStudents(detailsDTO.getNoOfStudents());
        classDetails.setSections(Arrays.asList(sectionRepository.findById(detailsDTO.getSection()).get()));
        classDetails.setStandard(detailsDTO.getStandards());
//        isValid.error

        ClassDetails saved = classDetailsRepository.save(classDetails);
        return saved;
    }

    private List updateTeacherForClass(ClassDetails classDetails, Integer id) {
        List<SectionDetails> sectionDetails = classDetails.getSections();
        if (!sectionDetails.stream().anyMatch(item -> item.getSectionID() == id)) {
            SectionDetails newSection = sectionRepository.findById(id).get();
            sectionDetails.add(newSection);
        }

        return sectionDetails;
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
//            classDetails.setClassTeacherName(teacherRepository.findById(detailsDTO.getClassTeacherName()));
            updatedTeacher = classDetailsRepository.save(classDetails);
        }
//        user.setTeacherId(userId);

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

    @GetMapping("/getDistinctStandards")
    public List<ClassDetailsView> getDistinctStandards() {
        return classDetailsRepository.getAllStandards();
    }

    @GetMapping("/getSectionByStandard/{std}")
    public List<SectionNameAndIdView> getSectionByStandard(@PathVariable("std") Integer std) {
        return classDetailsRepository.getSectionByStandard(std);
    }

    @GetMapping("/getStudentsBySectionUNID")
    public SectionNameAndIdView getExamListBasedOnStdUNID() {
        //need to implement getting list of exam info of each student based on standard
        sectionRepository.getAllSections();
        return null;
    }

}
