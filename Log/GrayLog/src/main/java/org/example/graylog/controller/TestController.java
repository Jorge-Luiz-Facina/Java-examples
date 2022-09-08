package org.example.graylog.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.graylog.dto.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public ResponseEntity<TestDto> test() throws InterruptedException {
        log.info("Test");
        Thread.sleep(2000);
        TestDto test = new TestDto();

        return ResponseEntity.ok(test);
    }
}
