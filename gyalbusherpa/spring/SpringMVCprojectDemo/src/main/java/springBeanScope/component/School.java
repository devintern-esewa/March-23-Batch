package springBeanScope.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class School {
    @Autowired
    private Student student;

    @Lookup
    Student createStudentObject(){
       return null;
    }
    public Student getStudent() {
        Student student1 = createStudentObject();
        return student1;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School() {
        System.out.println("School obj created..");
    }
}
