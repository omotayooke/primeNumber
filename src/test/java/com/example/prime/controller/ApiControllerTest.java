package com.example.prime.controller;

import com.example.prime.model.PrimeResponse;
import com.example.prime.service.ApiService;
import com.example.prime.service.CacheService;
import com.example.prime.service.PrimeService;
import com.example.prime.service.SievePrimeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ApiControllerTest {
    @InjectMocks
    ApiService apiService;

    private MockMvc mockMvc;
    @Mock
    PrimeService primeService;
    @Mock
    CacheService cacheService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ApiController(apiService)).build();
    }


    @Test
    public void findPrimeNumber() throws Exception {

        String jsonStringExpected = "{\"initial\":10,\"primes\":[2,3,5,7]}";
        when(primeService.getAllPrimeNumbers(anyInt())).thenReturn(Arrays.asList(2, 3, 5, 7));
        when(cacheService.convertStringToPrimeResponse(anyString())).thenReturn(any(PrimeResponse.class));
        MvcResult result =  mockMvc.perform(get("/primes/10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertNotNull(result);
        String actualString = result.getResponse().getContentAsString();
        assertEquals(jsonStringExpected, actualString);
    }

    @Test
    public void findPrimeNumberWithThreads() throws Exception {

        String jsonStringExpected = "{\"initial\":19,\"primes\":[2,3,5,7,11,13,17,19]}";
        MvcResult result =  mockMvc.perform(get("/threads/5/primes/19")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertNotNull(result);
        String actualString = result.getResponse().getContentAsString();
        assertEquals(jsonStringExpected, actualString);
    }
}