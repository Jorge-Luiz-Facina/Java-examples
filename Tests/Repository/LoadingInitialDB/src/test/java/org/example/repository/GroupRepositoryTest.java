package org.example.repository;

import org.example.Application;
import org.example.entity.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql({"/db/changelog/DML/001_insert_data.sql"})
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void findAllTest(){
        assertEquals(1, groupRepository.findAll().size());
    }
}