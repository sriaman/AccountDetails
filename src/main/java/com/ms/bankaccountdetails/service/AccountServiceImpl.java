package com.ms.bankaccountdetails.service;

import com.ms.bankaccountdetails.exception.AccountNotFoundException;
import com.ms.bankaccountdetails.model.Account;
import com.ms.bankaccountdetails.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Account Not found for Id: "+id));
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id).orElse(null);

        if (existingAccount != null) {
            existingAccount.setCurrency(updatedAccount.getCurrency());
            existingAccount.setBank(updatedAccount.getBank());
            existingAccount.setBranch(updatedAccount.getBranch());
            existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
            existingAccount.setBalance(updatedAccount.getBalance());

            return accountRepository.save(existingAccount);
        }

        return null; // Handle not found case
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getAccountDetails(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
