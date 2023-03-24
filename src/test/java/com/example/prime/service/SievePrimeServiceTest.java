package com.example.prime.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SievePrimeServiceTest {

    @Test
    public void getPrimes() throws Exception {
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19);
        SievePrimeService sievePrimeService = new  SievePrimeService(4, 19);
        List<Integer> result = sievePrimeService.getIsPrime();
        assertEquals(8, result.size());
        assertEquals(expected, result);
    }
}
