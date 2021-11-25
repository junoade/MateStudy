package com.MateStudy.MateStudy.repository;

import com.MateStudy.MateStudy.domain.common.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
