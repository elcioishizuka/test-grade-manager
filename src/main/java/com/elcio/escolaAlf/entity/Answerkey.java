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
@NoArgsConstructor
@AllArgsConstructor
@Table(name="answerkey")
public class Answerkey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerkeyId;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private TestInfo testInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;

}