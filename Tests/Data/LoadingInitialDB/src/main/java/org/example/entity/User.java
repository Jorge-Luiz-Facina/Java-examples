package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer age;

    @OneToMany(mappedBy="user")
    private List<Address> adresses;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_plan",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "plan_id") }
    )
    private List<Plan> plans;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    private Group group;
}
