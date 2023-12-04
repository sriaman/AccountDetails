package com.ms.bankaccountdetails.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bank {

    private String name;
    private Account account;
    private String branch;
}
