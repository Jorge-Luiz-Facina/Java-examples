package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public static final UserResponseDTO USER = new UserResponseDTO(1l,"Teste",20,"email@email.com", "description");

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Arrays.asList(USER));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(USER);
    }

    @PutMapping("/userId")
    public ResponseEntity<UserResponseDTO> putUser(@PathVariable("userId") Long userId, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(USER);
    }

    @PatchMapping("/userId")
    public ResponseEntity<UserResponseDTO> patchUser(@PathVariable("userId") Long userId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(USER);
    }

    @DeleteMapping("/userId")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok("Deleted");
    }
}