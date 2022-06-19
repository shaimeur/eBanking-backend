package com.shaimeur.ebankingbackend;

import com.shaimeur.ebankingbackend.dtos.BankAccountDTO;
import com.shaimeur.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.shaimeur.ebankingbackend.dtos.CustomerDTO;
import com.shaimeur.ebankingbackend.dtos.SavingBankAccountDTO;
import com.shaimeur.ebankingbackend.entities.*;
import com.shaimeur.ebankingbackend.enums.AccountStatus;
import com.shaimeur.ebankingbackend.enums.OperationType;
import com.shaimeur.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.shaimeur.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.shaimeur.ebankingbackend.exceptions.CustomerNotFoundException;
import com.shaimeur.ebankingbackend.repositories.AccountOperationRepository;
import com.shaimeur.ebankingbackend.repositories.BankAccountRepository;
import com.shaimeur.ebankingbackend.repositories.CustomerRepository;
import com.shaimeur.ebankingbackend.services.BankAccountService;
import com.shaimeur.ebankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Ash", "Red", "Aiden").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@email.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }

            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0; i < 10; i++) {
                    // i just add  this variable in the restController incted of the BankAccountDTO cause is empty it would make some problémes
                    String accoundId;
                    if (bankAccount instanceof SavingBankAccountDTO) {
                        accoundId = ((SavingBankAccountDTO) bankAccount).getId();
                    } else {
                        accoundId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    try {
                        bankAccountService.credit(accoundId, 10000 + Math.random() * 120000, "Credit");
                    } catch (BalanceNotSufficientException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        bankAccountService.debit(accoundId, 1000 + Math.random() * 9000, "Debit");
                    } catch (BalanceNotSufficientException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            AccountOperationRepository accountOperationRepository,
                            BankAccountRepository bankAccountRepository
    ) {
        return args -> {
            Stream.of("Saad", "Nadir", "Agra").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 9000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterstRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(acc -> {
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 120000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }


            });
        };
    }

}
