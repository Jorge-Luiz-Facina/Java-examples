package org.example.form.repository;

import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormTemplateRepository extends JpaRepository<FormTemplate, Long> {

    FormTemplate findByNameAndSystemAndVersion(String name, String system, Integer version);

    FormTemplate findByNameAndSystem(String name, SystemTypeEnum system);

    @Query("select max(version) from FormTemplate ft where ft.name = :name and ft.system = system")
    FormTemplate findByNameAndSystemAndMaxVersion(String name, String system);

}
