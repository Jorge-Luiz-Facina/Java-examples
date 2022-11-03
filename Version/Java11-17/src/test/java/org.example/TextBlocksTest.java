package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TextBlocksTest {

    @Test
    public void test() {
        String testJava17 =
                """
                        Test0
                        test1
                        test2
                        """;
        String testJava11 = "Test0\ntest1\ntest2\n";
        assertEquals(testJava11, testJava17);

    }

    @Test
    public void tabTest() {
        String testJava17 =
                """
                            Test0
                            test1
                            test2
                        """;
        String testJava11 = "    Test0\n    test1\n    test2\n";
        assertEquals(testJava11, testJava17);
    }
}
