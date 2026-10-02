package com.parkingapp.parkingbackend.auth;

import com.parkingapp.parkingbackend.user.User;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController{

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request){
        User user=authService.signup(request);
        return "User created successfully with ID: " + user.getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        User user=authService.login(request);

        return "Login successful for "
                + user.getFirstName()
                + " - Role: "
                + user.getRole();
    }

}
