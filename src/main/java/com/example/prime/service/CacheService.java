package com.example.prime.service;

import com.example.prime.model.PrimeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CacheService {
    private final ObjectMapper objectMapper;

    public CacheService() {
        this.objectMapper = new ObjectMapper();
    }

    public static final Cache<String, String> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(Duration.ofDays(5))
            .build();

    protected void cachePrimeResponse(String key, PrimeResponse primeResponse) throws JsonProcessingException {
        String primeStringValue = objectMapper.writeValueAsString(primeResponse);
        cache.put(key, primeStringValue);
    }

    public PrimeResponse convertStringToPrimeResponse(String value) throws JsonProcessingException {
       return objectMapper.readValue(value, PrimeResponse.class);
    }
}
