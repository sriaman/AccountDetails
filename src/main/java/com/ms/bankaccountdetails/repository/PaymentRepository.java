package com.ms.bankaccountdetails.repository;

import com.ms.bankaccountdetails.model.PaymentInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInstruction,Long> {
}
