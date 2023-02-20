package org.example.git.commit.id.controller;

import org.example.git.commit.id.controller.dto.response.GitCommitResponse;
import org.example.git.commit.id.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/version")
public class VersionController {

    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping
    public ResponseEntity<GitCommitResponse> version() {
        return ResponseEntity.ok().body(versionService.get());
    }
}
