package com.MateStudy.MateStudy.repository.attend;

import com.MateStudy.MateStudy.domain.attendance.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long> {
}
