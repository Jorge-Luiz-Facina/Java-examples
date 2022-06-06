package org.example.hexagonal.adapters.inbound.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tag", schema = "public")
@Data
public class TagEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<BookEntity> books;
}