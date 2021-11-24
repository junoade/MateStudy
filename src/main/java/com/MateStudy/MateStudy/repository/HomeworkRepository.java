package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
