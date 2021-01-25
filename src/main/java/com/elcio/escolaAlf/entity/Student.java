package com.elcio.escolaAlf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

//    @OneToMany(mappedBy = "student")
//    private List<TestAnswers> testAnswersList = new ArrayList<>();


//    @Column(nullable = false, unique = true)
//    private String cpf;

}
