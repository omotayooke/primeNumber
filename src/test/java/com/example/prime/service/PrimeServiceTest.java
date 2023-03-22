package com.example.prime.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrimeServiceTest {
    @Autowired
    private PrimeService primeService;

    @Test
    public void getAllPrimeNumbers() {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
        List<Integer> result = primeService.getAllPrimeNumbers(19);
        assertEquals(8, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void getAllPrimeNumbersWithNullResult() {
        List<Integer> result = primeService.getAllPrimeNumbers(1);
        assertEquals(new ArrayList<>(), result);
    }
}