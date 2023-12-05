package com.ms.bankaccountdetails.service;

import com.ms.bankaccountdetails.model.Account;
import com.ms.bankaccountdetails.model.PaymentInstruction;
import com.ms.bankaccountdetails.model.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentInstruction createPaymentInstruction(Account account, BigDecimal amount, String instructionType);

    PaymentInstruction sendPaymentInstruction(PaymentRequest paymentRequest);
}
