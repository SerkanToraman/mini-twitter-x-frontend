package com.twitterProject.twitterbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {
    private int tweetId;
    private int tweetParentId;
    private String tweet;
    private String name;
    private String userName;
    private int memberId;
    private List<TweetLikeResponse> likes;
    private int replyCount;
    private LocalDateTime createdAt;
    private int retweetId;
    private List<RetweetResponse> retweets;
    private TweetResponse tweetResponse;
    public TweetResponse(
            int tweetId,
            int tweetParentId,
            String tweet,
            String name,
            String userName,
            int memberId,
            List<TweetLikeResponse> likes,
            int replyCount,
            LocalDateTime createdAt,
            int retweetId,
            List<RetweetResponse> retweets
    ) {
        this.tweetId = tweetId;
        this.tweetParentId = tweetParentId;
        this.tweet = tweet;
        this.name = name;
        this.userName = userName;
        this.memberId = memberId;
        this.likes = likes;
        this.replyCount = replyCount;
        this.createdAt = createdAt;
        this.retweetId =retweetId;
        this.retweets = retweets;
    }
}
