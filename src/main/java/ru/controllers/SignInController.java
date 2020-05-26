package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dao.services.WorkerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*import org.springframework.web.bind.annotation.GetMapping;*/

@Controller
@RequestMapping(value = "/sign")
public class SignInController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView goToAccount(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
            model.setViewName("signIn");
        } else if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
            model.setViewName("list");
        } else {
            model.setViewName("signIn");
        }
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }
}
