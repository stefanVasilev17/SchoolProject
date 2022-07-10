package com.school.demo.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SchoolProgramPut
{
  private String     weekDay;
  private List<Long> courseIds;
  private long     schoolProgramId;
}
