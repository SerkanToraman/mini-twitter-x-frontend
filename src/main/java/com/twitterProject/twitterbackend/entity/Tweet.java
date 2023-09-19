package com.twitterProject.twitterbackend.entity;


import com.twitterProject.twitterbackend.dto.TweetLikeRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="tweet", schema="twitterbackend")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "tweet")
    private String tweet;
    @Column(name = "parent_id")
    private int parentId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "retweet_id")
    private int retweetId;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name ="likes",schema="twitterbackend",
            joinColumns = @JoinColumn(name="tweet_id"),
            inverseJoinColumns = @JoinColumn(name="member_id"))
    private Set<Member> likedTweets = new HashSet<>();
    public void addLikes (Member member){
        if(likedTweets==null){
            likedTweets = new HashSet<>();
        }
        likedTweets.add(member);
    }
    public void removeLikes (Member member){
        if(likedTweets==null){
            likedTweets = new HashSet<>();
        }
        likedTweets.remove(member);
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name ="retweet",schema="twitterbackend",
            joinColumns = @JoinColumn(name="tweet_id"),
            inverseJoinColumns = @JoinColumn(name="member_id"))
    private Set<Member> retweetedTweets = new HashSet<>();
    public void addRetweet (Member member){
        if(retweetedTweets==null){
            retweetedTweets = new HashSet<>();
        }
        retweetedTweets.add(member);
    }
    public void removeRetweet (Member member){
        if(retweetedTweets==null){
            retweetedTweets = new HashSet<>();
        }
        retweetedTweets.remove(member);
    }

}
