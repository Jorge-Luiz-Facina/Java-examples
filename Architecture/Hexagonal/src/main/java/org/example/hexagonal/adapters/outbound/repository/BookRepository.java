package org.example.hexagonal.adapters.outbound.repository;

import org.example.hexagonal.adapters.inbound.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByName(String name);
}
