package org.example.repository;

import org.example.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql({"/db/changelog/DML/001_insert_data.sql"})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllTest(){
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void findByIdTest(){
        assertNotNull(userRepository.findById(1));
    }

    @Test
    public void findByAgeAndNameTest(){
        assertNotNull(userRepository.findByAgeAndName(1, "Jorge"));
    }

    @Test
    public void findByAgeOrNameTest(){
        assertNotNull(userRepository.findByAgeOrName(-1, "Jorge"));
    }
}
