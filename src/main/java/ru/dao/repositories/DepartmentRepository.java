package ru.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.models.Department;
import ru.models.Worker;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {

     Department findDepartmentById(Integer id);
     List<Department>findAll();
}
