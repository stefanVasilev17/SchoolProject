package com.school.demo.controllers;

import com.school.demo.converter.GenericConverter;
import com.school.demo.data.entity.Absence;
import com.school.demo.models.CreatePersonModel;
import com.school.demo.services.implementations.TeacherServiceImpl;
import com.school.demo.views.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
@Slf4j
public class TeacherController
{

  private final TeacherServiceImpl service;
  private final GenericConverter   converter;

  /**
   * A service to get teachers information
   *
   * @param id - teacher id
   * @return an object of TeacherView
   */

  @GetMapping("/{teacherId}")
  public ResponseEntity<TeacherView> getTeacher(@PathVariable("teacherId") long id)
  {
    log.info("Endpoint '/{teacherId}' reached.");

    log.debug("Calling service.");
    TeacherView view = converter.convert(service.get(id), TeacherView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to create a teacher information
   *
   * @param model - info for teacher
   * @return an object of StudentView
   */

  @PostMapping("/create")
  public ResponseEntity<TeacherView> createTeacher(@RequestBody CreatePersonModel model)
  {
    log.info("Endpoint '/create' reached.");

    log.debug("Calling service.");
    TeacherView view = converter.convert(service.create(model), TeacherView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to edit a student information
   *
   * @param model - info for student
   * @return 200
   */

  @PutMapping("/{teacherId}/edit")
  public ResponseEntity<TeacherView> editTeacher(@PathVariable("teacherId") long id, @RequestBody CreatePersonModel model)
  {
    log.info("Endpoint '/{teacherId}/edit' reached.");

    log.debug("Calling service.");
    TeacherView view = converter.convert(service.edit(id, model), TeacherView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to delete a teacher information
   *
   * @param id - teacher id
   * @return 200 or 400
   */
  @DeleteMapping("/{teacherId}/delete")
  public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") long id)
  {
    log.info("Endpoint '/{teacherId}/delete' reached.");

    log.debug("Calling service.");
    boolean flag = service.delete(id);
    if (flag) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * A service to get all students grades by teacher id
   *
   * @param id - teacher id
   * @return getAllStudentGrades
   */

  @GetMapping("/{id}/get/all/students/grades")
  public Map<Long, Map<PersonNamesView, List<GradeAsValueView>>> getAllStudentGradesByTeacherId(@PathVariable("id") long id)
  {
    log.info("Endpoint '/{id}/get/all/students/grades' reached.");

    log.debug("Calling service.");
    return service.getAllStudentGrades(id);
  }

  /**
   * A service to add grade
   *
   * @param id         - teacher id
   * @param course_id  - course id
   * @param grade      - grade
   * @param student_id - student id
   * @return GradeView
   */

  @PostMapping("/{id}/course/{course_id}/add/grade/{grade}/student/{student_id}")
  public ResponseEntity<GradeView> addGrade(@PathVariable("id") long id, @PathVariable("course_id") long course_id,
                                            @PathVariable("grade") double grade, @PathVariable("student_id") long student_id)
  {


    log.info("Endpoint '/{id}/course/{course_id}/add/grade/{grade}/student/{student_id}' reached.");

    log.debug("Calling service.");
    GradeView view = converter.convert(service.addGrade(id, course_id, grade, student_id), GradeView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to add absence
   *
   * @param id            - teacher id
   * @param course_id     - course id
   * @param student_id    - student id
   * @param dateOfAbsence - date
   * @return AbsenceView
   */

  @PostMapping("/{id}/course/{course_id}/add/absence/student/{student_id}")
  public ResponseEntity<AbsenceView> addAbsence(@PathVariable("id") long id,
                                                @PathVariable("course_id") long course_id,
                                                @PathVariable("student_id") long student_id,
                                                @RequestParam(value = "dateOfAbsence")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfAbsence)
  {
    log.info("Endpoint '/{id}/course/{course_id}/add/absence/{absence}/student/{student_id}' reached.");

    log.debug("Calling service.");
    AbsenceView view = converter.convert(service.addAbsence(id, course_id, dateOfAbsence, student_id), AbsenceView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to excuse absence
   *
   * @param absence_id - absence id
   * @return 200
   */

  @PutMapping("/excuse/absence/{absence_id}")
  public ResponseEntity<Void> excuseAbsence(@PathVariable("absence_id") long absence_id)
  {
    log.info("Endpoint '/{id}/course/{course_id}/excuse/absence/{absence_id}/student/{student_id}' reached.");

    log.debug("Calling service.");

    service.excuseAbsence(absence_id);

    return ResponseEntity.ok().build();
  }


  /**
   * A service to update grade
   *
   * @param id        - teacher id
   * @param course_id - course id
   * @param grade_id  - grade id
   * @param grade     - grade
   * @return GradeView
   */

  @PutMapping("/{id}/course/{course_id}/edit/grade/{grade_id}/value/{grade}")
  public ResponseEntity<GradeView> updateGrade(@PathVariable("id") long id, @PathVariable("course_id") long course_id,
                                               @PathVariable("grade_id") long grade_id, @PathVariable("grade") double grade)
  {


    log.info("Endpoint '/{id}/course/{course_id}/edit/grade/{grade_id}/value/{grade}' reached.");

    log.debug("Calling service.");
    GradeView view = converter.convert(service.updateGrade(id, course_id, grade_id, grade), GradeView.class);


    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to delete grade
   *
   * @param id        - teacher id
   * @param course_id - course id
   * @param grade_id  - grade id
   * @return 200
   */

  @DeleteMapping("/{id}/course/{course_id}/delete/grade/{gradeId}")
  public ResponseEntity<Void> deleteGrade(@PathVariable("id") long id, @PathVariable("course_id") long course_id,
                                          @PathVariable("gradeId") long grade_id)
  {

    log.info("Endpoint '/{id}/course/{course_id}/delete/grade/{gradeId}' reached.");

    log.debug("Calling service.");
    service.deleteGrade(id, course_id, grade_id);


    return ResponseEntity.ok().build();
  }
}
