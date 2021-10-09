package com.cosmos.repository;

import com.cosmos.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Long> {
}
