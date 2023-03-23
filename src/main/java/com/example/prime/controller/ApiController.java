package com.example.prime.controller;

import com.example.prime.model.PrimeResponse;
import com.example.prime.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService){
        this.apiService = apiService;
    }

    @GetMapping(value = "/primes/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeResponse> findPrimeNumber(@PathVariable("number") int number) throws JsonProcessingException {
        PrimeResponse response = apiService.cacheAndGetPrimeNumber(number);
        if (response.getError() == null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping(value = "/threads/{thread}/primes/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeResponse> findPrimeNumberWithThreads(@PathVariable("number") int number, @PathVariable("thread") int threads) throws JsonProcessingException, InterruptedException {
        if (threads > 10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PrimeResponse(null, null, "Threads cannot exceed 10"));
        }

        PrimeResponse response = apiService.cacheThreadedPrimeNumber(number, threads);
        if (response.getError() == null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
