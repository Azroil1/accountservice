package ru.mtsbank.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mtsbank.accountservice.jwt.JwtUtil;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountDto {
    private int idBankAccount;
    private BigDecimal numBankAccount;
    private BigDecimal amount;

    @JsonCreator
    public AccountDto(@JsonProperty("idBankAccount") int idBankAccount,
                      @JsonProperty("numBankAccount") BigDecimal numBankAccount,
                      @JsonProperty("amount") BigDecimal amount) {
        this.idBankAccount = idBankAccount;
        this.numBankAccount = numBankAccount;
        this.amount = amount;
    }

}
