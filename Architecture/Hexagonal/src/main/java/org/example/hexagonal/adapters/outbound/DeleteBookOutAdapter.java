package org.example.hexagonal.adapters.outbound;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.adapters.outbound.repository.BookRepository;
import org.example.hexagonal.application.ports.out.DeleteBookOutPort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteBookOutAdapter implements DeleteBookOutPort {

    private final BookRepository bookRepository;

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
