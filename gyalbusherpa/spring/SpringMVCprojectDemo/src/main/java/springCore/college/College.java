package springCore.college;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class College {

    @Value("${college.name}")
    private String collegeName;
    @Autowired
    private Principal principal;

    @Autowired
    @Qualifier("mathTeacher")
    private Teacher teacher;

    public void test() {
        System.out.println("I am don test");
        principal.principalInfo();
        teacher.teach();
        System.out.println("College name : " + collegeName);
    }
}
