package org.example.form.repository;

import org.example.form.entity.FieldTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTemplateRepository  extends JpaRepository<FieldTemplate, Long> {
}
