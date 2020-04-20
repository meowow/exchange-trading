package vovandev.exchangetrading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vovandev.exchangetrading.entity.User;
import vovandev.exchangetrading.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Home {

    private UserRepository userRepository;

    public Home(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("users")
    public List<User> users() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            // communication exception
            return List.of();
        }
    }
}
