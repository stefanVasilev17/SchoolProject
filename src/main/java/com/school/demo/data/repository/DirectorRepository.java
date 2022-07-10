package com.school.demo.data.repository;

import com.school.demo.data.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DirectorRepository extends JpaRepository<Director, Long> {
}
