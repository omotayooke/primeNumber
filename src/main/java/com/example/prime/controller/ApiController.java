package com.example.prime.controller;

import com.example.prime.model.PrimeResponse;
import com.example.prime.service.PrimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private PrimeService primeService;

    public ApiController(PrimeService primeService){
        this.primeService = primeService;
    }

    @GetMapping(value = "/primes/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeResponse> findPrimeNumber(@PathVariable("number") int number){
        PrimeResponse response = new PrimeResponse();
        List<Integer> primeList = primeService.getAllPrimeNumbers(number);
        if (!primeList.isEmpty()){
            response.setInitial(number);
            response.setPrimes(primeList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else{
            response.setError("No prime number found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
