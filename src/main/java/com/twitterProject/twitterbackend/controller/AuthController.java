package com.twitterProject.twitterbackend.controller;

import com.twitterProject.twitterbackend.dto.LoginRequest;
import com.twitterProject.twitterbackend.dto.LoginResponse;
import com.twitterProject.twitterbackend.dto.RegistrationRequest;
import com.twitterProject.twitterbackend.dto.RegistrationResponse;
import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.service.AuthenticationService;
import com.twitterProject.twitterbackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private AuthenticationService authenticationService;
    private MemberService memberService;

    public AuthController(AuthenticationService authenticationService, MemberService memberService) {
        this.authenticationService = authenticationService;
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public RegistrationResponse register(@Validated @RequestBody RegistrationRequest registrationRequest){
        return authenticationService.register(registrationRequest.getName(), registrationRequest.getUserName(), registrationRequest.getEmail(),
                registrationRequest.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest){
        Member member = memberService.findMemberByEmail(loginRequest.getEmail());
       String token =  authenticationService.login(loginRequest.getEmail(),
                loginRequest.getPassword());
        return new LoginResponse(token,member.getId(),member.getUsername());
    }
}
