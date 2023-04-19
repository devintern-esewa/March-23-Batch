package week_2.day8.assignments;

public class Employee {
    String name;
    int id;
    int age;

    public Employee(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;

    }

    public String getName() {
        return name;
    }


    public String toString(){
        return id+" "+age;
    }
}
