package com.elcio.escolaAlf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestAnswersDTO {

    private Long testAnswersId;

    @Valid
    private StudentDTO student;

    @Valid
    private TestInfoDTO testInfo;

    @Valid
    @NotEmpty
    private List<AnswerDTO> answers;

}
