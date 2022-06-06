package org.example.hexagonal.adapters.inbound.controller;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.adapters.inbound.mapper.BookMapper;
import org.example.hexagonal.adapters.inbound.dto.request.BookRequest;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.in.FindBooksInPort;
import org.example.hexagonal.application.ports.in.SaveBookInPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final SaveBookInPort saveBookInPort;
    private final FindBooksInPort findBooksInPort;

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody @Valid BookRequest request) {
        Book book = BookMapper.INSTANCE.bookRequestToBook(request);
        return new ResponseEntity<>(
                saveBookInPort.save(book),
                HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Book>> find(@PathVariable String name) {
        return new ResponseEntity<>(
                findBooksInPort.find(name),
                HttpStatus.OK);
    }

}