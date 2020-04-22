package vovandev.exchangetrading.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vovandev.exchangetrading.entity.Candlestick;
import vovandev.exchangetrading.entity.User;
import vovandev.exchangetrading.repository.UserRepository;
import vovandev.exchangetrading.service.CommunicationService;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class Home {

    private UserRepository userRepository;
    private CommunicationService communicationService;

    public Home(UserRepository userRepository, CommunicationService communicationService) {
        this.userRepository = userRepository;
        this.communicationService = communicationService;
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
        List<Candlestick> ret;
        // no authorized gets the last results
        if (auth == null) {
            ret = communicationService.getLastInsertedCandles();
        // authorized gets all records
        } else {
            ret = communicationService.getAllCandles();
        }
        if (ret == null || ret.isEmpty()) {
            return List.of();
        }
        return ret;
    }
}
