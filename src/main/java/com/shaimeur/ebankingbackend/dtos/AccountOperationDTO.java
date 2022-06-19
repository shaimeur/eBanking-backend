package com.shaimeur.ebankingbackend.dtos;

import com.shaimeur.ebankingbackend.entities.BankAccount;
import com.shaimeur.ebankingbackend.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
public class AccountOperationDTO {

    private Long id ;
    private Date operationDate;
    private double amount ;
    private OperationType type ;
    private String description;
}
