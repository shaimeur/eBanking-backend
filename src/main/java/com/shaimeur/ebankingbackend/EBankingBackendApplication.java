package com.shaimeur.ebankingbackend;

import com.shaimeur.ebankingbackend.entities.AccountOperation;
import com.shaimeur.ebankingbackend.entities.CurrentAccount;
import com.shaimeur.ebankingbackend.entities.Customer;
import com.shaimeur.ebankingbackend.entities.SavingAccount;
import com.shaimeur.ebankingbackend.enums.AccountStatus;
import com.shaimeur.ebankingbackend.enums.OperationType;
import com.shaimeur.ebankingbackend.repositories.AccountOperationRepository;
import com.shaimeur.ebankingbackend.repositories.BankAccountRepository;
import com.shaimeur.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository ,
                            AccountOperationRepository accountOperationRepository,
                            BankAccountRepository bankAccountRepository
                            ) {
        return args -> {
                Stream.of("Saad","Nadir","Agra").forEach(name->{
                    Customer customer = new Customer() ;
                    customer.setName(name);
                    customer.setEmail(name +"@gmail.com");
                    customerRepository.save(customer) ;
                });
                customerRepository.findAll().forEach(cust ->{
                    CurrentAccount currentAccount = new CurrentAccount();
                    currentAccount.setId(UUID.randomUUID().toString());
                    currentAccount.setBalance(Math.random()*9000);
                    currentAccount.setCreatedAt(new Date());
                    currentAccount.setStatus(AccountStatus.CREATED);
                    currentAccount.setCustomer(cust);
                    currentAccount.setOverDraft(9000);
                    bankAccountRepository.save(currentAccount);


                    SavingAccount savingAccount = new SavingAccount();
                    savingAccount.setId(UUID.randomUUID().toString());
                    savingAccount.setBalance(Math.random()*9000);
                    savingAccount.setCreatedAt(new Date());
                    savingAccount.setStatus(AccountStatus.CREATED);
                    savingAccount.setCustomer(cust);
                    savingAccount.setInterstRate(5.5);
                    bankAccountRepository.save(savingAccount);
                });

                bankAccountRepository.findAll().forEach(acc->{
                    for (int i = 0; i < 5; i++) {
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setOperationDate(new Date());
                        accountOperation.setAmount(Math.random()*120000);
                        accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                        accountOperation.setBankAccount(acc);
                        accountOperationRepository.save(accountOperation);
                    }

                });
        };
    }

}
