package org.example.entitygraph.controller.dto.response;

import java.util.List;

public class ContributorResponseDTO {

    private Long id;
    private String name;
    private JobResponseDTO job;
    private List<AddressResponseDTO> Addresses;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobResponseDTO getJob() {
        return job;
    }

    public void setJob(JobResponseDTO job) {
        this.job = job;
    }

    public List<AddressResponseDTO> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<AddressResponseDTO> addresses) {
        Addresses = addresses;
    }
}
