package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {

    private String name;
    private List<String> authors;
}
