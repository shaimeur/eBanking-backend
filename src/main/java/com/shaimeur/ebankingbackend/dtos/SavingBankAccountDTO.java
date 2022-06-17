package com.shaimeur.ebankingbackend.dtos;

import com.shaimeur.ebankingbackend.entities.AccountOperation;
import com.shaimeur.ebankingbackend.enums.AccountStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



//@DiscriminatorColumn(name ="TYPE",length = 4, discriminatorType = DiscriminatorType.STRING)
@Data
public  class SavingBankAccountDTO  extends BankAccountDTO{
    private String id ;
    private  Double balance ;
    private Date createdAt ;
    private AccountStatus status ;
    private CustomerDTO customerDTO ;
    private double interstRate ;
}
