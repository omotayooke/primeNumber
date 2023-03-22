package com.example.prime.service;

import com.example.prime.model.PrimeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PrimeService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Cache<String, String> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(Duration.ofDays(5))
            .build();

    public PrimeResponse cacheAndGetPrimeNumber(int number) throws JsonProcessingException {
        PrimeResponse primeResponse = new PrimeResponse();
        String numberToString = String.valueOf(number);
        String value = cache.getIfPresent(numberToString);
        if (value == null){
            List<Integer> primeResult = getAllPrimeNumbers(number);
            if (!primeResult.isEmpty()){
                primeResponse.setInitial(number);
                primeResponse.setPrimes(primeResult);
                cachePrimeResponse(String.valueOf(number), primeResponse);
            }
            else {
                primeResponse.setError("No prime number found");
            }
            return primeResponse;
        }
        return objectMapper.readValue(value,PrimeResponse.class);
    }

    protected List<Integer> getAllPrimeNumbers(int numberToCheckPrime){
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

    protected void cachePrimeResponse(String key, PrimeResponse primeResponse) throws JsonProcessingException {
        String primeStringValue = objectMapper.writeValueAsString(primeResponse);
        cache.put(key, primeStringValue);
    }

}
