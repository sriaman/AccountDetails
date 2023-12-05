package com.ms.bankaccountdetails.model;

import com.ms.bankaccountdetails.constant.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Entity
public class User {

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private SavingsAccount savingsAccount;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private CurrentAccount currentAccount;

}
