package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dao.repositories.DepartmentRepository;
import ru.models.Department;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findDepartmentById(Integer id) {
        return departmentRepository.findDepartmentById(id);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }
}
