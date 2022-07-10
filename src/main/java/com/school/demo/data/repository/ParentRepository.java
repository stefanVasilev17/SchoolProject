package com.school.demo.data.repository;

import com.school.demo.data.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParentRepository extends JpaRepository<Parent, Long> {
}
