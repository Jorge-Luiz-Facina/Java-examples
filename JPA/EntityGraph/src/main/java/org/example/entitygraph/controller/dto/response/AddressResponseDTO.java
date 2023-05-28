package org.example.entitygraph.controller.dto.response;

public class AddressResponseDTO {

    private Integer number;

    private CityResponseDTO city;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CityResponseDTO getCity() {
        return city;
    }

    public void setCity(CityResponseDTO city) {
        this.city = city;
    }
}
