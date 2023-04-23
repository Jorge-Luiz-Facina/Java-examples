package org.example.log.json.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.log.json.dto.TestResponse;
import org.example.log.json.service.TestErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Log4j2
@RequiredArgsConstructor
public class TestController {

    private final TestErrorService testErrorService;

    @GetMapping
    public ResponseEntity<TestResponse> test() {
        log.info("Test");
        TestResponse test = new TestResponse();
        return ResponseEntity.ok(test);
    }

    @GetMapping("/error")
    public ResponseEntity<String> testError() throws Exception {
        testErrorService.testError();
        return ResponseEntity.ok("");
    }

}
