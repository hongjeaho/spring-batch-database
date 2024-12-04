package com.example.spring.batch.plugin.template;

import com.example.spring.batch.plugin.dto.PluginSampleRequest;
import com.example.spring.batch.plugin.template.worker.PluginTemplateWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "example.plugin")
public class PluginSampleWorkManager {

    private final PluginRegistry<PluginTemplateWorker, PluginSampleTemplate> pluginRegistry;

    public void work(@NonNull PluginSampleRequest request) {
        var resultWork = pluginRegistry
                .getPluginFor(request.getSampleTemplate())
                .orElseThrow(() -> new IllegalArgumentException("사용 가능한 템플릿이 없습니다."));

        resultWork.execute(request);
    }
}
