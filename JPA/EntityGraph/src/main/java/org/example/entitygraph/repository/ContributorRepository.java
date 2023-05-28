package org.example.entitygraph.repository;

import org.example.entitygraph.entity.Contributor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {

    @EntityGraph(attributePaths = {"job"})
    Optional<Contributor> findByName(String name);

    @EntityGraph(value = "contributor-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Contributor> findById(Long id);
}
