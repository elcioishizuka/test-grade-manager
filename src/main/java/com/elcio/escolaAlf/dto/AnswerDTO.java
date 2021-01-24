package com.elcio.escolaAlf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private Long id;

    @NotEmpty
    private String question;

    @NotEmpty
    private String answer;

    @Positive
    @NotNull
    private Integer weight;


}
