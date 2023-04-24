package springBeanScope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springBeanScope.api.MyBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {
    /*
    Only for study purpose. Dispatcher servlet has not been set!
     */

    @Autowired
    private MyBean myBean;

    @RequestMapping("/testing")
    public void test(HttpServletResponse response) throws IOException {
        // display website name
        System.out.println(myBean.getWebSiteName());

        response.getWriter().write("Old website name" + myBean.getWebSiteName());
        myBean.setWebSiteName("hero.com");
        response.getWriter().write("updated website name" + myBean.getWebSiteName());

    }

}
