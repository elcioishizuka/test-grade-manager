package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.entity.Answer;
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
public class AnswerkeyDTO {

    private Long id;

    @Valid
    private TestInfoDTO testInfo;

    @Valid
    @NotEmpty
    private List<AnswerDTO> answers;

}
