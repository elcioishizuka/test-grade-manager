package com.elcio.escolaAlf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
