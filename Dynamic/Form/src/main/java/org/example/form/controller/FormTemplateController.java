package org.example.form.controller;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.dto.response.FormTemplateResponse;
import org.example.form.exception.CustomException;
import org.example.form.service.FormTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/formTemplate")
public class FormTemplateController {

    private final FormTemplateService service;

    @PostMapping
    public ResponseEntity<FormTemplateResponse> create(@RequestBody FormTemplateRequest request) throws CustomException{
        return ResponseEntity.ok().body(service.create(request));
    }

    @GetMapping("/system")
    public ResponseEntity<FormTemplateResponse> getForm(
            @RequestParam String name, @RequestParam String system,
            @RequestParam Integer version) throws CustomException {
        return ResponseEntity.ok().body(service.getForm(name, system, version));
    }

    @GetMapping
    public ResponseEntity<List<FormTemplateResponse>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/model")
    public ResponseEntity<FormTemplateResponse> getModel() {
        return ResponseEntity.ok().body(service.getModel());
    }
}
