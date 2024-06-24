package com.example.codingtest.repository;

import com.example.codingtest.entity.MCQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MCQRepository extends JpaRepository<MCQuestion, Integer> {
}
