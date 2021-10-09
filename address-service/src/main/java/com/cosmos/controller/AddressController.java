package com.cosmos.controller;

import com.cosmos.model.Address;
import com.cosmos.model.Addresses;
import com.cosmos.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public Addresses getAllAddress(){
        return addressService.getAllAddresses();
    }
    @GetMapping("/{mobileNumber}")
    public Address getAddressForUser(@PathVariable Long mobileNumber){
        return addressService.getAddressForUser(mobileNumber);
    }
    @PostMapping
    public Address saveAddress(@RequestBody Address address){
        return addressService.saveAddress(address);
    }
    @PutMapping("/{mobileNumber}")
    public Address updateAddressForUser(@PathVariable Long mobileNumber,@RequestBody Address address){
        return addressService.updateAddressForUser(mobileNumber,address);
    }
}
