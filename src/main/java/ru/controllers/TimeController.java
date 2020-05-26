package ru.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.*;
import ru.models.JearCalendar_Workers;
import ru.models.Worker;

import java.util.Set;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/changeTime")
public class TimeController {

    private static int depId = 1;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JearCalendar_WorkersService jearCalendar_workersService;

    @Autowired
    private MarkerService markerService;

    @GetMapping
    @Secured({"ROLE_time"})
    protected ModelAndView getMonth(@RequestParam(required = false, name = "month") String month,
                                    @RequestParam(required = false, name = "departmentId") Integer department) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("changeTime");
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
            worker.put("cal", jearCalendar_workersService.getJearCalendarsByWorker(w.getNumber(), month));
            worker.put("markers", markerService.findAll());
            workList.add(worker);
        }
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("workers", workList);
        return choseMonth(modelAndView, month);
    }

    @PostMapping
    protected void post(@RequestParam(required = false, name = "mId") Integer mId, @RequestParam(required = false, name = "cId") Integer cId) {
        JearCalendar_Workers jearCalendar_workers = jearCalendar_workersService.findById(cId);
        jearCalendar_workers.setMarker(markerService.findById(mId));
        jearCalendar_workersService.save(jearCalendar_workers);
        getMonth(null,null);
    }

    protected ModelAndView choseMonth(ModelAndView modelAndView, String month) {
        switch (month) {
            case "01":
                modelAndView.addObject("jan", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "02":
                modelAndView.addObject("feb", "checked");
                modelAndView.addObject("size", IntStream.range(1, 29).toArray());
                break;
            case "03":
                modelAndView.addObject("mar", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "04":
                modelAndView.addObject("apr", "checked");
                modelAndView.addObject("size", IntStream.range(1, 31).toArray());
                break;
            case "05":
                modelAndView.addObject("mai", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "06":
                modelAndView.addObject("iun", "checked");
                modelAndView.addObject("size", IntStream.range(1, 31).toArray());
                break;
            case "07":
                modelAndView.addObject("iul", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "08":
                modelAndView.addObject("avg", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "09":
                modelAndView.addObject("sen", "checked");
                modelAndView.addObject("size", IntStream.range(1, 31).toArray());
                break;
            case "10":
                modelAndView.addObject("okt", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
            case "11":
                modelAndView.addObject("noj", "checked");
                modelAndView.addObject("size", IntStream.range(1, 31).toArray());
                break;
            case "12":
                modelAndView.addObject("dec", "checked");
                modelAndView.addObject("size", IntStream.range(1, 32).toArray());
                break;
        }
        return modelAndView;
    }
}
