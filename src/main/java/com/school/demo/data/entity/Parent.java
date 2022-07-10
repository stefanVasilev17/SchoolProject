package com.school.demo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parents")
public class Parent extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PARENT_CHILDREN",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Student> kids;

}
