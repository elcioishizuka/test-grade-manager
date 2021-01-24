package com.elcio.escolaAlf.dto;


import com.elcio.escolaAlf.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestInfoDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @NotEmpty
    private String testNumber;

}
