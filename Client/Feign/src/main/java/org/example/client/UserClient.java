package org.example.client;


import org.example.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(value = "users", url = "${url.client-users}")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}", produces = "application/json")
    User getUserById(@PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.GET,  produces = "application/json")
    List<User> getUserByIdAndName(@RequestParam("userId") Long userId, @RequestParam("userName") String userName);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    User saveUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}", produces = "application/json")
    User putUser(@RequestBody User user, @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.PATCH, value = "/{userId}", produces = "application/json")
    User patchUser(@RequestBody User user, @PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}", produces = "application/json")
    User deleteUser(@PathVariable("userId") Long userId);
}
