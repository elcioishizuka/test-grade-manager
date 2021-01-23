package com.elcio.escolaAlf.entity;

import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="answerkey")
public class Answerkey {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Subject subject;
//
//    @Column(nullable = false)
//    private Long testNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private TestInfo testInfo;

//    @Column(nullable = false)
//    private String answer;

//    @Column(nullable = false)
//    private Integer weight;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;



}