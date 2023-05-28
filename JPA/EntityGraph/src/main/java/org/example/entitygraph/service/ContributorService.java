package org.example.entitygraph.service;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entitygraph.controller.dto.response.ContributorResponseDTO;
import org.example.entitygraph.entity.Contributor;
import org.example.entitygraph.mapper.ContributorMapper;
import org.example.entitygraph.repository.ContributorRepository;
import org.springframework.stereotype.Service;

@Service
public class ContributorService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ContributorRepository contributorRepository;

    public ContributorService(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    public ContributorResponseDTO findById(Long id) {
        Contributor contributor = contributorRepository.findById(id).orElseThrow();
        return ContributorMapper.toResponseDTO(contributor);
    }

    public ContributorResponseDTO findByName(String name) {
        Contributor contributor = contributorRepository.findByName(name).orElseThrow();
        return ContributorMapper.toResponseDTO(contributor);
    }

    public ContributorResponseDTO findByIdUsingCriteria(Long id) {
        EntityGraph entityGraph = entityManager.getEntityGraph("contributor-all-entity-graph");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contributor> criteriaQuery = criteriaBuilder.createQuery(Contributor.class);
        Root<Contributor> root = criteriaQuery.from(Contributor.class);
        criteriaQuery.where(criteriaBuilder.equal(root.<Long>get("id"), id));
        TypedQuery<Contributor> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("jakarta.persistence.loadgraph", entityGraph);
        Contributor contributor = typedQuery.getSingleResult();
        return ContributorMapper.toResponseDTO(contributor);
    }

}
