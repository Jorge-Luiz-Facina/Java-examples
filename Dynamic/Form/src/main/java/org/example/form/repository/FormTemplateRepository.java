package org.example.form.repository;

import org.example.form.entity.FormTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    FormTemplate findByNameAndSystemAndVersion(String name, String system, Integer version);

    FormTemplate findByNameAndSystem(String name, String system);

}
