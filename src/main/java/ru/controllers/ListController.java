package ru.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.DepartmentService;
import ru.dao.services.JearCalendarService;
import ru.dao.services.MarkerService;
import ru.dao.services.WorkerService;
import ru.models.JearCalendar;
import ru.models.JearCalendar_Workers;
import ru.models.Worker;

import java.util.Set;
import java.util.stream.IntStream;

@Controller
public class ListController {

    private static int depId = 1;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JearCalendarService jearCalendarService;

    @Autowired
    private MarkerService markerService;

    @GetMapping(value = {"/", "/list"})
    protected ModelAndView getMonth(@RequestParam(required = false, name = "month") String month,
                                    @RequestParam(required = false, name = "departmentId") Integer department) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        month = month == null ? "01" : month;
        department = department == null ? depId : department;
        depId = department;
        Set<Worker> workers = workerService.findWorkersByMonth(month, department);
        JSONArray workList = new JSONArray();
        for (Worker w : workers) {
            JSONObject worker = new JSONObject();
            worker.put("name", w.getFirst_name() + ' ' + w.getMiddle_name() + ' ' + w.getSecond_name());
            worker.put("employment", w.getEmployment());
            worker.put("number", w.getNumber());
            worker.put("markers", markerService.getJearCalendarsByWorker(w.getNumber(), month));
            workList.add(worker);
        }
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("workers", workList);
        return choseMonth(modelAndView, month);
    }

    protected ModelAndView choseMonth(ModelAndView modelAndView, String month) {
        switch (month) {
            case "01":
                modelAndView.addObject("jan", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "02":
                modelAndView.addObject("feb", "checked");
                modelAndView.addObject("size", IntStream.range(1,29).toArray());
                break;
            case "03":
                modelAndView.addObject("mar", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "04":
                modelAndView.addObject("apr", "checked");
                modelAndView.addObject("size", IntStream.range(1,31).toArray());
                break;
            case "05":
                modelAndView.addObject("mai", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "06":
                modelAndView.addObject("iun", "checked");
                modelAndView.addObject("size", IntStream.range(1,31).toArray());
                break;
            case "07":
                modelAndView.addObject("iul", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "08":
                modelAndView.addObject("avg", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "09":
                modelAndView.addObject("sen", "checked");
                modelAndView.addObject("size", IntStream.range(1,31).toArray());
                break;
            case "10":
                modelAndView.addObject("okt", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
            case "11":
                modelAndView.addObject("noj", "checked");
                modelAndView.addObject("size", IntStream.range(1,31).toArray());
                break;
            case "12":
                modelAndView.addObject("dec", "checked");
                modelAndView.addObject("size", IntStream.range(1,32).toArray());
                break;
        }
        return modelAndView;
    }
}
