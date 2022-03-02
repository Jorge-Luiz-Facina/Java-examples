package org.example.run;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.client.UserClient;
import org.example.entity.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class UserRun implements ApplicationRunner {
    private final UserClient userClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Long userId = 1L;
        User user = User.builder().
                id(3L).age(20).
                name("Test").
                build();

        List<User> userList = userClient.getUsers();
        log.info("Get users");
        log.info(userList.toString());

        User userById = userClient.getUserById(userId);
        log.info("Get user by id");
        log.info(userById.toString());

        List<User> userByIdAnName = userClient.getUserByIdAndName(userId, "Test");
        log.info("Get user by id and name");
        log.info(userByIdAnName.toString());

        User userSave = userClient.saveUser(user);
        log.info("Save user");
        log.info(userSave.toString());

        User userPut = userClient.putUser(user, userId);
        log.info("Put user");
        log.info(userPut.toString());

        User userPatch = userClient.patchUser(user, userId);
        log.info("Patch user");
        log.info(userPatch.toString());

        User userDelete = userClient.deleteUser(userId);
        log.info("Delete user");
        log.info(userDelete.toString());

    }
}
