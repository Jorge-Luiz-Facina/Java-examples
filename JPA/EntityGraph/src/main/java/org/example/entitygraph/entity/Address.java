package org.example.entitygraph.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Address", catalog = "Data", schema = "report")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_fk")
    private City city;

    @ManyToOne
    @JoinColumn(name = "contributor_fk")
    private Contributor contributor;

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }
}
