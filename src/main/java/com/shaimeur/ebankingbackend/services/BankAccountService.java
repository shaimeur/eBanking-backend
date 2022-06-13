package com.shaimeur.ebankingbackend.services;

import com.shaimeur.ebankingbackend.entities.BankAccount;
import com.shaimeur.ebankingbackend.entities.CurrentAccount;
import com.shaimeur.ebankingbackend.entities.Customer;
import com.shaimeur.ebankingbackend.entities.SavingAccount;
import com.shaimeur.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.shaimeur.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.shaimeur.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);

    CurrentAccount saveCurrentBankAccount(double intialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingAccount saveSavingBankAccount(double intialBalance, double intrestRate, Long customerId) throws CustomerNotFoundException;

    List<Customer> listCustomer();

    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;


    List<BankAccount> bankAccountList();
}
