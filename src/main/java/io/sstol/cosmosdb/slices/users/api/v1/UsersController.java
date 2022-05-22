package io.sstol.cosmosdb.slices.users.api.v1;

import io.sstol.cosmosdb.slices.users.api.v1.dto.UserDto;
import io.sstol.cosmosdb.slices.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sergey Stol
 * 2022-05-21
 */
@RestController
@RequestMapping("/v1" + "/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService service;

    // GET /v1/users/:id
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable String id) {
        return service.getUser(id);
    }

    // GET /v1/users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    // GET /v1/users/by/firstname/:firstName
    @GetMapping(value = "/by/firstname/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(@PathVariable String firstName) {
        return service.getUserByFirstName(firstName);
    }

    // POST /v1/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserDto userDto) {
        return service.addUser(userDto);
    }

    // PUT /v1/users
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody UserDto userDto) {
        service.updateUser(userDto);
    }

    // DELETE /v1/users/:id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        service.deleteUser(id);
    }
}