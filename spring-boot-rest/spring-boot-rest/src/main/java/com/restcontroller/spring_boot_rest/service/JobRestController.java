package com.restcontroller.spring_boot_rest.service;

import com.restcontroller.spring_boot_rest.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//what is rest controller diff b/w rest controller vs controller
// what is RequestBody
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class JobRestController {


    @Autowired
    private JobService service;


    //@GetMapping(path="jobPosts", produces={"application/json"})
    //generally jakarta will send the data in the form of json if we need in xml format then
    //jarkarta xml mvn
    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("JobPost/{jobPost}")
    public JobPost getJobPost(@PathVariable("jobPost") int jobPost){
        return service.getJob(jobPost);
    }

    @PostMapping("JobPost")
    public void addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
    }

    @PutMapping("JobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "Done";
    }



}
