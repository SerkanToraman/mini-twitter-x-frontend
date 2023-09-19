package com.twitterProject.twitterbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record UpdatedTweetRequest (String tweet,int tweetId){
}
