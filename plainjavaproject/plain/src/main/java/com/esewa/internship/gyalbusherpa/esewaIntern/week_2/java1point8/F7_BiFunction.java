package week_2.java1point8;

import java.util.ArrayList;
import java.util.function.BiFunction;

class Emp3{
    int eno;
    String name;

    public Emp3(int eno, String name) {
        this.eno = eno;
        this.name = name;
    }
}
public class F7_BiFunction {
    public static void main(String[] args) {
        ArrayList<Emp3> l = new ArrayList<>();
        BiFunction<Integer,String,Emp3> f = (eno,name)->new Emp3(eno,name);
//        Emp3 e1 = f.apply(100,"don");
        l.add(f.apply(100,"fhe"));
        l.add(f.apply(200,"aab"));
        l.add(f.apply(300,"asg"));
        l.add(f.apply(400,"ewr"));

        for (Emp3 a: l) {
            System.out.println("name"+ a.name);
            System.out.println("id"+ a.eno);
        }
    }
}
