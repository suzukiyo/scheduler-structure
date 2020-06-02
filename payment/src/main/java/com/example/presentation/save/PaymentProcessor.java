package com.example.presentation.save;

import com.example.application.service.PaymentService;
import com.example.domain.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
@AllArgsConstructor
public class PaymentProcessor implements ItemProcessor<Payment, Payment> {

    PaymentService paymentService;

    @Override
    public Payment process(Payment item) throws Exception {
        return paymentService.adjust(item);
    }
}
