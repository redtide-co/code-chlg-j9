package co.redtide.chlg.api;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.io.IOException;
import java.util.function.Supplier;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalyos.jfiglet.FigletFont;

@RestController
@RequestMapping(name = "greet", path = "/api/greet")
public class GreetController {
    static final String prefix = "";
    static final String[] greetWords = { "Hello", "Welcome", "Greetings", "Surprise", "Gambai" };
    static final String separator = ", ";
    static final Supplier<String> codeName = () -> randomAlphabetic(5, 11);
    static final String postfix = "!";

    @GetMapping(produces = TEXT_PLAIN_VALUE)
    private ResponseEntity<String> getGreet() throws IOException {
        String greet = greetWords[nextInt(0, greetWords.length)];
        String alias = codeName.get();
        String phrase = prefix + greet + separator + alias + postfix;
        HttpHeaders headers = new HttpHeaders();
        headers.setETag('"' + phrase + '"');
        //headers.setLocation();
        return new ResponseEntity<String>(FigletFont.convertOneLine(phrase), headers, OK);
    }

}
