package com.twitterProject.twitterbackend.daorepository;

import com.twitterProject.twitterbackend.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    @Query("SELECT t FROM Tweet t WHERE t.parentId = 0 ORDER BY t.createdAt DESC")
    Optional<List<Tweet>> findParentTweets();
    @Query("SELECT t FROM Tweet t WHERE t.parentId = :parentId ORDER BY t.createdAt DESC")
    Optional<List<Tweet>> findChildTweets(int parentId);

    @Query("SELECT t FROM Tweet t WHERE t.retweetId = :retweetId")
    Optional<List<Tweet>> findRetweets(int retweetId);

    @Query("SELECT t FROM Tweet t LEFT JOIN t.member m WHERE t.retweetId = :retweetId AND m.id = :memberId")
    Optional<Tweet> findMembersRetweet(int retweetId, int memberId);

    @Query("SELECT t FROM Tweet t LEFT JOIN t.member m WHERE m.userName = :userName ORDER BY t.createdAt DESC")
    Optional<List<Tweet>>  findMembersTweets(String userName);

    @Query("SELECT COUNT(t.parentId) FROM Tweet t WHERE t.parentId = :parentId GROUP BY t.parentId")
    Optional<Integer> findChildCount(int parentId);



}
