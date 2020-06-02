package com.example.presentation.cancel;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * キャンセルバッチを想定
 */
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class CancelBatch {

    JobBuilderFactory jobs;
    Step cancelStep;

    @Bean
    public Job cancelJob() {
        return jobs.get("cancelJob")
                .incrementer(new RunIdIncrementer())
                .start(cancelStep)
                .build();
    }

}
