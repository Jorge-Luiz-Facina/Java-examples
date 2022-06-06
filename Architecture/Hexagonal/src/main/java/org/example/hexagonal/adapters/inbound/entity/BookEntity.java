package org.example.hexagonal.adapters.inbound.entity;

import lombok.Data;
import org.example.hexagonal.application.core.domain.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book", schema = "public")
@Data
public class BookEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Integer id;

    private String name;

    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<TagEntity> tags;

}