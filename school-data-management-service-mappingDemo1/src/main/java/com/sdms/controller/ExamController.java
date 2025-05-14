package com.sdms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdms.dto.ExamDetail;
import com.sdms.dto.ResponseDTO;
import com.sdms.entity.ExamDetails;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StudentDetails;
import com.sdms.exception.SDMSException;
import com.sdms.helper.Constants;
import com.sdms.helper.UtilityClass;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.ExamRepo;
import com.sdms.repo.SectionRepository;
import com.sdms.repo.StudentRepository;
import com.sdms.repo.SubjectDetailsRepo;
import com.sdms.repo.TeacherRepository;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ExamController {
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
	    
	    @GetMapping("/getExamDetailsByStudentId/{studId}")
	    public List<ExamDetails> getExamDetailsByStudentId(@PathVariable("studId") Integer studId) {
	        List<ExamDetails> examList = new ArrayList<>();
	        Optional<StudentDetails> optionalStudentDetails = studentRepository.findById(studId);
	        if (optionalStudentDetails.isPresent()) {
	            if (optionalStudentDetails.get().getExamDetails().size() == 0)
	            	return new ArrayList<>();
	            examList = optionalStudentDetails.get().getExamDetails();
	        } else {
	            throw new SDMSException("No Estudent found.");
	        }
	        return examList;
	    }
	    
	    ///---------------------------

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
                    .isMarksAssigned(true)
                    .finalyCalculated(false)
                    .build();
            studentDetails.getExamDetails().add(examDetails);
            studentDetails.setExamAllottmentStatus("Allotted");
            studentRepository.save(studentDetails);
            responseDTO.setFlag(true);
            responseDTO.setMessage("Success");
        } else {
            responseDTO.setFlag(false);
            responseDTO.setMessage("Error");
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
    public ResponseEntity<ResponseDTO> updateExamDetails(@RequestBody ExamDetails examDetails) {
        Optional<ExamDetails> byId = examRepo.findById(examDetails.getSubId());
        if (!byId.isPresent())
            throw new SDMSException("No Exam details found");
        ExamDetails details = byId.get();
            if(null!=details.getCalcStatus() && details.getCalcStatus().equalsIgnoreCase("Calculated"))
                return new ResponseEntity<>(ResponseDTO.builder().message("Marks are already assigned").flag(false).build(), HttpStatus.OK);
        details.setSubject1ObtainedMarks(examDetails.getSubject1ObtainedMarks());
        details.setSubject2ObtainedMarks(examDetails.getSubject2ObtainedMarks());
        details.setSubject3ObtainedMarks(examDetails.getSubject3ObtainedMarks());
        details.setSubject4ObtainedMarks(examDetails.getSubject4ObtainedMarks());
        details.setSubject5ObtainedMarks(examDetails.getSubject5ObtainedMarks());
        details.setIsMarksAssigned(true);

        int totalMarks = details.getSubject1totalMarks() + details.getSubject2totalMarks() + details.getSubject3totalMarks() + details.getSubject4totalMarks() + details.getSubject5totalMarks();
        int obtainedTotalMarks = details.getSubject1ObtainedMarks() + details.getSubject2ObtainedMarks() + details.getSubject3ObtainedMarks() + details.getSubject4ObtainedMarks() + details.getSubject5ObtainedMarks();
        details.setTotalMarks(totalMarks);
        details.setTotalObtainedMarks(obtainedTotalMarks);
        details.setPercentage((float) ((obtainedTotalMarks * 100) / totalMarks));
        details.setResultStatus(UtilityClass.getStudentPassFailStatus(details.getPercentage()));
        details.setCalcStatus("Calculated");
        details.setFinalyCalculated(true);
        details.setIsMarksAssigned(true);
        try{

            examRepo.save(details);
        }
        catch (Exception e){
             return new ResponseEntity<>(ResponseDTO.builder().message("Error").flag(false).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseDTO.builder().message("Success").flag(true).build(), HttpStatus.OK);
    }
    
    
//    // get exam details by student Id
//    public List<ExamDetails> getExamDetailsByStudentId(@PathVariable("id") Integer id){
//    	return examRepo.getExamDetailsByStudentId(id);
//    }

}
