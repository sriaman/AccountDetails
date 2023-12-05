package com.ms.bankaccountdetails.model;

import com.ms.bankaccountdetails.constant.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="savings_account")
@Entity
public class SavingsAccount  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Currency currency;
    private String bank;
    private String branch;
}
