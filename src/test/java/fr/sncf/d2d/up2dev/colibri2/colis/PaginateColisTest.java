package fr.sncf.d2d.up2dev.colibri2.colis;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PaginateColisTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void readFirstPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/colis")
            .queryParam("page", "0")
            .queryParam("itemsPerPage", "2")
        ).andExpectAll(
            status().isOk(),
            jsonPath("$.total", is(3)),
            jsonPath("$.items", hasSize(2))
        );
    }
}
