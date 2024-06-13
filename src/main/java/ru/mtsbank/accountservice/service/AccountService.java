package ru.mtsbank.accountservice.service;

import ru.mtsbank.accountservice.exception.NoMoneyException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

public interface AccountService {
    BigDecimal getMoney(int account_id, BigDecimal money) throws NoMoneyException;
    BigDecimal getBalance(int account_id) throws NoMoneyException;
    BigDecimal addMoney(int account_id, BigDecimal money) throws NoMoneyException;
}
