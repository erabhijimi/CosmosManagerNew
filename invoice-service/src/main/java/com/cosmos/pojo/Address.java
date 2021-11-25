package com.cosmos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private Long mobileNumber;
    private String name;
    private String address;
    private String landmark;
    private Long pincode;
    private Long alternateNumber;
}
