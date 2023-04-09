package org.example.store.mapper;

import org.example.store.controller.dto.request.StoreRequestDTO;
import org.example.store.controller.dto.response.StoreResponseDTO;
import org.example.store.entity.Store;

public class StoreMapper {

    public static void storeRequestDTOToStore(Store store, StoreRequestDTO storeRequestDTO) {
        store.setName(storeRequestDTO.getName());
    }

    public static StoreResponseDTO storeToStoreResponse(Store store) {
        StoreResponseDTO storeResponseDTO = new StoreResponseDTO();
        storeResponseDTO.setId(store.getId());
        storeResponseDTO.setNome(store.getName());
        return storeResponseDTO;
    }

}
