package com.cosmos.controller;

import com.cosmos.model.Address;
import com.cosmos.model.Addresses;
import com.cosmos.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public Addresses getAllAddress(){
        return addressService.getAllAddresses();
    }
    @GetMapping("/{mobileNumber}")
    public Address getAddressForUser(@PathVariable Long mobileNumber){
        log.info("getAddressForUser called for"+mobileNumber);
        return addressService.getAddressForUser(mobileNumber);
    }
    @PostMapping
    public Address saveAddress(@RequestBody Address address){
        log.info("saveAddress called for"+address.toString());
        return addressService.saveAddress(address);
    }
    @PutMapping("/{mobileNumber}")
    public Address updateAddressForUser(@PathVariable Long mobileNumber,@RequestBody Address address){
        log.info("updateAddressForUser called for"+address.toString()+" "+mobileNumber);
        return addressService.updateAddressForUser(mobileNumber,address);
    }
}
