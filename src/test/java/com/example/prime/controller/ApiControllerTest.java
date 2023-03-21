package com.example.prime.controller;

import com.example.prime.service.PrimeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ApiControllerTest {
    @InjectMocks
    PrimeService primeService;
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController(primeService)).build();
    }

    @Test
    public void testFindPrimeNumber() throws Exception {

        String jsonStringExpected = "{\"initial\":10,\"primes\":[2,3,5,7]}";
        MvcResult result =  mockMvc.perform(get("/primes/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertNotNull(result);
        String actualString = result.getResponse().getContentAsString();
        assertEquals(jsonStringExpected, actualString);
    }
}