package org.example.form.controller;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FieldTemplateRequest;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.dto.response.FormTemplateResponse;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.service.FormTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form-template")
public class FormTemplateController {

    private final FormTemplateService service;

    @PostMapping
    public ResponseEntity<FormTemplateResponse> create(@RequestBody FormTemplateRequest request) throws CustomException {
        return ResponseEntity.ok().body(service.create(request));
    }

    @PutMapping("/{formTemplateId}")
    public ResponseEntity<FormTemplateResponse> update(@PathVariable String formTemplateId, @RequestBody FormTemplateRequest request) throws CustomException {
        return ResponseEntity.ok().body(service.update(formTemplateId, request));
    }

    @PostMapping("/{formTemplateId}/field-template")
    public ResponseEntity<FormTemplateResponse> createFieldTemplate(@PathVariable String formTemplateId, @RequestBody FieldTemplateRequest request) throws CustomException {
        return ResponseEntity.ok().body(service.createFieldTemplate(formTemplateId, request));
    }

    @PutMapping("/{formTemplateId}/field-template/{fieldTemplateId}")
    public ResponseEntity<FormTemplateResponse> updateFieldTemplate(@PathVariable String formTemplateId,
                                                       @PathVariable String fieldTemplateId,
                                                       @RequestBody FieldTemplateRequest request) throws CustomException {
        return ResponseEntity.ok().body(service.updateFieldTemplate(formTemplateId, fieldTemplateId, request));
    }

    @DeleteMapping("/{formTemplateId}/field-template/{fieldTemplateId}")
    public ResponseEntity<FormTemplateResponse> deleteFieldTemplate(@PathVariable String formTemplateId,
                                                                    @PathVariable String fieldTemplateId) throws CustomException {
        return ResponseEntity.ok().body(service.deleteFieldTemplate(formTemplateId, fieldTemplateId));
    }

    @GetMapping("/system")
    public ResponseEntity<FormTemplateResponse> getForm(
            @RequestParam String type, @RequestParam SystemTypeEnum system,
            @RequestParam Integer version) throws CustomException {
        return ResponseEntity.ok().body(service.getForm(type, system, version));
    }

    @GetMapping("/model")
    public ResponseEntity<FormTemplateResponse> getModel() {
        return ResponseEntity.ok().body(service.getModel());
    }
}
