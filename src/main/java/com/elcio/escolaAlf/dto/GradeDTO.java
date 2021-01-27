package com.elcio.escolaAlf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

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

}
