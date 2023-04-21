package springCore.oic;

import org.springframework.stereotype.Component;

@Component
public class Airtel implements Sim{

    Airtel(){
        System.out.println("Airtel is called");
    }

    @Override
    public void calling() {
        System.out.println("Call using airtel");
    }

    @Override
    public void data() {
        System.out.println("browse using airtel");
    }
}
