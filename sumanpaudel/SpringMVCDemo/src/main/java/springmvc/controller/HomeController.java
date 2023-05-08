package springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    // using path variable
    @RequestMapping(value = "/user/{id}")
    public String getUserDetails(@PathVariable("id") int id) {
        System.out.println(id);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("name", "suman");
        String s=null;
        System.out.println(s.length());
        return "about";
    }

    @RequestMapping(value = "/help")
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hello", "suman");
        LocalDateTime localDateTime = LocalDateTime.now();
        modelAndView.addObject("date", localDateTime);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(34);
        numbers.add(21);
        numbers.add(41);
        numbers.add(51);
        numbers.add(91);
        modelAndView.addObject("numbers", numbers);
        return modelAndView;
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class})
    public String exceptionHandler(Model model){
        model.addAttribute("Exception","Exception Occurred");
        return "exception";
    }
}
