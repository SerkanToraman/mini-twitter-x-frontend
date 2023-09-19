package com.twitterProject.twitterbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetLikeRequest {
    private int memberId;
    private int tweetId;
}
