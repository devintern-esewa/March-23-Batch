package springCore.oic;

import org.springframework.stereotype.Component;

@Component
public class Vodaphone implements Sim{

    @Override
    public void calling() {
        System.out.println("Call using voda");
    }

    @Override
    public void data() {
        System.out.println("browse using airtel");
    }
}

