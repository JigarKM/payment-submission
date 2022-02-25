package com.camvio.paymentsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camvio.paymentsystem.entity.PaymentEntity;


@Repository
public interface PaymentEntityRepo extends JpaRepository<PaymentEntity, Long> {

}
