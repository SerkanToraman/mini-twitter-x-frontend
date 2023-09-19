package com.twitterProject.twitterbackend.service;

import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Tweet;

import java.util.List;

public interface MemberService {

    Member findById(int id);

    Member findMemberByEmail(String email);
}
