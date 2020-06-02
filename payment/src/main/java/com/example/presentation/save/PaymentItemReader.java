package com.example.presentation.save;

import com.example.domain.model.Payment;
import com.example.infrastructure.datasource.payment.PaymentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@StepScope
public class PaymentItemReader extends MyBatisCursorItemReader<Payment> {

    @Override
    public Payment read() throws Exception {
        return super.read();
    }

    public PaymentItemReader(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory,
                             @Value("#{jobParameters['date']}") String date) {
        super.setQueryId(PaymentMapper.class.getName() + ".findList");
        super.setSqlSessionFactory(sqlSessionFactory);

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("date", date);

        super.setParameterValues(parameterValues);
    }
}
