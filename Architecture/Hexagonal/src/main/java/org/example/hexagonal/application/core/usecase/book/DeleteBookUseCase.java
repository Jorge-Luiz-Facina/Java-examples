package org.example.hexagonal.application.core.usecase.book;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.application.ports.in.DeleteBookInPort;
import org.example.hexagonal.application.ports.out.DeleteBookOutPort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteBookUseCase implements DeleteBookInPort {

    private final DeleteBookOutPort deleteBookOutPort;

    @Override
    public void delete(Long id) {
        deleteBookOutPort.delete(id);
    }
}
