package org.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address", schema = "public")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private String street;

    private String district;

    private String number;
}
