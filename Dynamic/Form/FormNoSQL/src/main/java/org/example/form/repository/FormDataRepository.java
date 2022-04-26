package org.example.form.repository;

import org.bson.types.ObjectId;
import org.example.form.entity.FormData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository  extends MongoRepository<FormData, ObjectId> {
}
