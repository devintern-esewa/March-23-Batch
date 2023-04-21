package springCore.frompropertyfile;

import org.springframework.beans.factory.annotation.Value;

public class Student {
    @Value("${student.name}")
    private String name;

    @Value("java")
//    @Required
    private String interestedCourse;
    @Value("gaming")
    private String hobby;

//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setInterestedCourse(String interestedCourse) {
//        this.interestedCourse = interestedCourse;
//    }
//
//
//    public void setHobby(String hobby) {
//        this.hobby = hobby;
//    }

    public void displayStudentInfo(){
        System.out.println("Student name " + name);
        System.out.println("Student interested Course " + interestedCourse);
        System.out.println("Student hobby " + hobby);
    }
}
