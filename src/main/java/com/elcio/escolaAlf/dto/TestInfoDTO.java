package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestInfoDTO {

    private Long testInfoId;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @NotEmpty
    private String testNumber;

}
