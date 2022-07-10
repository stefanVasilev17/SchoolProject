package com.school.demo.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;



@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    @NotNull
//    @Size(min = 5, max = 20, message = "Min 5, Max 20")
    private String username;
    //@ValidPassword
    @NotNull
//    @Size(min = 8, max = 20, message = "Min 8, Max 20")
    private String password;

    @NotNull
    private Role role;

    private String firstName;
    private String lastName;

}
