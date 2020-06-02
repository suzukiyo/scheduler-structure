package com.example.presentation.save;

import com.example.application.service.PaymentService;
import com.example.domain.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PaymentItemWriter implements ItemWriter<Payment> {
    PaymentService paymentService;

    @Override
    public void write(List<? extends Payment> items) throws Exception {
        items.forEach(it -> paymentService.register(it));
    }
}
