package org.example.form.repository;

import org.example.form.entity.FormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository  extends JpaRepository<FormData, Long> {
}
