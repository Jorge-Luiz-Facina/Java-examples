package org.example;

import org.example.reflectiontestutils.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
public class ReflectionTestUtilsTest {

    @Test
    public void privateFieldSetNameTest() {
        String name = "Jorge";
        User user = new User();
        ReflectionTestUtils.setField(user, "name", name);
        assertEquals(name,user.getName());
    }
}
