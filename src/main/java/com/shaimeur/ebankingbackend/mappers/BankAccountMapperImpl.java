package com.shaimeur.ebankingbackend.mappers;

import com.shaimeur.ebankingbackend.dtos.AccountOperationDTO;
import com.shaimeur.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.shaimeur.ebankingbackend.dtos.CustomerDTO;
import com.shaimeur.ebankingbackend.dtos.SavingBankAccountDTO;
import com.shaimeur.ebankingbackend.entities.AccountOperation;
import com.shaimeur.ebankingbackend.entities.CurrentAccount;
import com.shaimeur.ebankingbackend.entities.Customer;
import com.shaimeur.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

// MapStruct Jmapper ARE FRIMWORKS FOR MAPPING FROM ENTITTES =====> DTO
// IN OUR CASE WE ARE USING BeanUtils PROVIDED BY SPRING
@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        /* THIS SYNTAX IS THE EQUIVILANTE OF BeanUtils
           customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());*/

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }


    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {

        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;

    }
    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {

        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return  savingAccount ;

    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {

        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return  currentBankAccountDTO ;

    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {

        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount ;

    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
            AccountOperationDTO accountOperationDTO =  new AccountOperationDTO();
            BeanUtils.copyProperties(accountOperation,accountOperationDTO);
            return accountOperationDTO ;
    }


}
