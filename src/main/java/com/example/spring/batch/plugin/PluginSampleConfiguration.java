package com.example.spring.batch.plugin;

import com.example.spring.batch.plugin.dto.PluginSampleRequest;
import com.example.spring.batch.plugin.template.PluginSampleTemplate;
import com.example.spring.batch.plugin.template.PluginSampleWorkManager;
import com.example.spring.batch.plugin.template.worker.PluginTemplateWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "example.plugin")
@EnablePluginRegistries(PluginTemplateWorker.class)
@RequiredArgsConstructor
@Slf4j
public class PluginSampleConfiguration {

    private final PluginSampleWorkManager sampleWorkManager;

    @Bean
    public Job job(JobRepository jobRepository, Step helloStep, JobParametersIncrementer jobParametersIncrementer) {
        return new JobBuilder("example.plugin.job", jobRepository)
                .incrementer(jobParametersIncrementer)
                .start(helloStep)
                .build();
    }

    @Bean
    public Step helloStep (Tasklet helloTasklet, JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("example.plugin.step", jobRepository)
                .tasklet(helloTasklet, transactionManager)
                .build();

    }

    @Bean
    public Tasklet helloTasklet() {
        return (contribution, chunkContext) -> {

            var imageRequest = new PluginSampleRequest(PluginSampleTemplate.IMAGE_TEMPLATE);
            var textRequest = new PluginSampleRequest(PluginSampleTemplate.TEXT_TEMPLATE);

            log.info("++++++++++++++++++++++++++");
            sampleWorkManager.work(imageRequest);
            sampleWorkManager.work(textRequest);
            log.info("++++++++++++++++++++++++++");

            return RepeatStatus.FINISHED;
        };
    }

}
