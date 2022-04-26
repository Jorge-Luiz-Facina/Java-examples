package org.example.form.repository;

import org.bson.types.ObjectId;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormTemplateRepository extends MongoRepository<FormTemplate, ObjectId> {

    Optional<FormTemplate> findByTypeAndSystemAndVersion(String type, SystemTypeEnum system, Integer version);

    Optional<FormTemplate> findByTypeAndSystem(String type, SystemTypeEnum system);

    Optional<FormTemplate> findTopByTypeAndSystemOrderByVersionDesc(String type, SystemTypeEnum system);

}
