package co.redtide.chlg;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static com.google.common.base.Predicates.notNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Bootler {
    private List<RequestMapping> mappings;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Bootler.class, args);
    }

    @RequestMapping(path = "/api", method = RequestMethod.GET)
    Map<String, String> api(@RequestHeader("host") String host) {
        return mappings.stream().collect(toMap(RequestMapping::name, mapping -> host + mapping.path()[0]));
    }

    @Bean
    public ApplicationRunner applicationRunner(ApplicationContext ctx) {
        return args -> {
            mappings = ctx.getBeansWithAnnotation(RestController.class).values().stream()
                    .map(bean -> bean.getClass().getAnnotation(RequestMapping.class)).filter(notNull())
                    .collect(toList());
            //System.out.println("Let's inspect the beans provided by Spring Boot:");
            //Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
            System.out.println("**********************************");
            System.out.print("Let's inspect the non option args: ");
            System.out.println(args.getNonOptionArgs());
            System.out.print("Let's inspect the option names: ");
            System.out.println(args.getOptionNames());
            System.out.println("**********************************");
        };
    }

}
