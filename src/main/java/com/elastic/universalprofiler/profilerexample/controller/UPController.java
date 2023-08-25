package com.elastic.universalprofiler.profilerexample.controller;

import com.elastic.universalprofiler.profilerexample.service.UPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Integer;

@RestController
public class UPController {

    @Autowired
    UPService service;

    @GetMapping("/start")
    public String start(@RequestParam String fibonacci, @RequestParam String sleepFor, @RequestParam(required = false) String fixed) throws InterruptedException {
        return service.entryFunction(Integer.parseInt(fibonacci), Integer.parseInt(sleepFor), fixed);
    }

}
