package org.example.hexagonal.application.ports.out;

import org.example.hexagonal.application.core.domain.Tag;

import java.util.List;

public interface FindTagOutPort {

    List<Tag> find(String bookName);
}
