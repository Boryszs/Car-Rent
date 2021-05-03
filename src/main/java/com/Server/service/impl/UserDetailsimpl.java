package com.Server.service.impl;

import com.Server.entiy.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Service implements interface UserDetails.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

public class UserDetailsimpl implements UserDetails {
    /**serialVersionUID*/
    private static final long serialVersionUID = 1L;
    /**id*/
    private Long id;
    /**username*/
    private String username;
    /**email*/
    private String email;

    @JsonIgnore
    /**password*/
    private String password;
    /**authorities*/
    public Collection<? extends GrantedAuthority> authorities;

    /**Constructor*/
    public UserDetailsimpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**Builder*/
    public static UserDetailsimpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsimpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    /**
     *
     * @return Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     *
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return Username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return isAccountNonExpired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *
     * @return isAccountNonLocked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *
     * @return isCredentialsNonExpired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *
     * @return isEnabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
