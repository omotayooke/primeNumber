package com.example.prime.service;

import com.example.prime.model.PrimeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    private final CacheService cacheService;
    private final PrimeService primeService;

    public ApiService(CacheService cacheService, PrimeService primeService) {
        this.cacheService = cacheService;
        this.primeService = primeService;
    }


    public PrimeResponse cacheAndGetPrimeNumber(int number) throws JsonProcessingException {
        PrimeResponse primeResponse = new PrimeResponse();
        String numberToString = String.valueOf(number);
        String value = CacheService.cache.getIfPresent(numberToString);
        if (value == null){
            List<Integer> primeResult = primeService.getAllPrimeNumbers(number);
            if (!primeResult.isEmpty()){
                primeResponse.setInitial(number);
                primeResponse.setPrimes(primeResult);
                cacheService.cachePrimeResponse(String.valueOf(number), primeResponse);
            }
            else {
                primeResponse.setError("No prime number found");
            }
            return primeResponse;
        }
        return cacheService.convertStringToPrimeResponse(value);
    }

    public PrimeResponse cacheThreadedPrimeNumber(int number, int threads) throws JsonProcessingException, InterruptedException {
        PrimeResponse primeResponse = new PrimeResponse();
        String numberToString = String.valueOf(number);
        String value = CacheService.cache.getIfPresent(numberToString);
        if (value == null){
            SievePrimeService sievePrimeService = new SievePrimeService(threads, number);
            List<Integer> primeResult = sievePrimeService.getIsPrime();
            if (!primeResult.isEmpty()){
                primeResponse.setInitial(number);
                primeResponse.setPrimes(primeResult);
               cacheService.cachePrimeResponse(String.valueOf(number), primeResponse);
            }
            else {
                primeResponse.setError("No prime number found");
            }
            return primeResponse;
        }
        return cacheService.convertStringToPrimeResponse(value);
    }
}
