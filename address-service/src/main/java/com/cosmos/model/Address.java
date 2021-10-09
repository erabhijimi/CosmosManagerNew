package com.cosmos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address003")
public class Address {
    @Id
    private Long custId;
    private String custName;
    private String custAddressLine1;
    private String landmark;
    private Long pincode;
    private Long alternateNumber;
}
