package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NullPointerTest {

    @Test
    public void test() {
        User user = new User();
        String name = user.company.name;
    }

    class User {
        public Company company;
    }

    class Company {
        public String name;
    }
}


