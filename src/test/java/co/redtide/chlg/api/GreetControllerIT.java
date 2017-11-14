package co.redtide.chlg.api;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetControllerIT {

    private URI apiGreetUri;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        apiGreetUri = URI.create("http://localhost:" + port + "/api/greet/");
    }

    @Test
    public void get_api_greet() throws Exception {
        // Given...
        // When...
        ResponseEntity<String> response = template.getForEntity(apiGreetUri, String.class);
        // Then...
        assertEquals(OK, response.getStatusCode());
        assertTrue(response.getHeaders().getContentType().isCompatibleWith(TEXT_PLAIN));
        assertThat(response.getHeaders().getETag(), containsString(GreetController.separator));
        System.out.println(response.getBody());
    }

}
