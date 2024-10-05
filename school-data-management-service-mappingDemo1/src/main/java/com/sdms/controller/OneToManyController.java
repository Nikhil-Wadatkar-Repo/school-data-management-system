package com.sdms.controller;

import com.sdms.entity.CommentDetails;
import com.sdms.entity.PostDetails;
//import com.sdms.repo.CommentRepo;
import com.sdms.repo.ExamRepo;
import com.sdms.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OneToManyController {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ExamRepo commentRepo;

    @GetMapping("/getPost")
    public PostDetails getPost() {
        return new PostDetails();
    }

    @PostMapping("/addParent")
    public PostDetails addParent(@RequestBody PostDetails post) {
        return postRepo.save(post);
    }

    @GetMapping("/deleteParentById/{id}")
    public void deleteParentById(@PathVariable("id") Integer id) {
        postRepo.deleteById(id);
    }

    @GetMapping("/deleteChildById/{id}")
    public void deleteChildById(@PathVariable("id") Integer id) {
        commentRepo.deleteById(id);
    }

    @PostMapping("/addChildToParent/{id}")
    public PostDetails addChildToParent(@PathVariable("id") Integer id,@RequestBody CommentDetails commentDetails) {
        PostDetails postDetails = postRepo.findById(id).get();
        postDetails.getCommentList().add(commentDetails);
        return postRepo.save(postDetails);
    }

}