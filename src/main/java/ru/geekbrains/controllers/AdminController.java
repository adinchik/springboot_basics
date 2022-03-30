package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.entities.User;
import ru.geekbrains.services.RolesService;
import ru.geekbrains.services.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private RolesService rolesService;
    private UserService userService;

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String adminHome(Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName());
        String email = "";
        if (user != null)
            email = user.getEmail();
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("email", email);

        return "admin-panel";
    }
}
