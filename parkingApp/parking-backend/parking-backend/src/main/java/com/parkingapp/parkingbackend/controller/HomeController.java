package com.parkingapp.parkingbackend.controller;

import com.parkingapp.parkingbackend.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService){
        this.homeService=homeService;
    }

    @GetMapping("/hello")
    public String hello(){
        return homeService.getWelcomeMessage();
    }
}
