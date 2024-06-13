package ru.mtsbank.accountservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.mtsbank.accountservice.dto.AccountDto;
import ru.mtsbank.accountservice.etity.Account;
import ru.mtsbank.accountservice.exception.NoMoneyException;
import ru.mtsbank.accountservice.mapperentitytodto.AccountMapper;
import ru.mtsbank.accountservice.repository.AccountRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository repository;
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }
    @Transactional(rollbackOn = NoMoneyException.class)
    @Override
    public BigDecimal getMoney(int account_id, BigDecimal money) throws NoMoneyException {
        Account account = repository.findByIdBankAccount(account_id);
        if (account == null) {
            throw new IllegalArgumentException("Account with id " + account_id + " not found");
        }
        BigDecimal accountBalance = account.getAmount();
        if (accountBalance.compareTo(money) >= 0) {
            BigDecimal newBalance = accountBalance.subtract(money);
            account.setAmount(newBalance);
            repository.save(account);
            return money;
        } else {
            throw new NoMoneyException("Not enough money on the account");
        }
    }

    @Override
    @Transactional(rollbackOn = NoMoneyException.class)
    public BigDecimal getBalance(int account_id) throws NoMoneyException {
        AccountDto accountDto = AccountMapper.accountToAccountDto(repository.findByIdBankAccount(account_id));
        BigDecimal accountBalance = accountDto.getAmount();
        log.info(accountBalance.toString());
        if (accountBalance.compareTo(BigDecimal.ZERO) >= 0) {
            return accountBalance;
        }
        throw new NoMoneyException("Not enough money on the account");
    }

    @Override
    public BigDecimal addMoney(int account_id, BigDecimal money) throws NoMoneyException {
        Account account = repository.findByIdBankAccount(account_id);
        if (account == null) {
            throw new IllegalArgumentException("Account with id " + account_id + " not found");
        }
        BigDecimal accountBalance = account.getAmount();
        BigDecimal newBalance = accountBalance.add(money);
        account.setAmount(newBalance);
        repository.save(account);
        return account.getAmount();
    }
}
