package ru.mtsbank.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtsbank.accountservice.etity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByIdBankAccount(int idBankAccount);
}
