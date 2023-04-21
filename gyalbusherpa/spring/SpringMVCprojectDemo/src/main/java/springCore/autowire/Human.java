package springCore.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Human {
    @Autowired
    @Qualifier("donHeart")
    private Heart heart;

    public Human() {
    }

    public Human(Heart heart) {
        this.heart = heart;
        System.out.println("this is called");
    }

    public void startPumping() {
        if (heart != null) {
            heart.pump();
            System.out.println(heart.getNoOfHeart() + heart.getNameOfAnimal());
        }
        System.out.println("dead");
    }

}
