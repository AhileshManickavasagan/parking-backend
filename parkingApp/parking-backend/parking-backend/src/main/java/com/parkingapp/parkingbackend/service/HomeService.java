package com.parkingapp.parkingbackend.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String getWelcomeMessage(){
        return "Welcome to Parking App!";
    }
}
