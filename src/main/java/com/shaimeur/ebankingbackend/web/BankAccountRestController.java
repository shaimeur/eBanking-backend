package com.shaimeur.ebankingbackend.web;

import com.shaimeur.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class BankAccountRestController {
    private BankAccountService bankAccountService;



}
