package org.example.entitygraph.entity;

import jakarta.persistence.*;

import java.util.List;

@NamedEntityGraph(
        name = "contributor-all-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "addresses", subgraph = "city-subgrap"),
                @NamedAttributeNode("job"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "city-subgrap",
                        attributeNodes = {
                                @NamedAttributeNode("city")
                        }
                )
        }
)

@NamedEntityGraph(
        name = "contributor-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("addresses"),
                @NamedAttributeNode("job"),
        }
)
@Entity
@Table(name = "Contributor", catalog = "Data", schema = "report")
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "contributor")
    private List<Address> addresses;

    @OneToOne(mappedBy = "contributor", fetch = FetchType.LAZY)
    private Job job;

//    @OneToOne(mappedBy = "contributor", fetch = FetchType.LAZY)
//    private Other Other;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
