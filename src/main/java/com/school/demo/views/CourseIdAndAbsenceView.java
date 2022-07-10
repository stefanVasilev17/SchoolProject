
package com.school.demo.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourseIdAndAbsenceView
{
  private long                    id;
  private List<SimpleAbsenceView> absences;
}

