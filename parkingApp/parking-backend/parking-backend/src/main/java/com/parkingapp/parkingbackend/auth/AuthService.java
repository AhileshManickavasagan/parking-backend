package com.parkingapp.parkingbackend.auth;


import com.parkingapp.parkingbackend.exception.EmailAlreadyExistsException;
import com.parkingapp.parkingbackend.exception.InvalidCredentialsException;
import com.parkingapp.parkingbackend.user.User;
import com.parkingapp.parkingbackend.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User signup(SignupRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        User user=new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
       // user.setPassword(request.getPassword());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    public User login(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->   new InvalidCredentialsException(
                "Invalid email or password")
        );

        boolean passwordMatches=passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw  new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }
        return user;
    }
}
