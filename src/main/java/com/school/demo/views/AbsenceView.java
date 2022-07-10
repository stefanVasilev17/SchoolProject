package com.school.demo.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AbsenceView
{
  private CourseOnlyIdView course;

  private LocalDate dateOfAbsence;
  private boolean   isExcused;
}
