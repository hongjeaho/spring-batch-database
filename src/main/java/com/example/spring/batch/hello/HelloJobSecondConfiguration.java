package com.example.spring.batch.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "example.hello.second")
@Slf4j
public class HelloJobSecondConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, Step helloStep) {
        return new JobBuilder("example.second.job", jobRepository)
                .start(helloStep)
                .build();
    }

    @Bean
    public Step helloStep (Tasklet helloTasklet, JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("example.second.step", jobRepository)
                .tasklet(helloTasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet helloTasklet() {
        return (contribution, chunkContext) -> {

            log.info("++++++++++++++++++++++++++");
            log.info("helloTasklet Second!!!");
            log.info("++++++++++++++++++++++++++");

            return RepeatStatus.FINISHED;
        };
    }
}