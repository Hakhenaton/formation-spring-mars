package fr.sncf.d2d.up2dev.colibri2.dummy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception {
        this.mockMvc.perform(get("/hello"))
            .andExpectAll(
                status().isOk(),
                content().string("hello world")
            );
    }
}
