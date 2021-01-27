package com.elcio.escolaAlf.entity;

import com.elcio.escolaAlf.enums.Subject;
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
@Table(name="testinfo")
public class TestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testInfoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private String testNumber;

}
