package com.external.controller;

import com.external.controller.dto.InfoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infos")
public class InfoController {

    @GetMapping("/{login}")
    public ResponseEntity<InfoResponseDTO> getAll(
            @PathVariable("login") String login) {

        InfoResponseDTO infoResponseDTO = new InfoResponseDTO();
        infoResponseDTO.setLogin(login);
        infoResponseDTO.setDetail("detail");
        return new ResponseEntity<>(infoResponseDTO, HttpStatus.OK);
    }
}
