package com.shaimeur.ebankingbackend.web;

import com.shaimeur.ebankingbackend.dtos.CustomerDTO;
import com.shaimeur.ebankingbackend.entities.Customer;
import com.shaimeur.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
        private BankAccountService bankAccountService;
        @GetMapping("/customers")
        private List<CustomerDTO> customers(){
            return bankAccountService.listCustomers();
        }
}
