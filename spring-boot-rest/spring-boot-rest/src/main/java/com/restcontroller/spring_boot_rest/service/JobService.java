package com.restcontroller.spring_boot_rest.service;

import com.restcontroller.spring_boot_rest.model.JobPost;
import com.restcontroller.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService {
    @Autowired
    private JobRepo repo;

    public void addJob(JobPost jobpost){
        repo.addJob(jobpost);
    }

    public List<JobPost> getAllJobs(){
       List<JobPost> jobs= repo.getAllJobs();
    return jobs;
    }


    public JobPost getJob(int JobPost) {
        return repo.getJob(JobPost);
    }

    public void updateJob(JobPost jobPost) {
        repo.updateJob(jobPost);
    }

    public void deleteJob(int postId) {
        repo.delete(postId);
    }
}
