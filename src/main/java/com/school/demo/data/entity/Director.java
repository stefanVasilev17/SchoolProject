package com.school.demo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "director")
public class Director extends Person {

    @OneToOne(mappedBy = "director")
    private School school;
}
