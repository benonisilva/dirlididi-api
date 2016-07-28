package bootwildfly;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWildFlyController {


    @RequestMapping(value = "/trade", method = RequestMethod.GET)
    public String sayHello(){
        return ("Hello, SpringBoot on Wildfly");
    }
}