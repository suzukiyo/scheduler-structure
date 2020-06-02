package com.example.presentation;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 一括決済バッチを想定
 */
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class PaymentBatch {

    JobBuilderFactory jobs;
    Step paymentOneStep;
    Step paymentTwoStep;

    @Bean
    public Job paymentJob() {
        return jobs.get("paymentJob")
                .incrementer(new RunIdIncrementer())
                .start(paymentOneStep)
                .next(paymentTwoStep)
                .build();
    }

}
