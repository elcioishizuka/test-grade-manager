package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.entity.Answer;
import com.elcio.escolaAlf.entity.Student;
import com.elcio.escolaAlf.entity.TestInfo;
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
    private Student student;

    @Valid
    private TestInfo testInfo;

    @Valid
    @NotEmpty
    private List<Answer> answers;


}
