package com.elcio.escolaAlf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotEmpty
    private String studentId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    public StudentDTO (String studentId, String name,String lastName){
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
    }

}
