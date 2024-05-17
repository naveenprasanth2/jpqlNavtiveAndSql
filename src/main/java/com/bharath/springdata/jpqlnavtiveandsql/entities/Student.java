package com.bharath.springdata.jpqlnavtiveandsql.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "lname")
    private String lastName;
    private int score;
}
