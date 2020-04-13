package vovandev.lohotronexchange.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vovandev.lohotronexchange.entity.User;
import vovandev.lohotronexchange.model.UserPrincipal;
import vovandev.lohotronexchange.repository.UserRepository;

@Service
public class UserDetailsPrincipalService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        return new UserPrincipal(user);
    }
}
