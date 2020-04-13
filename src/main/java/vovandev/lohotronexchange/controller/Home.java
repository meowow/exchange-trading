package vovandev.lohotronexchange.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vovandev.lohotronexchange.entity.User;
import vovandev.lohotronexchange.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Home {

    private UserRepository userRepository;

    public Home(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("hello")
    public String home() {
        return"Hello worlds";
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
