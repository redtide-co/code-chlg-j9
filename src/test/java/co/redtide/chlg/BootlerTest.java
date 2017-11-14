package co.redtide.chlg;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BootlerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void get_api_list() throws Exception {
        // Arrange...
        RequestBuilder mockHttpRequest = MockMvcRequestBuilders.get("/api").header("host", "localhost:8080")
                .accept(APPLICATION_JSON);
        // Act...
        ResultActions testResult = mvc.perform(mockHttpRequest);
        // Assert...
        testResult.andExpect(status().isOk()).andExpect(jsonPath("$.greet", endsWith("/api/greet")))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }

}
