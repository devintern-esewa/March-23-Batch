package controller;

import exception.SpringException;
import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    //student vanne ma form xa tao kholna empty student ko object pathahko
    //spring ley form ma kaam garda modelName ma object named "command" use gar vanxa so
    public ModelAndView student(){
        return new  ModelAndView("student","command",new Student());
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ExceptionHandler({SpringException.class})
    //hamiley aba form ma varyako data anusar naya page banaune ho jun result vanne ma dakhauxa
    //@ModelAttribute ley student object ma  aako paramter lai bind garxa
    //aako data chai ModelMap type ko  ho
    public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap modelMap){
        if(student.getName().length()<5){
            throw new SpringException("Given name is too short");
        }else
        modelMap.addAttribute("name",student.getName());

        if (student.getId()<1){
            throw new SpringException("Age should be greater than 1");
        }
        else
        modelMap.addAttribute("id",student.getId());
        return "result";
    }
}
