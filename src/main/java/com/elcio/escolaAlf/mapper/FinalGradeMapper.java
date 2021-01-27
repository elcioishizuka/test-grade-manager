package com.elcio.escolaAlf.mapper;

import com.elcio.escolaAlf.dto.FinalGradeDTO;
import com.elcio.escolaAlf.entity.FinalGrade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FinalGradeMapper {

    FinalGradeMapper INSTANCE = Mappers.getMapper(FinalGradeMapper.class);

    FinalGrade toModel(FinalGradeDTO finalGradeDTO);

    FinalGradeDTO toDTO(FinalGrade finalGrade);


}
