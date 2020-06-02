package com.example.application.service;

import com.example.domain.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment adjust(Payment item) {
        return item;
    }

    public Payment register(Payment item) {
        return item;
    }

}
