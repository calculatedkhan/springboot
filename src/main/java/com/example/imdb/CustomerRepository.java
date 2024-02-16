package com.example.imdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    @Query(value = "SELECT c.* FROM customer c WHERE email = :emailAddress", nativeQuery = true)
    public Customer getCustomerByEmail(@Param("emailAddress") String emailAddress);
}
