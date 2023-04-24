package week_2.java1point8;

interface Interf{
    void add(int a, int b);
}
class Sample{
    Sample(){
        System.out.println("Sample class constructor");
    }
}
interface Two{
    public Sample get();
}
public class F11_MethodAndConstructorReference {

    public static void run() {
        for (int i = 0; i < 10; i++) {
//            System.out.println("child");
        }
    }

    public static void sum(int a, int b){
        System.out.println("The sum is "+(a+b));
    }

    public static void main(String[] args) {

        // method reference concept //
        // classname::method name -> for static
        // object reference:: method name
        // in method reference, argument type should match.
        // no restriction for return type

        Runnable r = F11_MethodAndConstructorReference::run;
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 10; i++) {
//            System.out.println("main");
        }

        Interf i = (a, b) -> System.out.println("The sum is " + (a + b));
//        i.add(1, 35);

        Interf i2 = F11_MethodAndConstructorReference::sum;
//        i2.add(23,53);



        /*
            Test::new ==> constructor reference
            classname::new
         */

        Two tw = Sample::new;
        Sample s1 = tw.get();
        Sample s2 = tw.get();
        Sample s3 = tw.get();

    }
}

