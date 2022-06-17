package com.shaimeur.ebankingbackend.dtos;

import com.shaimeur.ebankingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;


//@DiscriminatorColumn(name ="TYPE",length = 4, discriminatorType = DiscriminatorType.STRING)
@Data
public  class CurrentBankAccountDTO extends BankAccountDTO{
    private String id ;
    private  Double balance ;
    private Date createdAt ;
    private AccountStatus status ;
    private CustomerDTO customerDTO ;
    private double overDraft ;
}
