package com.example.presentation.cancel;

import com.example.presentation.cancel.external.CancelExternalJob;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CancelStep {
    StepBuilderFactory steps;
    AutowireCapableBeanFactory beanFactory;

    @Bean("cancelStep")
    public Step cancelStep() {
        return steps.get("cancelStep")
                .tasklet(cancelTasklet())
                .build();
    }

    public CancelExternalJob cancelTasklet() {
        CancelExternalJob tasklet = new CancelExternalJob();
        beanFactory.autowireBean(tasklet);
        return tasklet;
    }
}
