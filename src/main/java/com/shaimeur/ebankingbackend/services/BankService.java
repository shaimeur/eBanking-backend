package com.shaimeur.ebankingbackend.services;

import com.shaimeur.ebankingbackend.entities.BankAccount;
import com.shaimeur.ebankingbackend.entities.CurrentAccount;
import com.shaimeur.ebankingbackend.entities.SavingAccount;
import com.shaimeur.ebankingbackend.mappers.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository ;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("2e9b2188-3e82-48a0-908c-47d743318d40").orElse(null);
        if (bankAccount!= null) {
            System.out.println("*******************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("OVER DRAFT => " + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("INTEREST RATE => " + ((SavingAccount) bankAccount).getInterstRate());
            }
            bankAccount.getAccountOperationList().forEach(op -> {
                System.out.println("================================");
                System.out.println(op.getType());
                System.out.println(op.getAmount());
                System.out.println(op.getOperationDate());

            });
        }
    }
}
