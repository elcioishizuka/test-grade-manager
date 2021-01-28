package com.elcio.escolaAlf.builder;

import com.elcio.escolaAlf.dto.StudentDTO;
import lombok.Builder;

@Builder
public class StudentDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String studentId = "123";

    @Builder.Default
    private String name = "José";

    @Builder.Default
    private String lastName = "Silva";

    public StudentDTO toStudentDTO(){
        return new StudentDTO(id, studentId, name, lastName);
    }
}
