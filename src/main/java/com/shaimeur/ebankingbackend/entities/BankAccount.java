package com.shaimeur.ebankingbackend.entities;

import com.shaimeur.ebankingbackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name ="TYPE",length = 4, discriminatorType = DiscriminatorType.STRING)
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class BankAccount {
    @Id
    private String id ;
    private  Double balance ;
    private Date createdAt ;
    @Enumerated(EnumType.STRING)
    private AccountStatus status ;
    @ManyToOne
    private Customer customer ;
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AccountOperation> accountOperationList ;
}
