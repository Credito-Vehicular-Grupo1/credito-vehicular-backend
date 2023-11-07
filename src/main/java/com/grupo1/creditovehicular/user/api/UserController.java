package com.grupo1.creditovehicular.user.api;

import com.grupo1.creditovehicular.user.domain.service.UserService;
import com.grupo1.creditovehicular.user.mapping.UserMapper;
import com.grupo1.creditovehicular.user.resource.CreateUserResource;
import com.grupo1.creditovehicular.user.resource.UpdateUserResource;
import com.grupo1.creditovehicular.user.resource.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Users", description = "Create, read, update and delete users")
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }

    @GetMapping("email&password/{userEmail}/{userPassword}")
    public UserResource getInfoUserByEmailAndPassword(@PathVariable("userEmail") String userEmail, @PathVariable("userPassword") String userPassword) {
        return mapper.toResource(userService.getByEmailAndPassword(userEmail, userPassword));
    }

    @GetMapping("{userId}")
    public UserResource getInfoUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getInfoUserById(userId));
    }

    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }

    @PutMapping("/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
