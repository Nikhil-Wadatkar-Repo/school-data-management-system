package com.sdms.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sdms.dto.ClassIdStudId_DTO;
import com.sdms.dto.ClassSectionStudentDTO;
import com.sdms.dto.ResponseDTO;
import com.sdms.dto.StandardSectionWiseStudentI;
import com.sdms.dto.StudentDetailsView;
import com.sdms.entity.ClassDetails;
import com.sdms.entity.StudentDetails;
import com.sdms.exception.SDMSException;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.StudentRepository;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class StudentController {

    @Autowired
    private ClassDetailsRepository classDetailsRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/addExistedStudentToClass") // DONE
    public ResponseEntity<ResponseDTO> addExistedStudentToClass(@RequestBody ClassIdStudId_DTO classIdStudId_dto) {
        System.out.println(classIdStudId_dto);
        String message = "";
        StudentDetails studentDetails = null;
        Optional<StudentDetails> optionalStudent = studentRepository.findById(classIdStudId_dto.getStudId());
        if (optionalStudent.isPresent()) {
            studentDetails = optionalStudent.get();
            studentDetails.setClassAllottementStatus("Allotted");
            ClassDetails classDetails = classDetailsRepository.getClassBySectionsAndStandard(classIdStudId_dto.getStandard(), classIdStudId_dto.getSectionName()).get();
            studentDetails.setStd(classDetails.getStandard());
            classDetails.getStudents().add(studentDetails);
            classDetails.setPresentStudents(classDetails.getStudents().size() + 1);
            classDetailsRepository.save(classDetails);
            studentRepository.save(studentDetails);
            message = "Success";
        }
        return new ResponseEntity<>(ResponseDTO.builder().message(message).flag(true).build(), HttpStatus.OK);
    }

    @PostMapping("/addNewStudentToClass")
    public ResponseEntity<ResponseDTO> addNewStudentToClassSection(@RequestBody ClassSectionStudentDTO dto) {
        ClassDetails response = null;
        // get class by standard#
        Optional<ClassDetails> optional = classDetailsRepository.getClassBySectionsAndStandard(dto.getStandard(), dto.getSectionName());
        if (optional.isPresent()) {
            ClassDetails classDetails = optional.get();
            StudentDetails studentDetails = StudentDetails.builder().city(dto.getCity()).contact(dto.getContact()).dob(dto.getDob()).email(dto.getEmail()).name(dto.getName()).pincode(dto.getPincode())
            		.classAllottementStatus("Allotted").std(classDetails.getStandard()).studUNID("Not done").status("active").build();
            classDetails.getStudents().add(studentDetails);
            response = classDetailsRepository.save(classDetails);
        } else return new ResponseEntity<>(ResponseDTO.builder().message("Error").flag(false).build(), HttpStatus.OK);
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(), HttpStatus.OK);
    }

    @PostMapping("/createStudent")
    public StudentDetails createStudentDetails(@RequestBody StudentDetails details) {
        details.setStatus("Active");
        details.setClassAllottementStatus("Not Alloted");
        details.setCreatedBy("System");
        details.setCreatedDate(new Date());
        details.setStudUNID(details.getName() + details.getCity());
        return studentRepository.save(details);
    }

    @PostMapping("/updateStudent")
    public StudentDetails updateStudentDetails(@RequestBody StudentDetails details) {
        Optional<StudentDetails> byId = studentRepository.findById(details.getStudId());
        StudentDetails studentDetails = null;
        if (byId.isPresent()) {
            details.setStudId(byId.get().getStudId());
            studentDetails = studentRepository.save(details);
        }
        return studentDetails;
    }

    @GetMapping("/getStudentsByName/{name}")
    public List<StudentDetailsView> getStudentsByName(@PathVariable String name) {
        List<StudentDetailsView> studentsByName = null;
        try {
            studentsByName = studentRepository.getStudentsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (studentsByName.isEmpty()) throw new SDMSException("No Student found wth this name " + name);
        return studentsByName;
    }

    @GetMapping("/getStudentsByUNID/{id}")
    public StudentDetails getStudentsByUNID(@PathVariable Integer id) {
        Optional<StudentDetails> studentsByName = studentRepository.findById(id);
        if (!studentsByName.isPresent()) throw new SDMSException("No Student found wth this global ID " + id);
        return studentsByName.get();

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
        } else throw new SDMSException("No Student found wth this name ");
        return message;
    }

    //	@GetMapping("/getAllStudentsByClassId/{classId}/{sectionaName}") // DONE
//	public List<StudentDetails> getAllStudentsByClassId(@PathVariable("classId") Integer classId,
//			@PathVariable("sectionaName") String section) {
//		List<StudentDetails> byId = studentRepository.getStudentByStdAndSection(section, classId);
//
//		if (CollectionUtils.isEmpty(byId))
//			return new LinkedList<>();
//		return byId;
//
//	}
    @GetMapping("/getAllStudentsByStdSection/{classId}/{sectionaName}") // DONE
    public List<StandardSectionWiseStudentI> getAllStudentsByStdSection(@PathVariable("classId") Integer classId, @PathVariable("sectionaName") String section) {
        List<StandardSectionWiseStudentI> byId = studentRepository.getStudentByStdAndSection(section, classId);

        if (CollectionUtils.isEmpty(byId)) return new LinkedList<>();
        return byId;

    }
    @GetMapping("/getAllExamNonMappedStudentsByStdSection/{classId}/{sectionaName}") // DONE
    public List<StandardSectionWiseStudentI> getAllExamNonMappedStudentsByStdSection(@PathVariable("classId") Integer classId, @PathVariable("sectionaName") String section) {
        List<StandardSectionWiseStudentI> byId = studentRepository.getExamUnmappedStudentByStdAndSection(section, classId);

        if (CollectionUtils.isEmpty(byId)) return new LinkedList<>();
        return byId;

    }

    @GetMapping("/getStudentByStdSection/{std}/{section}")
    public List<StandardSectionWiseStudentI> getStudentByStdSection(@PathVariable Integer std, @PathVariable String section) {
        return studentRepository.getStudentByStdAndSection(section, std);
    }

    @GetMapping("/getAllStudentsByStandardSection")
    public List<StudentDetailsView> getAllStudentsByStandardSection() {
        return studentRepository.getAllStudentsByStandardSection();
    }
    // ----------------

}