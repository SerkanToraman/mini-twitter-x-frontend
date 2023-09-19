package com.twitterProject.twitterbackend.util;

import com.twitterProject.twitterbackend.daorepository.TweetRepository;
import com.twitterProject.twitterbackend.dto.RetweetResponse;
import com.twitterProject.twitterbackend.dto.TweetLikeResponse;
import com.twitterProject.twitterbackend.dto.TweetResponse;
import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Tweet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TweetUtility {
    public static TweetResponse tweetResponseCreator(Tweet tweet,int replyCount){
        Set<Member> likedMembersList = tweet.getLikedTweets();
        List<TweetLikeResponse> tweetLikeResponses = new ArrayList<>();
        for(Member member : likedMembersList ){
            tweetLikeResponses.add(new TweetLikeResponse(member.getId()));
        }
        Set<Member> retweetedMembersList = tweet.getRetweetedTweets();
        List<RetweetResponse> retweetResponses = new ArrayList<>();
        for(Member member : retweetedMembersList ){
            retweetResponses.add(new RetweetResponse(member.getId()));
        }
        return new TweetResponse(tweet.getId(),tweet.getParentId(), tweet.getTweet(),tweet.getMember().getName(),
                tweet.getMember().getUsername(),tweet.getMember().getId(),tweetLikeResponses,replyCount,
                tweet.getCreatedAt(),tweet.getRetweetId() ,retweetResponses);
    }
    public static TweetResponse tweetResponseCreator(Tweet tweet,int replyCount,TweetResponse reTweetResponse){
        Set<Member> likedMembersList = tweet.getLikedTweets();
        List<TweetLikeResponse> tweetLikeResponses = new ArrayList<>();
        for(Member member : likedMembersList ){
            tweetLikeResponses.add(new TweetLikeResponse(member.getId()));
        }
        Set<Member> retweetedMembersList = tweet.getRetweetedTweets();
        List<RetweetResponse> retweetResponses = new ArrayList<>();
        for(Member member : retweetedMembersList ){
            retweetResponses.add(new RetweetResponse(member.getId()));
        }
        return new TweetResponse(tweet.getId(),tweet.getParentId(), tweet.getTweet(),tweet.getMember().getName(),
                tweet.getMember().getUsername(),tweet.getMember().getId(),tweetLikeResponses,replyCount,
                tweet.getCreatedAt(),tweet.getRetweetId(),retweetResponses,reTweetResponse);
    }

    public static Tweet createTweet(Member member, String tweetText, int parentId) {
        Tweet tweet = new Tweet();
        tweet.setTweet(tweetText);
        tweet.setParentId(parentId);
        tweet.setMember(member);
        tweet.setCreatedAt(LocalDateTime.now());
        return tweet;
    }
}
