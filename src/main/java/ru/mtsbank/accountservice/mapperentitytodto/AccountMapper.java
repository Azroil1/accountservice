package ru.mtsbank.accountservice.mapperentitytodto;

import org.postgresql.util.PGmoney;
import ru.mtsbank.accountservice.dto.AccountDto;
import ru.mtsbank.accountservice.etity.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

public class AccountMapper {
    public static AccountDto accountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setIdBankAccount(account.getIdBankAccount());
        accountDto.setNumBankAccount(account.getNumBankAccount());
        accountDto.setAmount(account.getAmount());
       return accountDto;
    }
    public static Account accountDtoToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setIdBankAccount(accountDto.getIdBankAccount());
        account.setNumBankAccount(accountDto.getNumBankAccount());
        account.setAmount(accountDto.getAmount());
        return account;
    }
}
