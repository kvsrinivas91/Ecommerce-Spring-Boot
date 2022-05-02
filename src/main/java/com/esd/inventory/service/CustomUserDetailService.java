package com.esd.inventory.service;

import com.esd.inventory.DAO.UserDAO;
import com.esd.inventory.model.CustomUserDetail;
import com.esd.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= Optional.ofNullable(userDAO.findUserByEmail(email));
        user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return user.map(CustomUserDetail::new).get();
    }
}
