package com.elcio.escolaAlf.mapper;

import com.elcio.escolaAlf.dto.AnswerkeyDTO;
import com.elcio.escolaAlf.dto.GradeDTO;
import com.elcio.escolaAlf.entity.Answerkey;
import com.elcio.escolaAlf.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {

    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    Grade toModel(GradeDTO gradeDTO);

    GradeDTO toDTO(Grade grade);


}
