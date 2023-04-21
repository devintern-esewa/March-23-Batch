package springAOP;

import org.springframework.stereotype.Component;

// business class
@Component
public class EmpDao {
    public void saveEmp(){
        System.out.println("From save emp");
    }
}
