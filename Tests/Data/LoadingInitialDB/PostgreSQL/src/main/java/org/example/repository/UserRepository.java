package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    User findById(int id);

    List<User> findByAgeAndName(int age, String name);

    List<User> findByAgeOrName(int age, String name);

}
