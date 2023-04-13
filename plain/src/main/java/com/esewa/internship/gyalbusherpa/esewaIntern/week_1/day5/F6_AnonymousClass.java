package week_1.day5;

class Don{
    public void detail(){
        System.out.println("I am don");
    }
}
public class F6_AnonymousClass {

    void method(){
        Don d = new Don(){
            public void detail(){
                System.out.println("I am also don");
            }
        };
        d.detail();
    }
    public static void main(String[] args) {
        F6_AnonymousClass a = new F6_AnonymousClass();
        a.method();
    }
}
