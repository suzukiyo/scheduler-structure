package com.example.presentation;

import com.example.domain.model.Payment;
import com.example.presentation.external.PaymentExternalJob;
import com.example.presentation.save.PaymentItemReader;
import com.example.presentation.save.PaymentItemWriter;
import com.example.presentation.save.PaymentProcessor;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PaymentStep {
    StepBuilderFactory steps;
    AutowireCapableBeanFactory beanFactory;

    PaymentItemReader reader;
    PaymentProcessor processor;
    PaymentItemWriter writer;

    @Bean("paymentOneStep")
    public Step paymentOneStep() {
        return steps.get("paymentOneStep")
                .tasklet(paymentTasklet())
                .build();
    }

    public PaymentExternalJob paymentTasklet() {
        PaymentExternalJob tasklet = new PaymentExternalJob();
        beanFactory.autowireBean(tasklet);
        return tasklet;
    }

    @Bean("paymentTwoStep")
    Step paymentTwoStep() {
        return steps.get("paymentTwoStep")
                .<Payment, Payment>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
