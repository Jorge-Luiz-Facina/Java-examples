package org.example.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequestDTO {
    @NotNull
    private String name;
    @NotNull
    private int age;
    @Email
    private String email;
    @Size(max = 100, message = "The description field can only have a maximum of 100 characters")
    private String description;
}
