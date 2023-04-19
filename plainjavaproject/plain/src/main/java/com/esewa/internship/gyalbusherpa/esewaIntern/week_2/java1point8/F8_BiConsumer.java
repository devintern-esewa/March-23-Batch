package week_2.java1point8;

import java.util.ArrayList;
import java.util.function.BiConsumer;

class Emp4{
    String name;
    double salary;

    public Emp4(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
public class F8_BiConsumer {
    public static void main(String[] args) {
        ArrayList<Emp4> l = new ArrayList<>();
        populate(l);
        BiConsumer<Emp4,Double> c = (e,d)->e.salary=e.salary+d;
        for(Emp4 e: l){
            c.accept(e,5000.0);
        }
        for(Emp4 e:l){
            System.out.println("name"+e.name);
            System.out.println("salary"+e.salary);
            System.out.println();
        }
    }
    public static void populate(ArrayList<Emp4> l){
        l.add(new Emp4("Da",53));
        l.add(new Emp4("d",533));
        l.add(new Emp4("ga",513));
        l.add(new Emp4("hbna",553));
    }
}
