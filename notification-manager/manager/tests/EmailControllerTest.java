import arxa.EmailController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailController.class)
public class EmailControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(EmailController.class).build();
    }

    @Test
    public void testEmailController() throws Exception {
        mockMvc.perform(post("/email")
                .param("hostPassword","bad credentials")
                .param("host","nikarchakis@gmail.com")
                .param("destination","nikiforos.archakis@akka.eu")
                .param("content","hey what's up")
                .param("subject","Hi"))
                .andExpect(status().isNotFound()) // due to bad credentials
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"));

    }
}