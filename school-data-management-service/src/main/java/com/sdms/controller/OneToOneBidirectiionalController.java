package com.sdms.controller;

import com.sdms.entity.AddressDetails;
import com.sdms.entity.CommentDetails;
import com.sdms.entity.PersonDetails;
import com.sdms.repo.AddressRepo;
import com.sdms.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OneToOneBidirectiionalController {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private AddressRepo addressRepo;

    @GetMapping("/getPersonDetails")
    public PersonDetails getPersonDetails() {

        return PersonDetails.builder().AddressDetails(new AddressDetails()).build();
    }

    @PostMapping("/addPerson")
    public PersonDetails addPerson(@RequestBody PersonDetails post) {
        return personRepo.save(post);
    }

    @GetMapping("/deletePersonById/{id}")
    public void deletePersonById(@PathVariable("id") Integer id) {
        personRepo.deleteById(id);
    }
    @GetMapping("/deleteAddressById/{id}")
    public void deleteAddressById(@PathVariable("id") Integer id) {
        addressRepo.deleteById(id);
    }

    @PostMapping("/addAddressToPerson/{id}")
    public PersonDetails addChildToParent(@PathVariable("id") Integer id,@RequestBody AddressDetails commentDetails) {
        PersonDetails postDetails = personRepo.findById(id).get();
        postDetails.setDetails(commentDetails);
        return personRepo.save(postDetails);
    }

}