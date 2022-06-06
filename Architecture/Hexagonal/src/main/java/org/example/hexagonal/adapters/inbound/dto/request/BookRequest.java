package org.example.hexagonal.adapters.inbound.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BookRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate releaseDate;

}
