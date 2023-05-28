package org.example.entitygraph.mapper;

import org.example.entitygraph.controller.dto.response.AddressResponseDTO;
import org.example.entitygraph.entity.Address;
import java.util.List;

public class AddressMapper {

    public static AddressResponseDTO toResponseDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setNumber(address.getNumber());
        addressResponseDTO.setCity(CityMapper.toResponseDTO(address.getCity()));
        return addressResponseDTO;
    }

    public static List<AddressResponseDTO> toListResponseDTO(List<Address> addresses) {
        if (addresses == null) {
            return null;
        }
       return addresses.stream().map(AddressMapper::toResponseDTO).toList();
    }

}
