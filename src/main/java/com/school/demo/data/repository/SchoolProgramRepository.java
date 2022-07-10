package com.school.demo.data.repository;

import com.school.demo.data.entity.SchoolProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolProgramRepository extends JpaRepository<SchoolProgram, Long>
{
  List<SchoolProgram> findBySchoolId(long schoolId);
}
