package com.elcio.escolaAlf.dto;


import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.enums.Status;
import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalGradeDTO {

    private Long finalGradeId;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Valid
    private StudentDTO student;

    private Double finalGrade;

    @Enumerated(EnumType.STRING)
    private Status status;

}
