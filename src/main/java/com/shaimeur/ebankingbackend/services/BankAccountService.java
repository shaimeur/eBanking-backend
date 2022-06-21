package com.shaimeur.ebankingbackend.services;

import com.shaimeur.ebankingbackend.dtos.*;
import com.shaimeur.ebankingbackend.entities.BankAccount;
import com.shaimeur.ebankingbackend.entities.CurrentAccount;
import com.shaimeur.ebankingbackend.entities.Customer;
import com.shaimeur.ebankingbackend.entities.SavingAccount;
import com.shaimeur.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.shaimeur.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.shaimeur.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double intialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(double intialBalance, double intrestRate, Long customerId) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;


    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
