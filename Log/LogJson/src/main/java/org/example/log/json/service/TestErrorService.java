package org.example.log.json.service;

import org.springframework.stereotype.Service;

@Service
public class TestErrorService {

    public void testError() throws Exception {
        throw new Exception("test");
    }
}
