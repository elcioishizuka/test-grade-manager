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
@Table(name="student")
public class Student {

    @Id
    private Long studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

//    @Column(nullable = false, unique = true)
//    private String cpf;

}
