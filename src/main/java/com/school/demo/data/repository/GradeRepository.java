package com.school.demo.data.repository;

import com.school.demo.data.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GradeRepository extends JpaRepository<Grade, Long> {
}
