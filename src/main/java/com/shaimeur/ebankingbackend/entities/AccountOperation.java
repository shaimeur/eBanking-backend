package com.shaimeur.ebankingbackend.entities;

import com.shaimeur.ebankingbackend.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date oprerationDate;
    private double amount ;
    private OperationType type ;
    @ManyToOne
    private BankAccount bankAccount ;
}
