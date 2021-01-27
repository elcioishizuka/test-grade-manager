package com.elcio.escolaAlf.mapper;

import com.elcio.escolaAlf.dto.StudentDTO;
import com.elcio.escolaAlf.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toModel(StudentDTO studentDTO);

    StudentDTO toDTO(Student student);


}
