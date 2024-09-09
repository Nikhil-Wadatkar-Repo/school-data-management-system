package com.sdms.controller;

import com.sdms.entity.Book;
import com.sdms.entity.StudDetails;
import com.sdms.repo.BookRepo;
import com.sdms.repo.StudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManyToOneControllerTest {

    @Autowired
    private BookRepo brepo;
    @Autowired
    private StudRepo srepo;
@GetMapping("/savekaro")
    public void saveDetails(){
//    Mster tables
        Book bk1 = new Book(101, "DS");
        Book bk2 = new Book(102, "SB");

        //save this
        brepo.save(bk1);
        brepo.save(bk2);

        //create StudDetails : non master tables
        StudDetails st1 = new StudDetails(50, "SAM", bk1);
        StudDetails st2 = new StudDetails(60, "JHON", bk1);
        StudDetails st3 = new StudDetails(70, "ANGELA", bk2);
        StudDetails st4 = new StudDetails(80, "BROCK", bk2);

        srepo.save(st1);
        srepo.save(st2);
        srepo.save(st3);
        srepo.save(st4);

    }

    @GetMapping("/studById/{id}")
    public StudDetails getDetails(@PathVariable("id") int id){
        StudDetails studDetails=srepo.findById(id).get();
        System.out.println("Details:::  "+studDetails);
        return studDetails;
    }


    @GetMapping("/allStuds")
    public List<StudDetails> allStuds(){
        return srepo.findAll();
    }
}
