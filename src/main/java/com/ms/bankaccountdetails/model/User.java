package com.ms.bankaccountdetails.model;

import com.ms.bankaccountdetails.constant.Currency;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @Id
    private int id;
    private SavingsAccount savingsAccount;
    private CurrentAccount currentAccount;

}
