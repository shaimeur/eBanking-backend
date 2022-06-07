package com.shaimeur.ebankingbackend.mappers.repositories;

import com.shaimeur.ebankingbackend.entities.AccountOperation;
import com.shaimeur.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    
}
