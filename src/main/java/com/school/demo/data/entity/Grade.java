package com.school.demo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //    @NotBlank
//    @DecimalMax("6.0")
//    @DecimalMin("2.0")
    private double grade;
}
