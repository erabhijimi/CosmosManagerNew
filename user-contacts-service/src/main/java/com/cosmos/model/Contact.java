package com.cosmos.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @CsvBindByPosition(position = 0)
    private String mobileNumber;
    @CsvBindByPosition(position = 5)
    private String userName;
    @CsvBindByPosition(position = 3)
    private String location;
    @CsvBindByPosition(position = 6)
    private String userSource;
    @CsvBindByPosition(position = 2)
    private boolean gotWhatsapp;
    @CsvBindByPosition(position = 4)
    private boolean usefull;
    @CsvBindByPosition(position = 1)
    private String addedDate;
}
