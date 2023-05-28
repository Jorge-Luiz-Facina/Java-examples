package org.example.entitygraph.controller;

import org.example.entitygraph.controller.dto.response.ContributorResponseDTO;
import org.example.entitygraph.service.ContributorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contributors")
public class ContributorController {

    private final ContributorService contributorService;

    public ContributorController(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContributorResponseDTO> findById(@PathVariable("id") Long id) {
        ContributorResponseDTO contributorResponseDTO = contributorService.findById(id);
        return ResponseEntity.ok().body(contributorResponseDTO);
    }

    @GetMapping("/criteria/{id}")
    public ResponseEntity<ContributorResponseDTO> findByIdUsingCriteria(@PathVariable("id") Long id) {
        ContributorResponseDTO contributorResponseDTO = contributorService.findByIdUsingCriteria(id);
        return ResponseEntity.ok().body(contributorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<ContributorResponseDTO> findByName(@RequestParam(value = "name") String name) {
        ContributorResponseDTO contributorResponseDTO = contributorService.findByName(name);
        return ResponseEntity.ok().body(contributorResponseDTO);
    }

}
