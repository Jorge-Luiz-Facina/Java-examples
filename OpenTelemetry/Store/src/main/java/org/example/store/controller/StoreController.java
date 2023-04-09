package org.example.store.controller;

import org.example.store.controller.dto.request.StoreRequestDTO;
import org.example.store.controller.dto.response.StoreResponseDTO;
import org.example.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreResponseDTO>> findAll() throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StoreResponseDTO> create(@RequestBody StoreRequestDTO storeRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.create(storeRequestDTO));
    }

}
