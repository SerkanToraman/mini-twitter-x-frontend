package com.twitterProject.twitterbackend.exceptions;

import com.twitterProject.twitterbackend.entity.Member;

import java.util.Optional;

public class AuthValidation {
    public static void isEmailPresent(Optional<Member> member){
        if(member.isPresent()){
            throw new IllegalArgumentException("Email or Username is already registered");
        }
    }
    public static void isUserNamePresent(Optional<Member> member){
        if(member.isPresent()){
            throw new IllegalArgumentException("Email or Username is already registered");
        }
    }

}
