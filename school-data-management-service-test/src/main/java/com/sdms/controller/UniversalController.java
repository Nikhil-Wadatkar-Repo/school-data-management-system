package com.sdms.controller;

import com.sdms.dto.*;
import com.sdms.entity.ClassDetails;
import com.sdms.entity.ExamDetails;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StudentDetails;
import com.sdms.exception.SDMSException;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.ExamRepo;
import com.sdms.repo.SectionRepository;
import com.sdms.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/uni")
@RestController
@CrossOrigin
public class UniversalController {
	@Autowired
	private ClassDetailsRepositor classDetailsRepository;
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ExamRepo examRepo;
	private List<SectionDetails> sections;

	@GetMapping("/saveDetails")
	public List<ClassDetails> saveClass() {
		ClassDetails save1 = new ClassDetails(), save2 = new ClassDetails();
		try {
			ExamDetails examDetails1 = ExamDetails.builder().examName("Unit test 1").subject1Name("sub1")
					.subject1ObtainedMarks(10).subject1totalMarks(100).build();
			ExamDetails examDetails2 = ExamDetails.builder().examName("Unit test 2").subject1Name("sub1")
					.subject1ObtainedMarks(10).subject1totalMarks(100).subject2Name("sub2").subject2ObtainedMarks(10)
					.subject2totalMarks(100).build();

			List<ExamDetails> exams = Arrays.asList(examDetails1, examDetails2);

			StudentDetails studentDetails1 = StudentDetails.builder().exams(exams).name("Ankur").city("Morshi")
					.dob("04/06/1993").contact(123L).email("ankur@test.com").pincode(111250l).studUNID("ANK!123")
					.status("active").build();
			StudentDetails studentDetails2 = StudentDetails.builder().exams(exams).name("Dhanu").city("Morshi")
					.dob("04/06/1993").contact(123L).email("dhanu@test.com").pincode(111250l).studUNID("DHA!123")
					.status("active").build();
			SectionDetails sectionDetails1 = SectionDetails.builder().year(2025).sectionName("A").status("active")
					.students(Arrays.asList(studentDetails1, studentDetails2)).build();
			SectionDetails sectionDetails2 = SectionDetails.builder().year(2025).sectionName("A").status("active")
					.students(Arrays.asList(studentDetails1, studentDetails2)).build();

			ClassDetails classDetails = ClassDetails.builder().classUNID("A1").year(2012L).presentStudents(20)
					.noOfStudents(2).standard(12).sections(Arrays.asList(sectionDetails1, sectionDetails2)).build();
			ClassDetails classDetails1 = ClassDetails.builder().classUNID("A2").year(2042L).presentStudents(50)
					.noOfStudents(2).standard(15).sections(Arrays.asList(sectionDetails1, sectionDetails2)).build();
			save1 = classDetailsRepository.save(classDetails);
			System.out.println("Class 1 saved");
			save2 = classDetailsRepository.save(classDetails1);
			System.out.println("Class 2 saved");

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		return Arrays.asList(save1, save2);
	}

	public SectionDetails createNewSection(NewSectionDTO dto) {
		return sectionRepository.save(SectionDetails.builder().status("active").year(dto.getYear())
				.sectionName(dto.getSectionName()).build());
	}

	public SectionDetails updateSectionDetails(SectionDetails newsectionDetails) {
		return getSectionDetailsById(newsectionDetails.getSectionID());
	}

	private SectionDetails getSectionDetailsById(Integer sectionId) {
		Optional<SectionDetails> optionalSectionDetails = sectionRepository.findById((long) sectionId);
		SectionDetails sectionDetails = optionalSectionDetails.isPresent() ? optionalSectionDetails.get() : null;
		return sectionDetails;
	}

	public StudentDetails createStudentDetails(StudentDetails details) {
		return studentRepository.save(details);
	}

	public StudentDetails updateStudentDetails(StudentDetails details) {
		Optional<StudentDetails> byId = studentRepository.findById(details.getStudId());
		StudentDetails studentDetails = byId.isPresent() ? studentRepository.save(details) : null;
		return studentDetails;
	}

	public StudentDetails deactivateStudent(String unid) {
		StudentDetails byStudUNID = studentRepository.findByStudUNID(unid).get();
		byStudUNID.setStatus("deactive");
		return byStudUNID = studentRepository.save(byStudUNID);
	}

	// get classes, section names, list of studentUNID with name
	@GetMapping("/getAllClass")
	public List<ClassDetailsView> getCLasses() {
		return classDetailsRepository.getAllStandards();
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
	public StudentDetails getStudentsByUNID(@PathVariable String UNID) {
		Optional<StudentDetails> studentsByName = studentRepository.findByStudUNID(UNID);
		if (!studentsByName.isPresent())
			throw new SDMSException("No Student found wth this global ID " + UNID);
		return studentsByName.get();

	}

// add existed student to class or section
	@PostMapping("/addExistedStudentToClass")
	public void addExistedStudentToClassSection(@RequestBody ClassSectionStudentDTO dto) {
		// get class by standard
		Optional<ClassDetails> optional = classDetailsRepository.findByStandard(dto.getStandard());
		if (optional.isPresent()) {
			ClassDetails classDetails = optional.get();
			Optional<SectionDetails> optionalSection = sectionRepository.findBySectionName(dto.getSectionName());
			if (optionalSection.isPresent()) {
				classDetails.getSections().add(optionalSection.get());
				Optional<StudentDetails> optionalStudent = studentRepository.findByStudUNID(dto.getStudUNID());
				if (optionalStudent.isPresent()) {
					sections = classDetails.getSections();
					SectionDetails sectionDetails=SectionDetails.builder().build();
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
					
					sectionDetails.getStudents().add(optionalStudent.get());
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
		ClassDetails response=null;
		// get class by standard
		Optional<ClassDetails> optional = classDetailsRepository.findByStandard(dto.getStd());
		if (optional.isPresent()) {
			ClassDetails classDetails = optional.get();
			Optional<SectionDetails> optionalSection = sectionRepository.findBySectionName(dto.getSection());
			if (optionalSection.isPresent()) {
				classDetails.getSections().add(optionalSection.get());
				StudentDetails studentDetails=StudentDetails.builder()
						.city(dto.getCity())
						.contact(dto.getContact())
						.dob(dto.getDob())
						.email(dto.getEmail())
						.name(dto.getName())
						.pincode(dto.getPincode())
						.studUNID("Not done")
						.status("active")
						.build();
				optionalSection.get().getStudents().add(studentDetails);
				response=classDetailsRepository.save(classDetails);
			
			} else
				throw new SDMSException("Section is not present");
		} else
			throw new SDMSException("Class with standard is ot exist");
		return new ResponseEntity<ClassDetails>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getStudents/{standard}/{section}")
	public List<StudentDetailsView> filterStudentSectionClassWise(String section,Integer standard) {
		return classDetailsRepository.getStudentClassSectionStandardWise(standard, section);
	}
}
