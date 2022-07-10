package com.school.demo.data.repository;

import com.school.demo.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchoolRepository extends JpaRepository<School, Long> {
}
