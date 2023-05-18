package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.model.User;

@Controller
public class FormController {
    @RequestMapping(value = "/contact")
    public String showForm(){
        return "contact";
    }
    // Using Request Param
    /*@RequestMapping(path="/process-form",method = RequestMethod.POST)
    public String processForm(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam(value="checkbox", required = false) Boolean check,
                              Model model){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setCheck(check);
        System.out.println(user);
        //process
        model.addAttribute("user",user);
        /*model.addAttribute("email",email);
        model.addAttribute("password",password);
        model.addAttribute("check",check);
        return "process-form";
}*/
    @ModelAttribute
    public void addCommonDataToModel(Model model){
        model.addAttribute("hello","hello");
    }
    @RequestMapping(path="/process-form",method = RequestMethod.POST)
    public String processForm(@ModelAttribute  User user,Model model){
        return "process-form";
    }
}
