package org.example.entitygraph.mapper;

import org.example.entitygraph.controller.dto.response.JobResponseDTO;
import org.example.entitygraph.entity.Job;

public class JobMapper {

    public static JobResponseDTO toResponseDTO(Job job) {
        if (job == null) {
            return null;
        }
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setName(job.getName());
        return jobResponseDTO;
    }
}
