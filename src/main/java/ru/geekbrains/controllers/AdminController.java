package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.services.RolesService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private RolesService rolesService;

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("")
    public String adminHome(Model model) {
        model.addAttribute("roles", rolesService.getAllRoles());
        System.out.println("!!!!!!!!");
        return "admin-panel";
    }
}
