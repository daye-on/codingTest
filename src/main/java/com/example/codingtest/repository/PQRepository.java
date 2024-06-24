package com.example.codingtest.repository;

import com.example.codingtest.entity.PQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PQRepository extends JpaRepository<PQuestion, Integer> {
}
