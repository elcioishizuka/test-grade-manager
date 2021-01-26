package com.elcio.escolaAlf.entity;

import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TestInfo testInfo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Student student;

    @Column
    private Double grade;


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long gradeId;
//
//    @Column
//    private String studentId;
//
//    @Column
//    private String name;
//
//    @Column
//    private String lastName;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Subject subject;
//
//    @Column
//    private String testNumber;



}
