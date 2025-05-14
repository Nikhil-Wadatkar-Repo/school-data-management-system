package com.sdms.controller;

import com.sdms.dto.NewSectionDTO;
import com.sdms.dto.ResponseDTO;
import com.sdms.entity.ClassDetails;
import com.sdms.entity.SectionDetails;
import com.sdms.entity.StandardWiseSectino_IDTO;
import com.sdms.exception.SDMSException;
import com.sdms.helper.UtilityClass;
import com.sdms.repo.ClassDetailsRepository;
import com.sdms.repo.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ClassSectionDetailsController {
    @Autowired
    private UtilityClass utilityClass;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private ClassDetailsRepository classDetailsRepository;

    @PostMapping("/createNewSection")
    public ResponseEntity<ResponseDTO> createNewSection(@RequestBody NewSectionDTO dto) { //DONE
        ClassDetails classDetails = null;
        SectionDetails sectionDetails = null;

        if (sectionRepository.existsBySectionName(dto.getSectionName()))
            throw new SDMSException("Section " + dto.getSectionName() + " already exists. Please enter unique one");
        try {
            classDetails = ClassDetails.builder().sectionName(dto.getSectionName()).status("Active").totalStudents(0).noOfStudents(dto.getNoOfStudents()).standard(dto.getStd()).year(Long.valueOf(dto.getYear())).presentStudents(0).createdBy("System").createdDate(new Date()).classUNID(dto.getSectionName() + "_" + UtilityClass.generateRandomID()).build();
            classDetailsRepository.save(classDetails);
        } catch (RuntimeException e) {

        }
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(), HttpStatus.OK);
    }

    @PostMapping("/updateSectionDetails") //DONE
    public ResponseEntity<ResponseDTO> updateSectionDetails(@RequestBody ClassDetails newsectionDetails) {
        ClassDetails sectionNameOptional = getById(newsectionDetails);
        if (sectionNameOptional == null) {
            throw new SDMSException("No Section found");
        }
        ClassDetails sectionDetails = sectionNameOptional;
        sectionDetails.setClassId(sectionDetails.getClassId());
        newsectionDetails.setUpdatedBy("System");
        newsectionDetails.setUpdatedDate(new Date());
        classDetailsRepository.save(newsectionDetails);
        return new ResponseEntity<>(ResponseDTO.builder().message("Saved successful").flag(true).build(), HttpStatus.OK);
    }

    private ClassDetails getById(ClassDetails classDetails) {
        Optional<ClassDetails> byId = classDetailsRepository.findById(classDetails.getClassId());
        if (byId.isPresent()) return byId.get();
        else return null;

    }

    private SectionDetails getSectionDetailsById(Integer sectionId) {  //DONE
        Optional<SectionDetails> optionalSectionDetails = sectionRepository.findById(sectionId);
        SectionDetails sectionDetails = optionalSectionDetails.isPresent() ? optionalSectionDetails.get() : null;
        return sectionDetails;
    }

    @GetMapping("/deleteSectionById/{classId}")
    public List<ClassDetails> deleteClassSectionById(@PathVariable("classId") Integer classId) {
        classDetailsRepository.deleteById(classId);
        return classDetailsRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/getDistinctClasses")
    public ResponseEntity<List> getDistinctClasses() {
        List<Integer> allClasses = classDetailsRepository.getAllClasses().stream().map(det -> det.getStandard()).distinct().collect(Collectors.toList());
        return new ResponseEntity<>(allClasses, HttpStatus.OK);
    }

    @GetMapping("/getSectionByStandard/{std}") //DONE
    public ResponseEntity<List> getSectionByStandard(@PathVariable("std") Integer std) {
        return new ResponseEntity<>(classDetailsRepository.getSectionsByStandard(std), HttpStatus.OK);

    }
//---------------------------------------------------------------------------------------


    @GetMapping("/updateSectionById/{type}/{id}")
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
    List<ClassDetails> getActiveSection() {

        return classDetailsRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/sections")
        //DONE
    List<SectionDetails> getAllSection() {
        return sectionRepository.findAll();
    }
}