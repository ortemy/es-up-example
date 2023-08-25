package com.elastic.universalprofiler.profilerexample.service;

import org.springframework.stereotype.Service;

@Service
public class UPService {

    public String entryFunction(int fibonacci, int sleepFor, String fixed) throws InterruptedException{
        var fibonacciResult = calculateFibonacci(fibonacci, fixed!=null);
        var sleptFor = callExternalAPI(sleepFor);
        return fibonacciResult+"\n"+sleptFor+"\n";
    }

    //function that at times consumes a lot of CPU based on the 'fixed' flag
    public String calculateFibonacci(int n, boolean fixed){
        int result = fibonacci(n,fixed);
        return "Fibonacci number for "+n+" is " +result;
    }


    //Simulation of an external API call - will show up in APM charts
    public String callExternalAPI(int duration) throws InterruptedException{
        int timeToSleep= duration*1000;
        Thread.sleep(timeToSleep);
        return "External API call took "+ duration+" seconds";
    }

    private int fibonacciByIteration(int n){
        if(n <= 1) {
            return n;
        }
        int fib = 1;
        int prevFib = 1;

        for(int i=2; i<n; i++) {
            int temp = fib;
            fib+= prevFib;
            prevFib = temp;
        }
        return fib;
    }

    private int fibonacciByRecursion(int n){
        if (n < 2) {
            return n;
        }
        return fibonacciByRecursion(n - 1) + fibonacciByRecursion(n - 2);
    }

    private int fibonacci(int n, boolean fixed)  {
        //CPU-intensive approach (wrong)
        if(!fixed) {
            return fibonacciByRecursion(n);
        }
        //The right approach
        else{
            return fibonacciByIteration(n);
        }
    }
}
