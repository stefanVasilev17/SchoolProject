package com.school.demo.services;


import com.school.demo.data.entity.Absence;
import com.school.demo.data.entity.Grade;
import com.school.demo.dto.TeacherDTO;
import com.school.demo.models.CreatePersonModel;
import com.school.demo.views.GradeAsValueView;
import com.school.demo.views.PersonNamesView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TeacherService
{


  TeacherDTO get(long teacherId);

  TeacherDTO create(CreatePersonModel model);

  TeacherDTO edit(long id, CreatePersonModel model);

  boolean delete(long id);


  Map<Long, Map<PersonNamesView, List<GradeAsValueView>>> getAllStudentGrades(long teacherId);

//    boolean assignSchool(long teacherId,long schoolId);


  boolean removeSchool(long id);


  Grade addGrade(long id, long course_id, double grade, long student_id);

  Grade updateGrade(long id, long course_id, long grade_id, double grade);

  void deleteGrade(long id, long course_id, long grade_id);

  Absence addAbsence(long id, long course_id, LocalDate dateOfAbsence, long student_id);

  void excuseAbsence(long absence_id);
}
