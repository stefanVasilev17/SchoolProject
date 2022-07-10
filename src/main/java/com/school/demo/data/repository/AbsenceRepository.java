package com.school.demo.data.repository;

import com.school.demo.data.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long>
{
}
