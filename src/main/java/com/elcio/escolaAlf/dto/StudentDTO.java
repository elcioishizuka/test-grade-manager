package com.elcio.escolaAlf.dto;

import com.elcio.escolaAlf.entity.TestAnswers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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

//    private List<TestAnswers> testAnswersList = new ArrayList<>();

//    @NotEmpty
////    @UniqueElements
//    private String cpf;

}
