package com.twitterProject.twitterbackend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTweetRequest {
    private int memberId;
    private String tweet;
    private int parentId;
}
