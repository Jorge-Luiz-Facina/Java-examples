package org.example.statistic.controller;


import org.example.statistic.controller.dto.GeneralResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping(value = "/general")
public class GeneralController {

    @GetMapping("/one")
    public ResponseEntity<GeneralResponseDTO> getOne() {
        return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseDTO());
    }

    @GetMapping("/two")
    public ResponseEntity<GeneralResponseDTO> getTwo() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(30).toMillis());
        return ResponseEntity.status(HttpStatus.OK).body(new GeneralResponseDTO());
    }
}
