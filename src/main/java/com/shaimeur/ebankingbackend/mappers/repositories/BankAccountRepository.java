package com.shaimeur.ebankingbackend.mappers.repositories;

import com.shaimeur.ebankingbackend.entities.BankAccount;
import com.shaimeur.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    
}
