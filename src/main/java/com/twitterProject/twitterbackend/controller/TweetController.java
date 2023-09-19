package com.twitterProject.twitterbackend.controller;

import com.twitterProject.twitterbackend.dto.*;
import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Tweet;
import com.twitterProject.twitterbackend.service.MemberServiceImp;
import com.twitterProject.twitterbackend.service.TweetService;
import com.twitterProject.twitterbackend.util.TweetUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;
    private MemberServiceImp memberService;

    @Autowired
    public TweetController(TweetService tweetService, MemberServiceImp memberService) {
        this.tweetService = tweetService;
        this.memberService = memberService;
    }
    @GetMapping("/")
    public List<TweetResponse> findAll(){
        List<Tweet> tweetList= tweetService.findAll();
        List<TweetResponse> tweetResponses = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponses.add(tweetService.tweetsWithRetweets(tweet));
        }
        return tweetResponses;
    }
    @GetMapping("/{tweetId}")
    public TweetResponse findById( @PathVariable int tweetId){
       Tweet tweet = tweetService.findById(tweetId);
     return tweetService.tweetsWithRetweets(tweet);
    }

    @GetMapping("/mainpage")
    public List<TweetResponse> findParentTweets(){
        List<Tweet> tweetList = tweetService.findParentTweets();
        List<TweetResponse> tweetResponses = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponses.add(tweetService.tweetsWithRetweets(tweet));
        }
        return tweetResponses;
    }
    @GetMapping("/parent/{parentTweetId}")
    public List<TweetResponse> findByParentId( @PathVariable int parentTweetId){
        List<Tweet> tweetList = tweetService.findChildTweets(parentTweetId);
        List<TweetResponse> tweetResponses = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponses.add(tweetService.tweetsWithRetweets(tweet));
        }
        return tweetResponses;
    }

    @GetMapping("/membertweet/{userName}")
    public List<TweetResponse> findMemberTweetsById( @PathVariable String userName){
        List<Tweet> tweetList = tweetService.findMembersTweets(userName);
        List<TweetResponse> tweetResponses = new ArrayList<>();
        for(Tweet tweet : tweetList){
            tweetResponses.add(tweetService.tweetsWithRetweets(tweet));
        }
        return tweetResponses;
    }
    @PostMapping("/")
    public NewTweetResponse saveTweet(@RequestBody NewTweetRequest newTweetRequest){
        Member existingMember = memberService.findById(newTweetRequest.getMemberId());
        if (existingMember == null) {
            return null;
        }
        Tweet tweet = TweetUtility.createTweet(existingMember, newTweetRequest.getTweet(), newTweetRequest.getParentId());
        tweetService.save(tweet);
        return new NewTweetResponse(existingMember.getName(), existingMember.getUsername(),
                existingMember.getId(), tweet.getId(), tweet.getTweet(), tweet.getCreatedAt());
    }

    @PostMapping("/updateTweet")
    public UpdatedTweetResponse editTweet(@RequestBody UpdatedTweetRequest updatedTweetRequest){
        Tweet tweetToEdit = tweetService.findById(updatedTweetRequest.tweetId());
        if (tweetToEdit == null) {
            return null;
        }
        tweetToEdit.setTweet(updatedTweetRequest.tweet());
        tweetService.save(tweetToEdit);

        return new UpdatedTweetResponse(tweetToEdit.getId(), tweetToEdit.getTweet());
    }

    @PostMapping("/deletetweet")
    public DeleteTweetResponse deleteTweet(@RequestBody DeleteTweetRequest deleteTweetRequest){
        int tweetId = deleteTweetRequest.deleteTweetId();
        Tweet tweetToDelete = tweetService.findById(tweetId);
        if (tweetToDelete == null) {
            return null;
        }
        tweetService.delete(tweetId);
        List<Tweet> paretnRetweets = tweetService.findRetweets(tweetId);
        for(Tweet retweetsToDelete : paretnRetweets){
            tweetService.delete(retweetsToDelete.getId());
        }
        List<Tweet> childTweets = tweetService.findChildTweets(tweetId);
        for(Tweet childTweettoDelete: childTweets){
            tweetService.delete(childTweettoDelete.getId());
            List<Tweet> childRetweets = tweetService.findRetweets(childTweettoDelete.getId());
            for(Tweet retweetsToDelete : childRetweets){
                tweetService.delete(retweetsToDelete.getId());
            }
        }
        return new DeleteTweetResponse(tweetToDelete.getId());
    }

    @PostMapping("/retweet")
    public RetweetResponse retweet(@RequestBody RetweetRequest retweetRequest){
        Tweet existingTweet = tweetService.findById(retweetRequest.getTweetId());
        Member existingMember = memberService.findById(retweetRequest.getMemberId());
        if (existingTweet == null || existingMember == null) {
            return null;
        }
        existingTweet.addRetweet(existingMember);
        tweetService.save(existingTweet);
        Tweet retweet = TweetUtility.createTweet(existingMember, existingTweet.getTweet(), 0);
        retweet.setRetweetId(retweetRequest.getTweetId());
        tweetService.save(retweet);
        return new RetweetResponse(retweetRequest.getMemberId());
    }
    @PostMapping("/cancelretweet")
    public RetweetResponse cancelRetweet(@RequestBody RetweetRequest retweetRequest){
        Tweet existingTweet = tweetService.findById(retweetRequest.getTweetId());
        Member existingMember = memberService.findById(retweetRequest.getMemberId());
        if(existingTweet!= null && existingMember!= null){
            existingTweet.removeRetweet(existingMember);
            tweetService.save(existingTweet);
            RetweetResponse response = new RetweetResponse(retweetRequest.getMemberId());
            return response;
        }
        return null;
    }

    @PostMapping("/deleteretweet")
    public void deleteRetweet(@RequestBody DeleteTweetRequest deleteTweetRequest){
        Tweet retweetToDelete = tweetService.findById(deleteTweetRequest.deleteTweetId());
        if (retweetToDelete != null) {
            tweetService.delete(deleteTweetRequest.deleteTweetId());
        }
    }
    @PostMapping("/deleteretweetv2")
    public void deleteRetweet(@RequestBody DeleteThroughParentRetweetId deleteThroughParentRetweetId){
        Integer retweetToDelete = tweetService.findMembersRetweetId(deleteThroughParentRetweetId.retweetId(),deleteThroughParentRetweetId.memberId());
        System.out.println(retweetToDelete);
        if (retweetToDelete != null) {
            tweetService.delete(retweetToDelete);
        }
    }


    @PostMapping("/like")
    public TweetLikeResponse likeTweet(@RequestBody TweetLikeRequest tweetLikeRequest){
        Tweet existingTweet = tweetService.findById(tweetLikeRequest.getTweetId());
        Member existingMember = memberService.findById(tweetLikeRequest.getMemberId());
        if(existingTweet!= null && existingMember!= null){
            existingTweet.addLikes(existingMember);
            tweetService.save(existingTweet);
            TweetLikeResponse response = new TweetLikeResponse(tweetLikeRequest.getMemberId());
            return response;
        }
        return null;
    }
    @PostMapping("/dislike")
    public TweetLikeResponse dislikeTweet(@RequestBody TweetLikeRequest tweetLikeRequest){
        Tweet existingTweet = tweetService.findById(tweetLikeRequest.getTweetId());
        Member existingMember = memberService.findById(tweetLikeRequest.getMemberId());
        if(existingTweet!= null && existingMember!= null){
            existingTweet.removeLikes(existingMember);
            tweetService.save(existingTweet);
            TweetLikeResponse response = new TweetLikeResponse(tweetLikeRequest.getMemberId());
            return response;
        }
        return null;
    }
}
