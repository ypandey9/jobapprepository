package com.embarkx.FirstSpring.job.impl;

import com.embarkx.FirstSpring.job.Job;
import com.embarkx.FirstSpring.job.JobRepository;
import com.embarkx.FirstSpring.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(long id)
    {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch(Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job=jobOptional.get();
            System.out.println("This is" +job);
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            System.out.println("After update " +job);
            jobRepository.save(job);
            return true;
        }
        return false;
    }

}
