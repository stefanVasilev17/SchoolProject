package com.school.demo.dto;

import com.school.demo.data.entity.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SchoolProgramDTO
{
  private LocalDateTime localDateTime;
  private Course        course;
}
