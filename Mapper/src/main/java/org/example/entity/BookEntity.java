package org.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class BookEntity {

    private String name;
    private List<AuthorEntity> authors;
}
