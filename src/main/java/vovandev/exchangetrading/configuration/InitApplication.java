package vovandev.exchangetrading.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.entity.User;
import vovandev.exchangetrading.repository.UserRepository;
import vovandev.exchangetrading.service.CommunicationService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Prepopulate db for testing purpose
 * Start WebSocketConnection connection
 */
@Service
public class InitApplication implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CommunicationService communicationService;

    public InitApplication(UserRepository userRepository, PasswordEncoder passwordEncoder, CommunicationService communicationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.communicationService = communicationService;
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {
        /* Populate DB*/
        List<User> users = List.of(
                new User("admin", passwordEncoder.encode("pass"), "ADMIN", "ALL"),
                new User("petruha", passwordEncoder.encode("baltika9"), "USER", "")
        );
        userRepository.saveAll(users);

        boolean reconnect = true;
        while (reconnect) {
            /* Start WebSocket connection */
            try {
                communicationService.establishConnection();
                reconnect = false;
            } catch (IllegalStateException e) {
                communicationService.establishConnection();
            }
        }
    }
}
