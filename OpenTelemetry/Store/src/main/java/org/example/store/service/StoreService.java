package org.example.store.service;

import org.example.store.controller.dto.request.StoreRequestDTO;
import org.example.store.controller.dto.response.StoreResponseDTO;
import org.example.store.entity.Store;
import org.example.store.exceptions.NotFoundException;
import org.example.store.mapper.StoreMapper;
import org.example.store.repository.StoreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    public List<StoreResponseDTO> findAll() throws InterruptedException {
        Thread.sleep(5000);
        return storeRepository.findAll().stream().map(StoreMapper::storeToStoreResponse).toList();
    }

    public StoreResponseDTO create(StoreRequestDTO storeRequestDTO) {
        Store store = new Store();
        StoreMapper.storeRequestDTOToStore(store, storeRequestDTO);
        store = storeRepository.save(store);
        return StoreMapper.storeToStoreResponse(store);
    }

    public StoreResponseDTO findById(Long id) {
        Store store = storeRepository.findById(id).orElseThrow(()-> new NotFoundException());
        return StoreMapper.storeToStoreResponse(store);
    }
}
