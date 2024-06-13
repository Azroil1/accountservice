package ru.mtsbank.accountservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.accountservice.dto.AccountDto;
import ru.mtsbank.accountservice.etity.Account;
import ru.mtsbank.accountservice.exception.NoMoneyException;
import ru.mtsbank.accountservice.jwt.JwtUtil;
import ru.mtsbank.accountservice.service.AccountService;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/mtsbank/account")
public class AccountController {
    AccountService accountService;
    JwtUtil jwtUtil;
    public AccountController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/give")
    public ResponseEntity<?> giveMyMoney(@RequestHeader("Authorization") String token, @RequestBody AccountDto account) {
        String jwtToken = token.replace("Bearer ", "");
        log.info("jwtToken: " + jwtToken);
        String accountId = jwtUtil.extractAccountId(jwtToken);
        log.info("accountId: {}", accountId);
        BigDecimal money = null;
        try {
            money = accountService.getMoney(Integer.parseInt(accountId), account.getAmount());
        }
        catch (NoMoneyException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(money);
    }
    @GetMapping("/balance")
    public ResponseEntity<?> getMyBalance(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        String accountId = jwtUtil.extractAccountId(jwtToken);
        log.info(accountId);

        try{
            log.info(accountService.getBalance(Integer.parseInt(accountId)).toString());
            return ResponseEntity.ok().body(accountService.getBalance(Integer.parseInt(accountId)));
        }
        catch (NoMoneyException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addMoney(@RequestHeader("Authorization") String token, @RequestBody AccountDto account) {
        String jwtToken = token.replace("Bearer ", "");
        log.info("jwtToken: " + jwtToken);
        String accountId = jwtUtil.extractAccountId(jwtToken);
        log.info("accountId: {}", accountId);
        BigDecimal newBalance;
        try {
            newBalance = accountService.addMoney(Integer.parseInt(accountId), account.getAmount());
        } catch (Exception e) {
            log.error("Error while adding money to the account", e);
            return ResponseEntity.status(500).body("An error occurred while adding money to the account");
        }
        return ResponseEntity.ok().body(newBalance);
    }

}
