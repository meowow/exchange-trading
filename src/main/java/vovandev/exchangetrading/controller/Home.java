package vovandev.exchangetrading.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vovandev.exchangetrading.entity.Candlestick;
import vovandev.exchangetrading.entity.User;
import vovandev.exchangetrading.repository.CandlestickRepository;
import vovandev.exchangetrading.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Home {

    private UserRepository userRepository;
    private CandlestickRepository candlestickRepository;

    public Home(UserRepository userRepository, CandlestickRepository candlestickRepository) {
        this.userRepository = userRepository;
        this.candlestickRepository = candlestickRepository;
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

    @GetMapping("candles")
    public List<Candlestick> getCandlesticks(Authentication auth) {
        List<Candlestick> ret = new ArrayList<>();

        return ret;
    }
}
