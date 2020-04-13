package vovandev.lohotronexchange.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vovandev.lohotronexchange.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<SimpleGrantedAuthority> permissions = user.getPermissionList()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p));

        Stream<SimpleGrantedAuthority> roles = user.getRoleList()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r));

        return Stream.concat(permissions, roles).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive() == 1;
    }
}
