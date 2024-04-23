package fr.sncf.d2d.up2dev.colibri2.colis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.text.CharSequenceLength;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateColisTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    void createColis() throws Exception{
        this.mockMvc.perform(
            post("/colis")
                .content("""
                    {
                        "email": "nzaou.renaud@sncf.fr",
                        "address": "10 rue de la paix",
                        "details": "3ème étage"
                    }
                """)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isCreated(),
            jsonPath("$.id", isA(String.class)),
            jsonPath("$.address", is("10 rue de la paix")),
            jsonPath("$.details", is("3ème étage")),
            jsonPath("$.email", is("nzaou.renaud@sncf.fr")),
            jsonPath("$.trackingCode", CharSequenceLength.hasLength(16))
        );
    }
}
