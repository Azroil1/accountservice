package ru.mtsbank.accountservice.etity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank_account")
    private int idBankAccount;
    @Column(name = "num_bank_accounts", columnDefinition = "numeric(20,0)")
    private BigDecimal numBankAccount;
    @Column(name = "amount", columnDefinition = "numeric(20,2)")
    private BigDecimal amount;
}
