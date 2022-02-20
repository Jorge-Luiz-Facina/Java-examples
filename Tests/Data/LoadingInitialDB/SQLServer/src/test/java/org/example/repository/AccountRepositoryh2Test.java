package org.example.repository;

import org.example.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@Sql({"/db/changelog/DML/001_insert_data.sql"})
@ActiveProfiles("h2")
public class AccountRepositoryh2Test {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findAllTest(){
        assertEquals(1, accountRepository.findAll().size());
    }

}
