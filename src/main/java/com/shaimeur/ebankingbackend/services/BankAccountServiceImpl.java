package com.shaimeur.ebankingbackend.services;

import com.shaimeur.ebankingbackend.dtos.CustomerDTO;
import com.shaimeur.ebankingbackend.entities.*;
import com.shaimeur.ebankingbackend.enums.OperationType;
import com.shaimeur.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.shaimeur.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.shaimeur.ebankingbackend.exceptions.CustomerNotFoundException;
import com.shaimeur.ebankingbackend.mappers.BankAccountMapperImpl;
import com.shaimeur.ebankingbackend.repositories.AccountOperationRepository;
import com.shaimeur.ebankingbackend.repositories.BankAccountRepository;
import com.shaimeur.ebankingbackend.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;

    private BankAccountMapperImpl dtoMapper;


    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("saving new customer");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double intialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found!!");

        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(intialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);

        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return savedBankAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double intialBalance, double intrestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found!!");

        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(intialBalance);
        savingAccount.setInterstRate(intrestRate);
        savingAccount.setCustomer(customer);

        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);

        return savedBankAccount;
    }


    @Override
    public List<CustomerDTO> listCustomers() {

        List<Customer> customers = customerRepository.findAll();


        // programmation fonctionnel
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> dtoMapper.fromCustomer(customer))
                .collect(Collectors.toList());


        // la programmation impérative

       /* List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer:customers){
            CustomerDTO customerDTO = dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        return  customerDTOS ;*/
        return customerDTOS ;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).
                orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = getBankAccount(accountId);
                if (bankAccount.getBalance() < amount)
                    throw new BalanceNotSufficientException("Balance Not sufficient");
                AccountOperation accountOperation = new AccountOperation();
                accountOperation.setType(OperationType.DEBIT);
                accountOperation.setAmount(amount);
                accountOperation.setDescription(description);
                accountOperation.setOperationDate(new Date());
                accountOperation.setBankAccount(bankAccount);
                accountOperationRepository.save(accountOperation);
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                bankAccountRepository.save(bankAccount);

    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
                BankAccount bankAccount = getBankAccount(accountId);

                AccountOperation accountOperation = new AccountOperation();
                accountOperation.setType(OperationType.CREDIT);
                accountOperation.setAmount(amount);
                accountOperation.setDescription(description);
                accountOperation.setOperationDate(new Date());
                accountOperation.setBankAccount(bankAccount);
                accountOperationRepository.save(accountOperation);
                bankAccount.setBalance(bankAccount.getBalance() + amount);
                bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
            debit(accountIdSource,amount,"Transfer to " + accountIdDestination);
            credit(accountIdDestination,amount,"Transfer from " + accountIdSource  );
    }
    @Override
   public List<BankAccount> bankAccountList(){
        return bankAccountRepository.findAll();
    }
}
