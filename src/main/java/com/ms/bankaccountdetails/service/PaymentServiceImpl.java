package com.ms.bankaccountdetails.service;

import com.ms.bankaccountdetails.model.Account;
import com.ms.bankaccountdetails.model.PaymentInstruction;
import com.ms.bankaccountdetails.model.PaymentRequest;
import com.ms.bankaccountdetails.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public PaymentInstruction createPaymentInstruction(Account account, BigDecimal amount, String instructionType) {

        // Your implementation to create a payment instruction
        // Replace this with your actual logic
        // For simplicity, returning a mock PaymentInstruction object
        PaymentInstruction paymentInstruction = new PaymentInstruction();
        paymentInstruction.setAccount(account);
//        paymentInstruction.setAccountNumber(account.getAccountNumber());
//        paymentInstruction.setCurrency(account.getCurrency());
        paymentInstruction.setInstructionType(instructionType);
        paymentInstruction.setAmount(amount);
//        paymentInstruction.setBank(account.getBank());
//        paymentInstruction.setBranch(account.getBranch());

        return paymentRepository.save(paymentInstruction);
    }

    @Override
    public PaymentInstruction sendPaymentInstruction(PaymentRequest paymentRequest) {
//        String debitAccountNumber =
        return null;
    }
}
