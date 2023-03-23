package com.example.prime.service;

import java.util.ArrayList;
import java.util.List;

public class SievePrimeService {
    private final int numberOfThreads;
    private final int numRange;
    private boolean[] isPrime;
    private int rangeBlockSize;
    private List<Thread> threads;

    public SievePrimeService(int numberOfThreads, int numRange) {
        this.numberOfThreads = numberOfThreads;
        this.numRange = numRange;
        this.isPrime = new boolean[numRange + 1];
        this.rangeBlockSize = (numRange + numberOfThreads - 1) / numberOfThreads;
        this.threads = new ArrayList<>();

        for (int i = 2; i <= numRange; i++) {
            isPrime[i] = true;
        }
    }

    public List<Integer> getIsPrime() throws InterruptedException {
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * rangeBlockSize + 2;
            int end = Math.min((i + 1) * rangeBlockSize + 1, numRange);

            Thread thread = new Thread(() -> {
                for (int j = 2; j * j <= end; j++) {
                    if (isPrime[j]) {
                        int startMultiple = (start / j) * j;
                        if (startMultiple < j * j) {
                            startMultiple = j * j;
                        }
                        for (int k = startMultiple; k <= end; k += j) {
                            isPrime[k] = false;
                        }
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= numRange; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

}
