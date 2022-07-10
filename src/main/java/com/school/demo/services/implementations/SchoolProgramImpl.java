package com.school.demo.services.implementations;

import com.school.demo.converter.GenericConverter;
import com.school.demo.data.entity.*;
import com.school.demo.data.repository.CourseRepository;
import com.school.demo.data.repository.SchoolProgramRepository;
import com.school.demo.data.repository.SchoolRepository;
import com.school.demo.services.SchoolProgramService;
import com.school.demo.views.CourseView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
public class SchoolProgramImpl implements SchoolProgramService
{
  private final SchoolProgramRepository schoolProgramRepository;
  private final SchoolRepository        schoolRepository;
  private final CourseRepository        courseRepository;
  private final GenericConverter        converter;

  @Override
  public void createSchoolProgram(long schoolId, SchoolProgramPut schoolProgramPut)
  {
    List<Course> listOfCourses = new ArrayList<>();

    for (Long courseId : schoolProgramPut.getCourseIds()) {
      Course course = courseRepository.findById(courseId).orElseThrow();
      listOfCourses.add(course);
    }

    SchoolProgram schoolProgram = new SchoolProgram();
    schoolProgram.setSchool(schoolRepository.findById(schoolId).orElseThrow());
    schoolProgram.setCourses(listOfCourses);
    schoolProgram.setWeekDay(schoolProgramPut.getWeekDay());
    //schoolProgram.setSchoolProgramId(schoolProgramPut.getSchoolProgramId());

    schoolProgramRepository.save(schoolProgram);
  }

  @Override
  public Map<String, List<CourseView>> getSchoolProgramBySchoolId(long schoolId)
  {

    Map<String, List<CourseView>> schoolProgramMap = new HashMap<>();

    // SchoolProgram schoolProgram1 = schoolProgramRepository.findById(schoolProgramId).orElseThrow();

    //   List<String> weekDays = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

    List<SchoolProgram> schoolProgramListBySchoolId = schoolProgramRepository.findBySchoolId(schoolId);

    for (SchoolProgram schoolProgram : schoolProgramListBySchoolId) {
      schoolProgramMap.put(schoolProgram.getWeekDay(), converter.convertList(schoolProgram.getCourses(), CourseView.class));
    }

//    for (String weekDay : weekDays) {
//      schoolProgramMap.put(weekDay, converter.convertList(schoolProgram1.getCourses(), CourseView.class));
//    }

    return schoolProgramMap;
  }


  //  @Override
  //  public Map<String, List<CourseIdAndGradesView>> getAllGrades(long parentId)
  //  {
  //    log.debug("Getting all child grades on parent " + parentId);
  //    ParentDTO parentDTO = this.get(parentId);
  //
  //    List<StudentDTO> kids = converter.convertList(parentDTO.getKids(), StudentDTO.class);
  //
  //    Map<String, List<CourseIdAndGradesView>> kidsGrades = new HashMap<>();
  //
  //    for (StudentDTO kid : kids) {
  //      kidsGrades.put(String.format("%s %s", kid.getFirstName(), kid.getLastName()),
  //          service.getAllGrades(kid.getId()));
  //    }
  //
  //    return kidsGrades;
  //  }
}
