package com.sdms.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdms.entity.SectionDetails;
import com.sdms.repo.SectionRepository;

@Service
public class SectionService {
	

    @Autowired
    private SectionRepository sectionRepository;

    public List<SectionDetails> getAllSectionDetails() {
        return sectionRepository.findAll();
    }

    public Optional<SectionDetails> getSectionDetailsById(Integer userId) {
        return sectionRepository.findById(userId);
    }

    public SectionDetails saveSectionDetails(SectionDetails sectionDetails) {
//    	user.setStatus("active");
        return sectionRepository.save(sectionDetails);
    }

    public void deleteSectionDetails(Integer userId) {
        sectionRepository.deleteById(userId);
    }
    

}