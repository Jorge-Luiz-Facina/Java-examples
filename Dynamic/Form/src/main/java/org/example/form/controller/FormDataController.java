package org.example.form.controller;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FormDataRequest;
import org.example.form.dto.response.FormDataResponse;
import org.example.form.exception.CustomException;
import org.example.form.service.FormDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/formData")
public class FormDataController {

    private final FormDataService  service;

    @PostMapping
    public ResponseEntity<FormDataResponse> create(@RequestBody FormDataRequest formDataRequest) {
        return ResponseEntity.ok(service.create(formDataRequest));
    }

    @GetMapping
    public ResponseEntity<List<FormDataResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDataResponse> getById(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/model")
    public ResponseEntity<FormDataResponse> getModel() {
        return ResponseEntity.ok().body(service.getModel());
    }
}