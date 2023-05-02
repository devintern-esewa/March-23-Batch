package np.com.esewa.learn.springsecuritysample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thinkpad on 5/2/2023
 * Controller for handling mappings other than Home
 */

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/hi")
    public String sayHello() {
        return "hello folks!";
    }

    @GetMapping("/test")
    public String test() {
        return "hey! is it well ?";
    }

}
