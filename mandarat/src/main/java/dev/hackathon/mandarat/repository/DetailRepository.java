package dev.hackathon.mandarat.repository;

import dev.hackathon.mandarat.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query("select r from Detail r where r.post.id = :postId")
    List<Detail> findDetailByPostId(@Param("postId") Long postId);
}
