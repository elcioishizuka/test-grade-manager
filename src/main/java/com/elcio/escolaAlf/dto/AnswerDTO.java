package com.elcio.escolaAlf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private Long answerId;

    @NotEmpty
    private String question;

    @NotEmpty
    private String answer;

    @Positive(message = "Weight must be Integer type and greater than zero!")
    private Integer weight;


}
