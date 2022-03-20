package com.example.controller;

import com.example.client.soap.gen.BLZService;
import com.example.client.soap.gen.BLZServicePortType;
import com.example.client.soap.gen.DetailsType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BLZController {

    @GetMapping("/{blz}")
    public ResponseEntity<DetailsType> getBank(
            @PathVariable("blz") String blz
    ) {
        BLZServicePortType blzService = new BLZService().getBLZServiceSOAP12PortHttp();
        return ResponseEntity.ok(blzService.getBank(blz));
    }
}
