package com.twitterProject.twitterbackend.service;

import com.twitterProject.twitterbackend.daorepository.MemberRepository;
import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImp implements UserDetailsService,MemberService{
    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member is not valid"));
    }
    public List<Tweet> loadUserTweets(int id){
        Optional<List<Tweet>> userTweets = memberRepository.findMemberTweetsById(id);
        if(userTweets.isPresent()){
            return userTweets.get();
        }
        return null;
    }

    @Override
    public Member findById(int id) {
        Optional<Member> existMember = memberRepository.findById(id);
        if(existMember.isPresent()){
            return existMember.get();
        }
        return null;
    }

    @Override
    public Member findMemberByEmail(String email) {
        Optional<Member> existMember = memberRepository.findMemberByEmail(email);
        if(existMember.isPresent()){
            return existMember.get();
        }
        return null;
    }
}
