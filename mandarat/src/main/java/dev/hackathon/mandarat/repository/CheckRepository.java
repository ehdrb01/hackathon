package dev.hackathon.mandarat.repository;

import dev.hackathon.mandarat.entity.CheckWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<CheckWord, Long> {
}
