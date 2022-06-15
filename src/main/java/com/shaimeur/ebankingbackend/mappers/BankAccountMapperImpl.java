package com.shaimeur.ebankingbackend.mappers;

import com.shaimeur.ebankingbackend.dtos.CustomerDTO;
import com.shaimeur.ebankingbackend.entities.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
// MapStruct Jmapper ARE FRIMWORKS FOR MAPPING FROM ENTITTES =====> DTO
// IN OUR CASE WE ARE USING BeanUtils PROVIDED BY SPRING
@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();

        BeanUtils.copyProperties(customer, customerDTO);

           /* THIS SYNTAX IS THE EQUIVILANTE OF BeanUtils
           customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());*/

        return customerDTO;
    }


    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
}
