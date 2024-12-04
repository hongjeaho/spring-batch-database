package com.example.spring.batch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BatchJobListener implements JobExecutionListener {
    @Override
    public void afterJob(JobExecution jobExecution) {
        log.debug("Batch Job Finished: {} at {}", jobExecution.getJobInstance().getJobName(), System.currentTimeMillis());
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.debug("Batch Job Starting: {} at {}", jobExecution.getJobInstance().getJobName(), System.currentTimeMillis());
    }
}
