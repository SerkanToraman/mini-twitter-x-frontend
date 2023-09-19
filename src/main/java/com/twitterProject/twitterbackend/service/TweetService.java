package com.twitterProject.twitterbackend.service;

import com.twitterProject.twitterbackend.dto.TweetResponse;
import com.twitterProject.twitterbackend.entity.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> findAll();
    Tweet findById(int id);
    Tweet save(Tweet tweet);
    Tweet delete(int id);
    List<Tweet> findParentTweets();
    List<Tweet> findChildTweets(int id);
    Integer findChildCount(int parentId);
    TweetResponse tweetsWithRetweets(Tweet tweet);
    List<Tweet> findRetweets(int tweetId);
    Integer findMembersRetweetId(int retweetId, int memberId);
    List<Tweet>  findMembersTweets(String userName);



}
