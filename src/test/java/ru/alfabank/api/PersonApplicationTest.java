package ru.alfabank.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql("/data.sql")
@Transactional
public class PersonApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }


    @Test
    public void testGetPerson() throws Exception {
        mockMvc.perform(get("/person/0002")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.id", is("0002")))
                .andExpect(jsonPath("$.lastName", is("Петров")))
                .andExpect(jsonPath("$.firstName", is("Алексей")))
                .andExpect(jsonPath("$.phoneNumber", is("9551142233")))
                .andExpect(jsonPath("$.email", is("petrov@alex.net")))
        ;
    }

    @Test
    public void testPersonNotFound() throws Exception {
        mockMvc.perform(get("/person/007")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
