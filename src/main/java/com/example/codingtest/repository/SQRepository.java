package com.example.codingtest.repository;

import com.example.codingtest.entity.SQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQRepository extends JpaRepository<SQuestion, Integer> {
}
