package com.Server.service.impl;

import com.Server.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Class Service implements interface UserDetailsService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    /**userServiceImpl*/
    private final UserServiceImpl userServiceImpl;

    @Autowired
    /**Constructor*/
    public UserDetailsServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @Override
    @Transactional
    /**Method to loadUsername*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImpl.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not exsit"));
        return UserDetailsimpl.build(user);
    }
}
