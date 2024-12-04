package com.example.spring.batch.plugin.template.worker;

import com.example.spring.batch.plugin.dto.PluginSampleRequest;
import com.example.spring.batch.plugin.template.PluginSampleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "example.plugin")
public class PluginImageTemplateWorker implements PluginTemplateWorker {
    @Override
    public void execute(PluginSampleRequest pluginSampleRequest) {
        log.info("############################");
        log.info("image plugin template");
        log.info("############################");
    }

    @Override
    public boolean supports(@NonNull PluginSampleTemplate sampleTemplate) {
        return sampleTemplate == PluginSampleTemplate.IMAGE_TEMPLATE;
    }
}
