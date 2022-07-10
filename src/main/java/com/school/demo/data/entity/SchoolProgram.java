package com.school.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schoolProgram")
public class SchoolProgram extends BaseEntity
{
  @OneToMany
  @JoinColumn(name = "school_program_id")
  @JsonIgnoreProperties({"grades", "teachers", "students"})
  private List<Course> courses;

  @OneToOne
  @JoinColumn(name = "school_id")
  @JsonIgnoreProperties({"director", "teachers", "students"})
  private School school;

  private String weekDay;

  private long schoolProgramId;
}
