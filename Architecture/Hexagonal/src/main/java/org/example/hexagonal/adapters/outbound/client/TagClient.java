package org.example.hexagonal.adapters.outbound.client;

import org.example.hexagonal.application.core.domain.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "tagClient", url = "https://mock.com.br")
public interface TagClient {

    @GetMapping( "/find")
    ResponseEntity<List<Tag>> findByBookName(@PathVariable String bookName);
}
