package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.entity.TestInfo;
import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {

    private Long gradeId;

    @Valid
    private TestInfoDTO testInfo;

    @Valid
    private StudentDTO student;

    private Double grade;

//    private Long gradeId;
//
//    private String studentId;
//
//    private String name;
//
//    private String lastName;
//
//    @Enumerated(EnumType.STRING)
//    private Subject subject;
//
//    private String testNumber;
//
//    private Double grade;


}
