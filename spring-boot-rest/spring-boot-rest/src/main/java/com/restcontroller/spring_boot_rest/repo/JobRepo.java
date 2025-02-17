package com.restcontroller.spring_boot_rest.repo;

import com.restcontroller.spring_boot_rest.model.JobPost;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JobRepo {

List<JobPost> jobs=new ArrayList<>(Arrays.asList(
        new JobPost(1,"Java developer","Must have 2exp",2,List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
        new JobPost(2,"c developer","Must have 2exp",2,List.of("c", "assembly"))
));


    public List<JobPost> getAllJobs(){
        return jobs;
    }

    public void addJob(JobPost jobpost){
        jobs.add(jobpost);
        System.out.println(jobs);
    }

    public JobPost getJob(int JobPost) {
        for(JobPost job:jobs){
            if(job.getPostId()==JobPost){
                return job;
            }
        }
        return null;
    }

    public void updateJob(JobPost jobPost) {
        for(JobPost jobPost1: jobs){
            if(jobPost1.getPostId()==jobPost.getPostId()){
                jobPost1.setPostProfile(jobPost.getPostProfile());
                jobPost1.setPostDesc(jobPost.getPostDesc());
                jobPost1.setReqExperience(jobPost.getReqExperience());
                jobPost1.setPostTechStack(jobPost.getPostTechStack());
            }
        }
    }

    public void delete(int postId) {
        for(JobPost jobPost: jobs){
            if(jobPost.getPostId()==postId){
                jobs.remove(jobPost);
            }
        }
    }
}
