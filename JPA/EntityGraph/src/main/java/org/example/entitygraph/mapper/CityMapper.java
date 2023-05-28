package org.example.entitygraph.mapper;

import org.example.entitygraph.controller.dto.response.CityResponseDTO;
import org.example.entitygraph.entity.City;

public class CityMapper {

    public static CityResponseDTO toResponseDTO(City city) {
        if (city == null) {
            return null;
        }
        CityResponseDTO cityResponseDTO = new CityResponseDTO();
        cityResponseDTO.setName(city.getName());
        return cityResponseDTO;
    }
}
