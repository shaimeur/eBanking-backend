package com.shaimeur.ebankingbackend.repositories;

import com.shaimeur.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
