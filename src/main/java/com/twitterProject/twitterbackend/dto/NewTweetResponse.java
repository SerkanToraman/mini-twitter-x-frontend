package com.twitterProject.twitterbackend.dto;

import java.time.LocalDateTime;

public record NewTweetResponse(String name, String userName, int userId, int tweetId, String tweet, LocalDateTime localDateTime) {
}
