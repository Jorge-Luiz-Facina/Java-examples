package org.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account", schema = "public")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String number;

}
