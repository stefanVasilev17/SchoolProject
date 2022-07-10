package com.school.demo.services;

import com.school.demo.data.entity.SchoolProgramPut;
import com.school.demo.views.CourseView;

import java.util.List;
import java.util.Map;


public interface SchoolProgramService
{
  void createSchoolProgram(long schoolId, SchoolProgramPut schoolProgramPut);

  Map<String, List<CourseView>> getSchoolProgramBySchoolId(long id);
}
