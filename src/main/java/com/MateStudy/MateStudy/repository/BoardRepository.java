package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
