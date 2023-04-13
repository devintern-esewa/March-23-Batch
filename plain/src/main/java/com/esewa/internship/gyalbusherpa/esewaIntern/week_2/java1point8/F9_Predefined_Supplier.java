package week_2.java1point8;
import java.util.Date;
import java.util.function.Supplier;

public class F9_Predefined_Supplier {
    /*
        Just supply my required objects and it won't take any input --> Supplier

        interface Supplier<R>
        {
            public R get();
        }

     */
    public static void main(String[] args) {
        Supplier<Date> s = ()-> new Date();
        System.out.println(s.get());

        Supplier<String> s2 = () -> {
            String otp = "";
            for (int i = 0; i < 6; i++) {

                otp += (int)(Math.random()*10);
            }
            return otp;
        };

        System.out.println(s2.get());
    }
}
