package springAOP.example;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void checkout(){
        // logging
        // authentication and authorization
        // sanitize the data

        System.out.println("Checkout method from shopping cart called");
    }

    public int quantity(){
        return 2;
    }
}
