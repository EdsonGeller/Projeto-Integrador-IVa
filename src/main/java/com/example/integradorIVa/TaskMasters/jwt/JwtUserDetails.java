package com.example.integradorIVa.TaskMasters.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private com.example.integradorIVa.TaskMasters.entity.User  user;

    public JwtUserDetails(com.example.integradorIVa.TaskMasters.entity.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUsername()));
        this.user = user;
    }

    public Long getId(){
        return this.user.getId();
    }
}
