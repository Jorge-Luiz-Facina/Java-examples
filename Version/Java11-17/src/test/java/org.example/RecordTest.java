package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecordTest {

    @Test
    public void test() {
        User user = new User();
        System.out.println(user);
        System.out.println(user.getText());
    }

    record User(String name, Integer age, String profession, RecordOne recordOne, RecordTwo recordTwo) implements RecordThree {
        public User {
            profession = "test";
        }

        public String nameInUpperCase(){
            return name.toUpperCase();
        }

        public User() {
            this(null, null, null, new RecordOne(), null);
        }
//        public User(UserDto userdto) {
//        }
    }

    record RecordOne(String name) {
        public RecordOne() {
            this(null);
        }
    }

    final class RecordTwo {
        public RecordTwo() {

        }
    }

    interface RecordThree {
        default String getText(){
            return "RecordThree";
        }
    }

}
