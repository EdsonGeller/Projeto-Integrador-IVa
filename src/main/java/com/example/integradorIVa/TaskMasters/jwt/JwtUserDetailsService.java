package com.example.integradorIVa.TaskMasters.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.integradorIVa.TaskMasters.entity.User;
import com.example.integradorIVa.TaskMasters.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService{
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username){
        return JwtUtils.createToken(username);
    }

}
