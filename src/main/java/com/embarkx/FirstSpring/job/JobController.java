package com.embarkx.FirstSpring.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class JobController {
    private List<Job> jobs=new ArrayList<>();
    private JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>>findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> addJob(@RequestBody Job job){
    jobService.createJob(job);
    return new ResponseEntity<>("Job Added Successfully!!",HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getById(@PathVariable long id)
    {
        Job job=jobService.getJobById(id);
        if(job!=null)
        {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id)
    {
        boolean deleted=jobService.deleteJobById(id);
        if(deleted)
        return new ResponseEntity<>("Deleted Successfully !!",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob)
    {
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated)
            return new ResponseEntity<>("Job updated successfully!!",HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
