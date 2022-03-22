package ru.geekbrains.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.geekbrains.entities.Role;

public interface RolesRepository extends CrudRepository<Role, Integer> {
}
