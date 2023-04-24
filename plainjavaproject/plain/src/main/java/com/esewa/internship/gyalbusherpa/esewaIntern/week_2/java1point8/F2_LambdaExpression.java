package week_2.java1point8;
@FunctionalInterface
interface Don{
//    public void don();
    public void don(int a, int b);

    // use as many static and default as you like, but only one abstract method is allowed.
//    public static void hero1(){
//        System.out.println("don");
//    }
//    default void don(){
//        System.out.println("don12");
//    }
}

public class F2_LambdaExpression {
    // Lambda Expression is anonymous function.
    // lambda functions can only be used with functional interfaces. nowhere else.

    public static void main(String[] args) {
        /*
        ()-> System.out.println("don"); // void type
        (a,b)-> System.out.println(a+b); // void type // {} are optional if only one sout.
         n-> n*n; // If only one parameter then () is optional // this is int return type
        n->{return n*n;}; // if you want to use return then {} is compulsory
         */

        // Functional interface implementation using lambda

//        Don d = ()-> System.out.println("hero");
//        d.don();

        Don d = (a,b)-> System.out.println(a+b);
        d.don(3,6);
    }
}
