package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.DepartmentService;
import ru.models.Department;

@Controller
@RequestMapping("/createDep")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    @Secured({"ROLE_dep"})
    protected ModelAndView get() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createDep");
        modelAndView.addObject("departments", departmentService.findAll());
        return modelAndView;
    }

    @PostMapping
    protected void createDepartment(@ModelAttribute("dep") Department department) {
        Department newDepartment = new Department();
        newDepartment.setName(department.getName());
        departmentService.save(department.getId() == -1 ? newDepartment : department);
        get();
    }
}
