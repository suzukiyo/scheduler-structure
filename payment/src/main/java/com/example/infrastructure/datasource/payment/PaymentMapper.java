package com.example.infrastructure.datasource.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void findList();
    void resisters();
}
