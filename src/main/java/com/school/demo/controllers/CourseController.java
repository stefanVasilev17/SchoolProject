package com.school.demo.controllers;

import com.school.demo.converter.GenericConverter;
import com.school.demo.models.CreateCourseModel;
import com.school.demo.services.implementations.CourseServiceImpl;
import com.school.demo.views.CourseView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@Slf4j
public class CourseController
{

  private final GenericConverter  converter;
  private final CourseServiceImpl service;

  /**
   * A service for getting a course information
   *
   * @param id - course id
   * @return an object of CourseView;
   */
  @GetMapping("/{courseId}")
  public ResponseEntity<CourseView> getCourse(@PathVariable("courseId") long id)
  {
    log.info("Endpoint '/{courseId}' reached.");

    log.debug("Calling service.");
    CourseView view = converter.convert(service.get(id), CourseView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service for creating a course information
   *
   * @param model - object which stores information
   * @return an object of CourseView.
   */
  @PostMapping("/create")
  public ResponseEntity<CourseView> createCourse(@RequestBody CreateCourseModel model)
  {
    log.info("Endpoint '/create' reached.");

    log.debug("Calling service.");
    CourseView view = converter.convert(service.create(model), CourseView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to delete a course information
   *
   * @param id - course id
   * @return 200 or 400
   */
  @DeleteMapping("/{courseId}/delete")
  public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") long id)
  {
    log.info("Endpoint '/{courseId}/delete' reached.");

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
   * A service to assign teacher to a course
   *
   * @param courseId  - course id
   * @param teacherId - teacher id
   * @return 200;
   */
  @PutMapping("/{courseId}/assign/teacher/{teacherId}")
  public ResponseEntity<Void> addTeacherToCourse(@PathVariable long courseId, @PathVariable long teacherId)
  {
    log.info("Endpoint '/{courseId}/assign/teacher/{teacherId}' reached.");

    log.debug("Calling service.");
    service.assignTeacher(courseId, teacherId);

    return ResponseEntity.ok().build();
  }


  /**
   * A service to assign student to a course
   *
   * @param courseId  - course id
   * @param studentID - student id
   * @return 200;
   */
  @PutMapping("/{courseId}/assign/student/{studentID}")
  public ResponseEntity<Void> addStudentToCourse(@PathVariable long courseId, @PathVariable long studentID)
  {
    log.info("Endpoint '/{courseId}/assign/student/{studentID}' reached.");

    log.debug("Calling service.");
    service.addStudent(courseId, studentID);

    return ResponseEntity.ok().build();
  }

  /**
   * A service to remove student to a course
   *
   * @param courseId  - course id
   * @param studentID - student id
   * @return 200;
   */

  @PutMapping("/{courseId}/remove/student/{studentID}")
  public ResponseEntity<Void> removeStudentToCourse(@PathVariable long courseId, @PathVariable long studentID)
  {
    log.info("Endpoint '/{courseId}/remove/student/{studentID}' reached.");

    log.debug("Calling service.");
    service.removeStudent(courseId, studentID);

    return ResponseEntity.ok().build();
  }

}
