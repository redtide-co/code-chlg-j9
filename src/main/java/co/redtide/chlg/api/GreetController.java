package co.redtide.chlg.api;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "greet", path = "/api/greet")
public class GreetController {

    @RequestMapping(method = RequestMethod.GET)
    Collection<String> greet() {
        return Arrays.asList("Hello World!");
    }

}
