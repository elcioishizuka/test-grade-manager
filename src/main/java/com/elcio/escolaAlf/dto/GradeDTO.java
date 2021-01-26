package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.entity.TestInfo;
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
    private TestInfo testInfo;

    @Valid
    private Student student;

    private Double grade;


}
