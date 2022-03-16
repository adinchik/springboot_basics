package ru.geekbrains.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}

