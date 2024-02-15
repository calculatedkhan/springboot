package com.example.imdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    //need fixing
    @Query(value = "SELECT a.* FROM address a JOIN customer c ON a.address_id = c.address_id WHERE c.first_name = :firstName AND c.last_name = :lastName", nativeQuery = true)
    public Address findAddressByCustomerFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
