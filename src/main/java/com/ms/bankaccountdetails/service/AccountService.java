package com.ms.bankaccountdetails.service;

import com.ms.bankaccountdetails.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {

     List<Account> getAllAccounts();

     Account getAccountById(Long id);

     Account saveAccount(Account account);

     Account updateAccount(Long id, Account updatedAccount);

     void deleteAccount(Long id);

    Account getAccountDetails(String accountNumber);
}
