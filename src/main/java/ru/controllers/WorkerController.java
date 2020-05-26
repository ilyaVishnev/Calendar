package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.DepartmentService;
import ru.dao.services.WorkerService;
import ru.models.Department;
import ru.models.Worker;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/createWork")
public class WorkerController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    WorkerService workerService;

    @GetMapping
    @Secured({"ROLE_work"})
    protected ModelAndView get(@RequestParam(required = false, name = "w_id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createWork");
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("workers", workerService.findAll());
        modelAndView.addObject("selRum", true);
        if (id != null && id != -1) {
            Worker worker = workerService.findByNumber_w(id);
            modelAndView.addObject("first_name", worker.getFirst_name());
            modelAndView.addObject("second_name", worker.getSecond_name());
            modelAndView.addObject("middle_name", worker.getMiddle_name());
            modelAndView.addObject("birthdate", new Date(worker.getBirthdate().getTime()));
            modelAndView.addObject("employment", worker.getEmployment());
            modelAndView.addObject("selRum", worker.isRemoute_workspace());
            modelAndView.addObject("seldep", worker.getDepartment().getId());
            modelAndView.addObject("address", worker.getAddress());
            modelAndView.addObject("department", worker.getDepartment().getId());
            modelAndView.addObject("selworker", worker.getNumber());
        }
        return modelAndView;
    }

    @PostMapping
    protected void createWorker(@ModelAttribute("worker") Worker worker, @RequestParam(name = "dep_id") Integer id_dep, @RequestParam(required = false, name = "birth") String birth) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(!birth.equals("")) {
            try {
                Date date = formatter.parse(birth);
                worker.setBirthdate(new Timestamp(date.getTime()));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }else {
            worker.setBirthdate(workerService.findByNumber_w(worker.getNumber()).getBirthdate());
        }
        worker.setDepartment(departmentService.findDepartmentById(id_dep));
        workerService.save(worker.getNumber() == -1 ? full(worker, id_dep) : worker);
        get(null);
    }

    protected Worker full(Worker worker, Integer id_dep) {
        Worker newWorker = new Worker();
        newWorker.setAddress(worker.getAddress());
        newWorker.setBirthdate(worker.getBirthdate());
        newWorker.setSecond_name(worker.getSecond_name());
        newWorker.setDepartment(worker.getDepartment());
        newWorker.setMiddle_name(worker.getMiddle_name());
        newWorker.setFirst_name(worker.getFirst_name());
        newWorker.setEmployment(worker.getEmployment());
        newWorker.setRemoute_workspace(worker.isRemoute_workspace());
        return newWorker;
    }
}
