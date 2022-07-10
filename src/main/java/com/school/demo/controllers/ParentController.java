package com.school.demo.controllers;

import com.school.demo.converter.GenericConverter;
import com.school.demo.models.CreatePersonModel;
import com.school.demo.services.implementations.ParentServiceImpl;
import com.school.demo.views.CourseIdAndAbsenceView;
import com.school.demo.views.CourseIdAndGradesView;
import com.school.demo.views.ParentView;
import com.school.demo.views.TeacherView;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parents")
@AllArgsConstructor
@Slf4j
public class ParentController
{
  private final ParentServiceImpl service;
  private final GenericConverter  converter;

  /**
   * A service to get parent information
   *
   * @param id - parent id
   * @return an object of ParentView
   */

  @GetMapping("/{parentId}")
  public ResponseEntity<ParentView> getParent(@PathVariable("parentId") long id)
  {
    log.info("Endpoint '/{parentId}' reached.");

    log.debug("Calling service.");
    ParentView view = converter.convert(service.get(id), ParentView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to create parent information
   *
   * @param model - parent information
   * @return an object of ParentView
   */

  @PostMapping("/create")
  public ResponseEntity<ParentView> createParent(@RequestBody CreatePersonModel model)
  {
    log.info("Endpoint '/create' reached.");

    log.debug("Calling service.");
    ParentView view = converter.convert(service.create(model), ParentView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to edit parent information
   *
   * @param id - id of parent
   * @param model - parent information
   * @return an object of ParentView
   */

  @PutMapping("/{parentId}/edit")
  public ResponseEntity<ParentView> editParent(@PathVariable("parentId") long id, @RequestBody CreatePersonModel model)
  {
    log.info("Endpoint '/{parentId}/edit' reached.");

    log.debug("Calling service.");
    ParentView view = converter.convert(service.edit(id, model), ParentView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to delete parent information
   *
   * @param id - id of parent
   * @return an object of ParentView
   */

  @DeleteMapping("/{parentId}/delete")
  public ResponseEntity<Void> deleteParent(@PathVariable("parentId") long id)
  {
    log.info("Endpoint '/{parentId}/delete' reached.");

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
   * A service to add a student to parent
   *
   * @param parentId - id of parent
   * @param studentId - id of student
   * @return 200 or 400.
   */

  @PutMapping("/{parentId}/add/kid/{StudentId}")
  public ResponseEntity<Void> addChild(@PathVariable("parentId") long parentId, @PathVariable("StudentId") long studentId)
  {
    log.info("Endpoint '/{parentId}/add/kid/{StudentId}' reached.");

    log.debug("Calling service.");
    boolean flag = service.addChild(parentId, studentId);
    if (flag) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * A service to remove a student from parent
   *
   * @param parentId  - id of parent
   * @param studentId - id of student
   * @return 200 or 400.
   */
  @PutMapping("/{parentId}/remove/kid/{StudentId}")
  public ResponseEntity<Void> removeChild(@PathVariable("parentId") long parentId, @PathVariable("StudentId") long studentId)
  {
    log.info("Endpoint '/{parentId}/remove/kid/{StudentId}' reached.");

    log.debug("Calling service.");
    boolean flag = service.removeChild(parentId, studentId);
    if (flag) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  /**
   * A service to get all grades of parent's children
   *
   * @param id - id of parent
   * @return getAllGrades.
   */
  @GetMapping("/{id}/get/kids/grades")
  public Map<String, List<CourseIdAndGradesView>> getGrades(@PathVariable("id") long id)
  {
    log.info("Endpoint '/{id}/get/kids/grades' reached.");

    log.debug("Calling service.");
    return service.getAllGrades(id);
  }

  /**
   * A service to get all absences of parent's children
   *
   * @param id - id of parent
   * @return getAllAbsences.
   */

  @GetMapping("/{id}/get/kids/absences")
  public Map<String, List<CourseIdAndAbsenceView>> getAbsences(@PathVariable("id") long id)
  {
    log.info("Endpoint '/{id}/get/kids/absences' reached.");

    log.debug("Calling service.");
    return service.getAllAbsences(id);
  }

  /**
   * A service to get all teachers of parent's children
   *
   * @param id - id of parent
   * @return getAllTeachers.
   */

  @GetMapping("/{id}/get/kids/teachers")
  public Map<String, List<TeacherView>> getTeachers(@PathVariable("id") long id)
  {
    log.info("Endpoint '/{id}/get/kids/teachers' reached.");

    log.debug("Calling service.");
    return service.getAllTeachers(id);
  }
}
