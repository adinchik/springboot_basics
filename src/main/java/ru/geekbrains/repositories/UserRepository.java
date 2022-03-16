package ru.geekbrains.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUserName(String userName);
}
