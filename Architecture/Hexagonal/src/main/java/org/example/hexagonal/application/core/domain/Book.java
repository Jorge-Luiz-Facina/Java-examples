package org.example.hexagonal.application.core.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Book {

    private Long id;

    private String name;

    private LocalDate releaseDate;

    private List<Tag> tags;
}
