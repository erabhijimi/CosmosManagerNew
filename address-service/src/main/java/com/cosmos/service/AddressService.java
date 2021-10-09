package com.cosmos.service;

import com.cosmos.controller.AddressController;
import com.cosmos.model.Address;
import com.cosmos.model.Addresses;
import com.cosmos.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressForUser(Long mobileNumber) {
        Optional<Address> dbaddress = addressRepository.findById(mobileNumber);
        if(dbaddress.isPresent()){
            log.info("Address is present for specified mobile number"+mobileNumber);
            return dbaddress.get();
        }else {
            return dbaddress.orElse(new Address());
        }
    }

    public Address saveAddress(Address address) {
        address.setPincode(759122L);
        log.info("Address saved successfully"+address.toString());
        return addressRepository.save(address);
    }

    public Addresses getAllAddresses() {
        Addresses addresses = new Addresses();
        List address =addressRepository.findAll();
        addresses.setAddressesList(address);
        return addresses;
    }

    public Address updateAddressForUser(Long mobileNumber, Address address) {
        return addressRepository.save(address);
    }
}
