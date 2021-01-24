package com.elcio.escolaAlf.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_answer")
public class TestAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testAnswersId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TestInfo testInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;


}
