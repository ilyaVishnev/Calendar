package ru.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {

    Role findByName(String name);

}
