package com.ms.bankaccountdetails.controller;

import com.ms.bankaccountdetails.model.Account;
import com.ms.bankaccountdetails.model.PaymentInstruction;
import com.ms.bankaccountdetails.model.PaymentRequest;
import com.ms.bankaccountdetails.service.AccountService;
import com.ms.bankaccountdetails.service.AccountServiceImpl;
import com.ms.bankaccountdetails.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountService accountService;
    @PostMapping("/send-instruction")
    public ResponseEntity<?> sendPaymentInstruction(@RequestBody PaymentRequest paymentRequest) {


//        PaymentInstruction paymentInstruction = paymentService.sendPaymentInstruction(paymentRequest);
        // Validate and get account details from Account API
        Account debitAccount = getAccountDetails(paymentRequest.getDebitAccountNumber());
        Account creditAccount = getAccountDetails(paymentRequest.getCreditAccountNumber());

        if (debitAccount == null || creditAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid account details provided");
        }

        // Create debit instruction
        PaymentInstruction debitInstruction = createPaymentInstruction(debitAccount, paymentRequest.getAmount(), "Debit");

        // Create credit instruction
        PaymentInstruction creditInstruction = createPaymentInstruction(creditAccount, paymentRequest.getAmount(), "Credit");

        // Print instructions with additional details
        printPaymentInstructionDetails(debitInstruction);
        printPaymentInstructionDetails(creditInstruction);

        // Your logic to actually send payment instructions
        List<PaymentInstruction> lstPaymentInstruction = List.of(debitInstruction,creditInstruction);
        return ResponseEntity.status(HttpStatus.OK).body(lstPaymentInstruction);
    }

    private Account getAccountDetails(String accountNumber) {
        // Your logic to call the Account API and get account details
        // Replace this with your actual implementation
        // For simplicity, returning a mock Account object
        return accountService.getAccountDetails(accountNumber);
    }

    private PaymentInstruction createPaymentInstruction(Account account, BigDecimal amount, String instructionType) {
        return paymentService.createPaymentInstruction(account, amount, instructionType);
    }

    private void printPaymentInstructionDetails(PaymentInstruction paymentInstruction) {
        // Your logic to print payment instruction details
        // Replace this with your actual implementation
        System.out.println(paymentInstruction.toString());
    }
}
