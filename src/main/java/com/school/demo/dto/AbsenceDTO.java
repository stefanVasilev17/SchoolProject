package com.school.demo.dto;

import com.school.demo.data.entity.Course;
import com.school.demo.data.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class AbsenceDTO
{
  private Student student;
  private Course  course;


  private LocalDate dateOfAbsence;
  private boolean   isExcused;
  private long      id;
}
