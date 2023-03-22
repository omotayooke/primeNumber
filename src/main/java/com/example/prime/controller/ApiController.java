package com.example.prime.controller;

import com.example.prime.model.PrimeResponse;
import com.example.prime.service.PrimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final PrimeService primeService;

    public ApiController(PrimeService primeService){
        this.primeService = primeService;
    }

    @GetMapping(value = "/primes/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeResponse> findPrimeNumber(@PathVariable("number") int number) throws JsonProcessingException {
        PrimeResponse response = primeService.cacheAndGetPrimeNumber(number);
        if (response.getError() == null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
