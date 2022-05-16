package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressEntity {

    private Integer number;
    private String streetName;
    private String country;
}
