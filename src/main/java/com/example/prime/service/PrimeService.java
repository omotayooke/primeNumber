package com.example.prime.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrimeService {

    public List<Integer> getAllPrimeNumbers(int numberToCheckPrime){
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i <= numberToCheckPrime; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
