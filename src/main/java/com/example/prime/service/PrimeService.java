package com.example.prime.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PrimeService {

    public List<Integer> getAllPrimeNumbers(int numberToCheckPrime){
        return IntStream.rangeClosed(2, numberToCheckPrime)
                .parallel()
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean isPrime(int number) {
        if(number == 2)
            return number == 2;
        else
            return  (number % 2) != 0
                    &&
                    IntStream.rangeClosed(3, (int) Math.sqrt(number))
                            .filter(n -> n % 2 != 0)
                            .noneMatch(n -> (number % n == 0));
    }

}
