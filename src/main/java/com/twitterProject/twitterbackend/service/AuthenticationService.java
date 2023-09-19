package com.twitterProject.twitterbackend.service;

import com.twitterProject.twitterbackend.daorepository.MemberRepository;
import com.twitterProject.twitterbackend.daorepository.RoleRepository;
import com.twitterProject.twitterbackend.dto.LoginResponse;
import com.twitterProject.twitterbackend.dto.RegistrationResponse;
import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Role;
import com.twitterProject.twitterbackend.exceptions.AuthValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {
    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    public RegistrationResponse register(String name, String userName,String email, String password){

        //Validation
        Optional<Member> foundMemberEmail = memberRepository.findMemberByEmail(email);
        AuthValidation.isEmailPresent(foundMemberEmail);
        Optional<Member> foundMemberUserName = memberRepository.findMemberByUserName(userName);
        AuthValidation.isUserNamePresent(foundMemberUserName);
        //Registry
        String encodedPassword = passwordEncoder.encode(password);
        Role memberRole = roleRepository.findByAuthority("USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(memberRole);
        LocalDate dateJoined = LocalDate.now();
        Member member = new Member();
        member.setName(name);
        member.setUserName(userName);
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setDateJoined(dateJoined);
        member.setAuthorities(roles);
        memberRepository.save(member);
        return new RegistrationResponse(name,userName);
    }

    public String login(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwtToken(auth);
            return token;
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid Credentials");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred during authentication", ex);
        }
    }
}
