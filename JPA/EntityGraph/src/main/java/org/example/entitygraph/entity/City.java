package org.example.entitygraph.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "City", catalog = "Data", schema = "report")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
