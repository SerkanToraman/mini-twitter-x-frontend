package com.twitterProject.twitterbackend.daorepository;

import com.twitterProject.twitterbackend.entity.Member;
import com.twitterProject.twitterbackend.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findMemberByEmail(String email);
    @Query("SELECT m FROM Member m WHERE m.userName = :userName")
    Optional<Member> findMemberByUserName(String userName);

    @Query("SELECT m FROM Member m WHERE m.id =:id")
    Optional<List<Tweet>> findMemberTweetsById(int id);

}
