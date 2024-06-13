package ru.mtsbank.accountservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtsbank.accountservice.dto.AccountDto;
import ru.mtsbank.accountservice.etity.Account;
import ru.mtsbank.accountservice.repository.AccountRepository;

@RestController
@RequestMapping("/mtsbank/myaccount")
public class HelloController {
    AccountRepository accountRepository;
    public HelloController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @GetMapping("/value")
    public ResponseEntity<String> value() {
        Account account = accountRepository.findByIdBankAccount(1);
        System.out.println(account);
        return ResponseEntity.ok().body(account.getAmount().toString());
    }
}
