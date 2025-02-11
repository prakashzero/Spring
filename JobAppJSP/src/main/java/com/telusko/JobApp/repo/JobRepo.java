package com.telusko.JobApp.repo;

import com.telusko.JobApp.model.JobPost;
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

}
