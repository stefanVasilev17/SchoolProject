package com.school.demo.controllers;

import com.school.demo.converter.GenericConverter;
import com.school.demo.models.CreateDirectorModel;
import com.school.demo.services.implementations.DirectorServiceImpl;
import com.school.demo.views.CourseIdAndGradesView;
import com.school.demo.views.DirectorView;
import com.school.demo.views.ParentDirectorView;
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

@RestController
@RequestMapping("/api/directors")
@AllArgsConstructor
@Slf4j
public class DirectorController
{

  private final DirectorServiceImpl service;
  private final GenericConverter    converter;

  /**
   * A service to get director information
   *
   * @param id - director id
   * @return an object of DirectorView
   */

  @GetMapping("/{directorId}")
  public ResponseEntity<DirectorView> getDirector(@PathVariable("directorId") long id)
  {
    log.info("Endpoint '/{directorId}' reached.");

    log.debug("Calling service.");
    DirectorView view = converter.convert(service.get(id), DirectorView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to create a director
   *
   * @param model - an object storing director information
   * @return an object of DirectorView
   */

  @PostMapping("/create")
  public ResponseEntity<DirectorView> createDirector(@RequestBody CreateDirectorModel model)
  {
    log.info("Endpoint '/create' reached.");

    log.debug("Calling service.");
    DirectorView view = converter.convert(service.create(model), DirectorView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to edit a director
   *
   * @param id    - director id
   * @param model - an object storing director information
   * @return an object of DirectorView
   */
  @PutMapping("/{directorId}/edit")
  public ResponseEntity<DirectorView> editDirector(@PathVariable("directorId") long id, @RequestBody CreateDirectorModel model)
  {
    log.info("Endpoint '/{directorId}/edit' reached.");

    log.debug("Calling service.");
    DirectorView view = converter.convert(service.edit(id, model), DirectorView.class);

    return ResponseEntity.ok().body(view);
  }

  /**
   * A service to delete a director
   *
   * @param id - director id
   * @return 200 or 400
   */
  @DeleteMapping("/{directorId}/delete")
  public ResponseEntity<Void> deleteDirector(@PathVariable("directorId") long id)
  {
    log.info("Endpoint '/{directorId}/delete' reached.");

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
   * A service to get all grades on subjects
   *
   * @param id - director id
   * @return an object of CourseIdAndGradesView
   */

  @GetMapping("/{directorId}/get/all/grades")
  public List<CourseIdAndGradesView> getAllGradesOnSubjects(@PathVariable("directorId") long id)
  {
    log.info("Endpoint '/{directorId}/get/all/grades' reached.");

    log.debug("Calling service.");
    return service.getAllCoursesAndAllGrades(id);
  }

  /**
   * A service to get all teachers in the school
   *
   * @param id - director id
   * @return an object of TeacherView
   */

  @GetMapping("/{directorId}/get/all/teachers")
  public List<TeacherView> getAllTeachers(@PathVariable("directorId") long id)
  {
    log.info("Endpoint '/{directorId}/get/all/teachers' reached.");

    log.debug("Calling service.");
    return service.getAllTeachers(id);
  }

  /**
   * A service to get all parents of the kids in the school
   *
   * @param id - director id
   * @return an object of ParentDirectorView
   */

  @GetMapping("/{directorId}/get/all/parents")
  public List<ParentDirectorView> getAllParents(@PathVariable("directorId") long id)
  {
    log.info("Endpoint '/{directorId}/get/all/parents' reached.");

    log.debug("Calling service.");
    return service.getAllParents(id);
  }

}
