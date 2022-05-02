package com.esd.inventory.controller;

import com.esd.inventory.DAO.RoleDAO;
import com.esd.inventory.DAO.UserDAO;
import com.esd.inventory.global.GlobalData;
import com.esd.inventory.model.Role;
import com.esd.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginContoller {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(){
        System.out.println("error");
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException{
        String password = user.getPassword();
        user.setPassword((bCryptPasswordEncoder.encode(password)));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.findById(2));
        user.setRoles(roles);
        userDAO.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/";
     }

}
