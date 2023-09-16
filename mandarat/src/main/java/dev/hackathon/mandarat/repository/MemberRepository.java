package dev.hackathon.mandarat.repository;

import dev.hackathon.mandarat.entity.Member;
import dev.hackathon.mandarat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select r from Member r where r.email = :email")
    Member findMemberByEmail(@Param("email") String email);
}
