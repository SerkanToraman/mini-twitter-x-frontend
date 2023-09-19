package com.twitterProject.twitterbackend.service;

import com.twitterProject.twitterbackend.daorepository.TweetRepository;
import com.twitterProject.twitterbackend.dto.TweetResponse;
import com.twitterProject.twitterbackend.entity.Tweet;
import com.twitterProject.twitterbackend.util.TweetUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImp implements TweetService{
    private TweetRepository tweetRepository;
    @Autowired
    public TweetServiceImp(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }
    @Override
    public Tweet findById(int id) {
        Optional<Tweet> existTweet = tweetRepository.findById(id);
        if(existTweet.isPresent()){
            return existTweet.get();
        }
        return null;
    }
    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }
    @Override
    public Tweet delete(int id) {
        Tweet tweet = findById(id);
        tweetRepository.delete(tweet);
        return tweet;
    }
    public List<Tweet> findParentTweets(){
        Optional<List<Tweet>> parentTweets = tweetRepository.findParentTweets();
        if(parentTweets.isPresent()){
            return parentTweets.get();
        }
        return null;
    }

    public List<Tweet> findChildTweets(int id){
        Optional<List<Tweet>> parentTweets = tweetRepository.findChildTweets(id);
        if(parentTweets.isPresent()){
            return parentTweets.get();
        }
        return null;
    }

    public List<Tweet> findRetweets(int tweetId){
        Optional<List<Tweet>> retweets = tweetRepository.findRetweets(tweetId);
        if(retweets.isPresent()){
            return retweets.get();
        }
        return null;
    }

    public Integer findChildCount(int parentId){
        Optional<Integer> replyCount = tweetRepository.findChildCount(parentId);
        if(replyCount.isPresent() &&  replyCount!=null){
            return replyCount.get();
        }
        return 0;
    }

    public TweetResponse tweetsWithRetweets(Tweet tweet){
            if(tweet.getRetweetId()==0){
                int replyCount = findChildCount(tweet.getId());
                TweetResponse reTweetResponse = new TweetResponse();
                return TweetUtility.tweetResponseCreator(tweet,replyCount,reTweetResponse);
            }else{
                Tweet originalTweet = findById(tweet.getRetweetId());
                int originalTweetReplyCount = findChildCount(originalTweet.getId());
                int replyCount = findChildCount(tweet.getId());
                TweetResponse reTweetResponse = TweetUtility.tweetResponseCreator(originalTweet,originalTweetReplyCount);
                return TweetUtility.tweetResponseCreator(tweet,replyCount,reTweetResponse);
            }
        }

    @Override
    public Integer findMembersRetweetId(int retweetId, int memberId) {
        Optional<Tweet> tweet = tweetRepository.findMembersRetweet(retweetId,memberId);
        if (tweet.isPresent()){
           return tweet.get().getId();
        }
        return 0;
    }

    @Override
    public List<Tweet> findMembersTweets(String userName) {
        Optional<List<Tweet>> retweets = tweetRepository.findMembersTweets(userName);
        if(retweets.isPresent()){
            return retweets.get();
        }
        return null;
    }
}
