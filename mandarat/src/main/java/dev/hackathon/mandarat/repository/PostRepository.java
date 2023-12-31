package dev.hackathon.mandarat.repository;

import dev.hackathon.mandarat.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select r from Post r where r.member.id = :memberId")
    List<Post> findPostByMemberId(@Param("memberId") Long memberId);
}
