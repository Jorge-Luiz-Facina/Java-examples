package org.example.entitygraph.mapper;

import org.example.entitygraph.controller.dto.response.ContributorResponseDTO;
import org.example.entitygraph.entity.Contributor;

public class ContributorMapper {

    public static ContributorResponseDTO toResponseDTO(Contributor contributor) {
        if (contributor == null) {
            return null;
        }
        ContributorResponseDTO contributorResponseDTO = new ContributorResponseDTO();
        contributorResponseDTO.setId(contributor.getId());
        contributorResponseDTO.setName(contributor.getName());
        contributorResponseDTO.setJob(JobMapper.toResponseDTO(contributor.getJob()));
        contributorResponseDTO.setAddresses(AddressMapper.toListResponseDTO(contributor.getAddresses()));
        return contributorResponseDTO;
    }

}
