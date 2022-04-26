package org.example.form.controller;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FormDataRequest;
import org.example.form.dto.response.FormDataResponse;
import org.example.form.exception.CustomException;
import org.example.form.service.FormDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form-data")
public class FormDataController {

    private final FormDataService  service;

    @PostMapping("/{formTemplateId}")
    public ResponseEntity<FormDataResponse> create(@PathVariable String formTemplateId, @RequestBody FormDataRequest formDataRequest) throws CustomException {
        return ResponseEntity.ok(service.create(formTemplateId, formDataRequest));
    }

    @PutMapping("/{formDataId}")
    public ResponseEntity<FormDataResponse> update(@PathVariable String formDataId, @RequestBody FormDataRequest formDataRequest) throws CustomException {
        return ResponseEntity.ok(service.update(formDataId, formDataRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDataResponse> getById(@PathVariable String id) throws CustomException {
        return ResponseEntity.ok(service.getByIdResponse(id));
    }

    @GetMapping("/model")
    public ResponseEntity<FormDataResponse> getModel() {
        return ResponseEntity.ok().body(service.getModel());
    }
}