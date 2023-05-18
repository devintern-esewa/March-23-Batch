package com.springboott.SBProject.student;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/student")

public class StudentController {
    private final StudentService studentService; //reference to StudentService

    @Autowired
    public StudentController(StudentService studentService) {  //path the studenservice inside the controller
        //      this.studentService=studentService; //it wont work as we dont have instance of studentservice
        this.studentService = studentService;

    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);

    }


    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    {
        studentService.updateStudent(studentId, name, email);
    }
}
